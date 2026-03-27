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

    public List<SocioEntity> listarTodos() { return socioRepository.findAll(); }
    public SocioEntity guardar(SocioEntity socio) { return socioRepository.save(socio); }

    public void darDeBaja(Long id) {
        SocioEntity socio = socioRepository.findById(id).orElseThrow();
        // REGLA: No dar de baja si tiene préstamos activos
        if (socio.getPrestamos().stream().anyMatch(p -> p.getEstado() == PrestamoEntity.EstadoPrestamo.ACTIVO)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Socio con préstamos activos no puede darse de baja");
        }
        socio.setEstado(SocioEntity.EstadoSocio.INACTIVO);
        socioRepository.save(socio);
    }
}
