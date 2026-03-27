package com.jrdm.BiblioCity.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Data
@Entity
@Table(name = "socio")
public class SocioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idSocio;
    @Column(unique = true, nullable = false)
    private String identificacion;
    private String nombres;
    private String apellidos;
    private String telefono;
    @Enumerated(EnumType.STRING)
    private EstadoSocio estado;

    public enum EstadoSocio {
        ACTIVO,
        INACTIVO
    }

    @OneToMany(mappedBy = "socio")
    @JsonIgnore
    private List<PrestamoEntity> prestamos;

}
