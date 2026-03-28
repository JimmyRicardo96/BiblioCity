package com.jrdm.BiblioCity.repository;

import com.jrdm.BiblioCity.entity.MultaEntity;
import com.jrdm.BiblioCity.entity.SocioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MultaRepository extends JpaRepository<MultaEntity, Long> {
    boolean existsByPrestamoSocioAndEstado(SocioEntity socio, MultaEntity.EstadoMulta estado);
    List<MultaEntity> findByEstado(MultaEntity.EstadoMulta estado);

    List<MultaEntity> findByPrestamoSocioIdSocio(Long idSocio);
}
