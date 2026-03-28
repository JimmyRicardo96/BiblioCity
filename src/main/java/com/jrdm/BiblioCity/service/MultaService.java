package com.jrdm.BiblioCity.service;

import com.jrdm.BiblioCity.entity.MultaEntity;
import com.jrdm.BiblioCity.repository.MultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class MultaService {

    @Autowired
    private MultaRepository multaRepository;

    // GET - Listar todas
    public List<MultaEntity> listarTodas() {
        return multaRepository.findAll();
    }

    // GET - Listar solo pendientes
    public List<MultaEntity> listarPendientes() {
        return multaRepository.findByEstado(MultaEntity.EstadoMulta.PENDIENTE);
    }

    // GET - Multas de un socio específico
    public List<MultaEntity> listarPorSocio(Long idSocio) {
        return multaRepository.findByPrestamoSocioIdSocio(idSocio);
    }

    // PUT - Pagar multa (cambia estado a PAGADA)
    public MultaEntity pagarMulta(Long id) {
        MultaEntity multa = multaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Multa no encontrada"));
        if (multa.getEstado() == MultaEntity.EstadoMulta.PAGADA) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La multa ya fue pagada");
        }
        multa.setEstado(MultaEntity.EstadoMulta.PAGADA);
        return multaRepository.save(multa);
    }
}
