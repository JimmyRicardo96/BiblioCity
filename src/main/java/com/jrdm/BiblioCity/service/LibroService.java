package com.jrdm.BiblioCity.service;

import com.jrdm.BiblioCity.entity.LibroEntity;
import com.jrdm.BiblioCity.entity.PrestamoEntity;
import com.jrdm.BiblioCity.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;

    public List<LibroEntity> listarTodos() {
        return libroRepository.findAll();
    }

    public LibroEntity guardar(LibroEntity libro) {
        return libroRepository.save(libro);
    }

    public void eliminar(Long id) {
        LibroEntity libro = libroRepository.findById(id).orElseThrow();
        if (libro.getPrestamos().stream().anyMatch(p -> p.getEstado() == PrestamoEntity.EstadoPrestamo.ACTIVO)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El libro no se puede eliminar, tiene préstamos activos");
        }
        libroRepository.deleteById(id);

    }

}
