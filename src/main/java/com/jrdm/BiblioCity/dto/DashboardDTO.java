package com.jrdm.BiblioCity.dto;

import com.jrdm.BiblioCity.entity.LibroEntity;
import com.jrdm.BiblioCity.entity.MultaEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class DashboardDTO {
    private List<LibroEntity> librosMasPrestados;
    private List<MultaEntity> multasPendientes;
}
