package com.jrdm.BiblioCity.controller;

import com.jrdm.BiblioCity.dto.DashboardDTO;
import com.jrdm.BiblioCity.entity.MultaEntity;
import com.jrdm.BiblioCity.repository.MultaRepository;
import com.jrdm.BiblioCity.service.LibroService;
import com.jrdm.BiblioCity.service.PrestamoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private LibroService  libroService;
    @Autowired
    private MultaRepository multaRepository;

    @GetMapping("/dashboard")
    public DashboardDTO getDashboard() {
        return new DashboardDTO(
                libroService.listarTodos(), // Aquí podrías filtrar por los más prestados
                multaRepository.findByEstado(MultaEntity.EstadoMulta.PENDIENTE)
        );
    }
}
