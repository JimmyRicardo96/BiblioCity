package com.jrdm.BiblioCity.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Data
@Entity
@Table(name = "libro")
public class LibroEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idLibro;
    @Column(nullable = false, unique = true)
    private String isbn;
    @Column(nullable = false)
    private String titulo;
    private String autor;
    private String editorial;
    private String categoria;
    @Column(nullable = false)
    private int ejemplaresDisponibles;
    @Enumerated(EnumType.STRING)
    private EstadoLibro estado;

    public enum EstadoLibro {
        ACTIVO,
        INACTIVO
    }

    @OneToMany(mappedBy = "libro")
    private List<PrestamoEntity> prestamos;


}
