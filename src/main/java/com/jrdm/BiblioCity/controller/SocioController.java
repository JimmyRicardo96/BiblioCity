package com.jrdm.BiblioCity.controller;

import com.jrdm.BiblioCity.entity.SocioEntity;
import com.jrdm.BiblioCity.service.SocioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/socios")
public class SocioController {

    @Autowired
    private SocioService socioService;

    @GetMapping
    public ResponseEntity<List<SocioEntity>> listar() {
        return ResponseEntity.ok(socioService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SocioEntity> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(socioService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<SocioEntity> crear(@RequestBody SocioEntity socio) {
        return ResponseEntity.status(201).body(socioService.guardar(socio));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SocioEntity> actualizar(@PathVariable Long id, @RequestBody SocioEntity datos) {
        return ResponseEntity.ok(socioService.actualizar(id, datos));
    }

    @PutMapping("/{id}/baja")
    public ResponseEntity<SocioEntity> darDeBaja(@PathVariable Long id) {
        return ResponseEntity.ok(socioService.darDeBaja(id));
    }
}
