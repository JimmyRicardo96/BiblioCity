package com.jrdm.BiblioCity.controller;

import com.jrdm.BiblioCity.entity.SocioEntity;
import com.jrdm.BiblioCity.service.SocioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/socios")
public class SocioController {

    @Autowired
    private SocioService socioService;

    @GetMapping
    public List<SocioEntity> getSocios() {
        return socioService.listarTodos();
    }

    @PostMapping
    public SocioEntity saveSocio(@RequestBody SocioEntity s) {
        return socioService.guardar(s);
    }
}
