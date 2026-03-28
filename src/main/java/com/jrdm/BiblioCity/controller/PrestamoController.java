package com.jrdm.BiblioCity.controller;

import com.jrdm.BiblioCity.dto.DevolucionRequestDTO;
import com.jrdm.BiblioCity.dto.PrestamoRequestDTO;
import com.jrdm.BiblioCity.entity.PrestamoEntity;
import com.jrdm.BiblioCity.service.PrestamoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prestamos")
public class PrestamoController {

    @Autowired
    private PrestamoService prestamoService;

    @GetMapping
    public ResponseEntity<List<PrestamoEntity>> listar() {
        return ResponseEntity.ok(prestamoService.listarTodos());
    }

    @GetMapping("/activos")
    public ResponseEntity<List<PrestamoEntity>> listarActivos() {
        return ResponseEntity.ok(prestamoService.listarActivos());
    }
    @PostMapping
    public ResponseEntity<?> create(@RequestBody PrestamoRequestDTO dto) {
        return ResponseEntity.status(201).body(prestamoService.crearPrestamo(dto));
    }

    @PostMapping("/devolucion")
    public ResponseEntity<?> devolver(@RequestBody DevolucionRequestDTO dto) {
        return ResponseEntity.ok(prestamoService.procesarDevolucion(dto.getIdPrestamo()));
    }

}
