package com.matheus.gestor_periodo.repository;

import com.matheus.gestor_periodo.dto.PeriodoReponseDTO;
import com.matheus.gestor_periodo.entity.Periodo;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Optional;

import static com.matheus.gestor_periodo.constants.QueriesConstant.BUSCAR_PERIODO;
import static com.matheus.gestor_periodo.constants.QueriesConstant.CADASTRAR_DATA_FINAL;
import static com.matheus.gestor_periodo.constants.QueriesConstant.CADASTRAR_DATA_INICIAL;

public interface PeriodoRepository extends JpaRepository<Periodo, Long> {

    @Query(value = BUSCAR_PERIODO, nativeQuery = true)
    Optional<PeriodoReponseDTO.Periodo> buscarPeriodo(Long id);

    @Transactional
    @Modifying
    @Query(value = CADASTRAR_DATA_INICIAL, nativeQuery = true)
    int atualizarDataInicial(Long id, LocalDate novaDataInicial);

    @Transactional
    @Modifying
    @Query(value = CADASTRAR_DATA_FINAL, nativeQuery = true)
    int atualizarDataFinal(long l, LocalDate novaDataFinal);
}

