package com.jrdm.BiblioCity.controller;

import com.jrdm.BiblioCity.entity.LibroEntity;
import com.jrdm.BiblioCity.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/libros")
public class LibroController {

    @Autowired
    private LibroService libroService;

    @GetMapping
    public List<LibroEntity> getLibros() {
        return libroService.listarTodos();
    }

    @PostMapping
    public LibroEntity saveLibro(@RequestBody LibroEntity l) {
        return libroService.guardar(l);
    }
}
