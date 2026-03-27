package com.jrdm.BiblioCity.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
@Data
@Entity
@Table(name = "prestamo")
public class PrestamoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idPrestamo;
    @Column(nullable = false)
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucionEstimada;
    private LocalDate fechaDevolucionReal;
    @Enumerated(EnumType.STRING)
    private EstadoPrestamo estado;

    public enum EstadoPrestamo {
        ACTIVO,
        DEVUELTO,
        VENCIDO
    }

    @ManyToOne
    @JoinColumn(name = "socio_id", nullable = false)
    private SocioEntity socio;

    @ManyToOne
    @JoinColumn(name = "libro_id", nullable = false)
    private LibroEntity libro;

    @OneToOne(mappedBy = "prestamo")
    private MultaEntity multa;
}
