package com.jrdm.BiblioCity.controller;

import com.jrdm.BiblioCity.entity.LibroEntity;
import com.jrdm.BiblioCity.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/libros")
public class LibroController {

    @Autowired
    private LibroService libroService;

    @GetMapping
    public ResponseEntity<List<LibroEntity>> listar() {
        return ResponseEntity.ok(libroService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LibroEntity> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(libroService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<LibroEntity> crear(@RequestBody LibroEntity libro) {
        return ResponseEntity.status(201).body(libroService.guardar(libro));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LibroEntity> actualizar(@PathVariable Long id, @RequestBody LibroEntity datos) {
        return ResponseEntity.ok(libroService.actualizar(id, datos));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        libroService.eliminar(id);
        return ResponseEntity.noContent().build(); // 204
    }
}
