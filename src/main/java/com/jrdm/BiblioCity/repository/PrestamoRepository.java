package com.jrdm.BiblioCity.repository;

import com.jrdm.BiblioCity.entity.PrestamoEntity;
import com.jrdm.BiblioCity.entity.SocioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrestamoRepository extends JpaRepository<PrestamoEntity, Long> {
    long countBySocioAndEstado(SocioEntity socio, PrestamoEntity.EstadoPrestamo estado);
    List<PrestamoEntity> findByEstado(PrestamoEntity.EstadoPrestamo estado);
}
