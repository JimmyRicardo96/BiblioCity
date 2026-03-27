package com.jrdm.BiblioCity.controller;

import com.jrdm.BiblioCity.dto.DevolucionRequestDTO;
import com.jrdm.BiblioCity.dto.PrestamoRequestDTO;
import com.jrdm.BiblioCity.service.PrestamoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/prestamos")
public class PrestamoController {

    @Autowired
    private PrestamoService prestamoService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody PrestamoRequestDTO dto) {
        return ResponseEntity.status(201).body(prestamoService.crearPrestamo(dto));
    }

    @PostMapping("/devolucion")
    public ResponseEntity<?> devolver(@RequestBody DevolucionRequestDTO dto) {
        return ResponseEntity.ok(prestamoService.procesarDevolucion(dto.getIdPrestamo()));
    }

}
