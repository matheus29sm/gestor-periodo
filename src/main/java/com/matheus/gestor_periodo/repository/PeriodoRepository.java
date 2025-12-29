package com.matheus.gestor_periodo.repository;

import com.matheus.gestor_periodo.entity.Periodo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeriodoRepository extends JpaRepository<Periodo, Long> {
}

