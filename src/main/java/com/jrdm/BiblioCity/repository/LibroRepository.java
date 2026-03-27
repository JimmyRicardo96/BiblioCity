package com.jrdm.BiblioCity.repository;

import com.jrdm.BiblioCity.entity.LibroEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepository extends JpaRepository<LibroEntity, Long> {
}
