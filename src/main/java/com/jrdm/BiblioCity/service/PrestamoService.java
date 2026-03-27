package com.jrdm.BiblioCity.service;

import com.jrdm.BiblioCity.dto.PrestamoRequestDTO;
import com.jrdm.BiblioCity.entity.*;
import com.jrdm.BiblioCity.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;

@Service
public class PrestamoService {

    @Autowired
    private PrestamoRepository prestamoRepository;
    @Autowired
    private SocioRepository socioRepository;
    @Autowired
    private LibroRepository libroRepository;
    @Autowired
    private MultaRepository multaRepository;

    @Transactional
    public PrestamoEntity crearPrestamo(PrestamoRequestDTO request) {

        // 1. BUSCAR ENTIDADES (Validar existencia)
        SocioEntity socio = socioRepository.findById(request.getIdSocio())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Socio no encontrado"));

        LibroEntity libro = libroRepository.findById(request.getIdLibro())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Libro no encontrado"));

        // --- VALIDACIONES DE ORO ---

        // REGLA 1: Máximo 3 préstamos activos simultáneamente
        long prestamosActivos = prestamoRepository.countBySocioAndEstado(socio, PrestamoEntity.EstadoPrestamo.ACTIVO);
        if (prestamosActivos >= 3) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El socio ya tiene el máximo de 3 préstamos activos");
        }

        // REGLA 2: Disponibilidad de ejemplares
        if (libro.getEjemplaresDisponibles() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No hay ejemplares disponibles de este libro");
        }

        // REGLA 3: No tener multas pendientes
        boolean tieneMultas = multaRepository.existsByPrestamoSocioAndEstado(socio, MultaEntity.EstadoMulta.PENDIENTE);
        if (tieneMultas) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "El socio tiene multas pendientes y no puede realizar nuevos préstamos");
        }

        // --- PROCESAMIENTO ---

        // Descontar stock del libro
        libro.setEjemplaresDisponibles(libro.getEjemplaresDisponibles() - 1);
        libroRepository.save(libro);

        // Crear el préstamo
        PrestamoEntity nuevoPrestamo = new PrestamoEntity();
        nuevoPrestamo.setSocio(socio);
        nuevoPrestamo.setLibro(libro);
        nuevoPrestamo.setFechaPrestamo(LocalDate.now());
        nuevoPrestamo.setFechaDevolucionEstimada(LocalDate.now().plusDays(7)); // Ejemplo: 7 días de plazo
        nuevoPrestamo.setEstado(PrestamoEntity.EstadoPrestamo.ACTIVO);

        return prestamoRepository.save(nuevoPrestamo);
    }

    @Transactional
    public PrestamoEntity procesarDevolucion(Long idPrestamo) {
        PrestamoEntity p = prestamoRepository.findById(idPrestamo).orElseThrow();
        p.setFechaDevolucionReal(LocalDate.now());
        p.setEstado(PrestamoEntity.EstadoPrestamo.DEVUELTO);

        // Devolver stock
        p.getLibro().setEjemplaresDisponibles(p.getLibro().getEjemplaresDisponibles() + 1);

        // REGLA: Generar multa automática si hay retraso
        if (p.getFechaDevolucionReal().isAfter(p.getFechaDevolucionEstimada())) {
            MultaEntity m = new MultaEntity();
            m.setPrestamo(p);
            m.setMonto(5.0F); // Monto fijo de ejemplo
            m.setEstado(MultaEntity.EstadoMulta.PENDIENTE);
            multaRepository.save(m);
        }
        return prestamoRepository.save(p);
    }
}