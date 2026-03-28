package com.jrdm.BiblioCity.controller;

import com.jrdm.BiblioCity.entity.MultaEntity;
import com.jrdm.BiblioCity.service.MultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/multas")
public class MultaController {

    @Autowired
    private MultaService multaService;

    @GetMapping
    public ResponseEntity<List<MultaEntity>> listarTodas() {
        return ResponseEntity.ok(multaService.listarTodas());
    }

    @GetMapping("/pendientes")
    public ResponseEntity<List<MultaEntity>> listarPendientes() {
        return ResponseEntity.ok(multaService.listarPendientes());
    }

    @GetMapping("/socio/{idSocio}")
    public ResponseEntity<List<MultaEntity>> listarPorSocio(@PathVariable Long idSocio) {
        return ResponseEntity.ok(multaService.listarPorSocio(idSocio));
    }

    @PutMapping("/{id}/pagar")
    public ResponseEntity<MultaEntity> pagar(@PathVariable Long id) {
        return ResponseEntity.ok(multaService.pagarMulta(id));
    }
}
