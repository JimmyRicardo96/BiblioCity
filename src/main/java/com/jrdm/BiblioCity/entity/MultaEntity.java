package com.jrdm.BiblioCity.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "multa")
public class MultaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private long idMulta;
    private float monto;
    @Enumerated(EnumType.STRING)
    private EstadoMulta estado;

    public enum EstadoMulta{
        PENDIENTE,
        PAGADA
    }

    @OneToOne
    @JoinColumn(name = "prestamo_id", nullable = false)
    @JsonIgnore
    private PrestamoEntity prestamo;
}
