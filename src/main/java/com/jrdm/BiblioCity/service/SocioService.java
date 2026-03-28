package com.jrdm.BiblioCity.service;

import com.jrdm.BiblioCity.entity.PrestamoEntity;
import com.jrdm.BiblioCity.entity.SocioEntity;
import com.jrdm.BiblioCity.repository.SocioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class SocioService {
    @Autowired
    private SocioRepository socioRepository;



    public List<SocioEntity> listarTodos() {
        return socioRepository.findAll();
    }

    public SocioEntity buscarPorId(Long id) {
        return socioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Socio no encontrado"));
    }

    public SocioEntity guardar(SocioEntity socio) {
        return socioRepository.save(socio);
    }

    public SocioEntity actualizar(Long id, SocioEntity datos) {
        SocioEntity socio = buscarPorId(id);
        socio.setNombres(datos.getNombres());
        socio.setApellidos(datos.getApellidos());
        socio.setIdentificacion(datos.getIdentificacion());
        return socioRepository.save(socio);
    }

    public SocioEntity darDeBaja(Long id) {
        SocioEntity socio = buscarPorId(id);
        boolean tienePrestamosActivos = socio.getPrestamos()
                .stream()
                .anyMatch(p -> p.getEstado() == PrestamoEntity.EstadoPrestamo.ACTIVO);
        if (tienePrestamosActivos) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "El socio tiene préstamos activos, no puede darse de baja");
        }
        socio.setEstado(SocioEntity.EstadoSocio.INACTIVO);
        return socioRepository.save(socio);
    }
}
