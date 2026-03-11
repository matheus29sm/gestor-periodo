package com.matheus.gestor_periodo.services;

import com.matheus.gestor_periodo.dto.apiResponse.ApiResponseDTO;
import com.matheus.gestor_periodo.dto.periodo.PeriodoRequestDTO;
import org.springframework.http.ResponseEntity;

public interface PeriodoService {

    ResponseEntity<ApiResponseDTO> obterPeriodo();

    ResponseEntity<ApiResponseDTO> obterDataInicial();

    ResponseEntity<ApiResponseDTO> obterDataFinal();

    ResponseEntity<ApiResponseDTO> atualizarDataInicial(PeriodoRequestDTO.AtualizarData request);

    ResponseEntity<ApiResponseDTO> atualizarDataFinal(PeriodoRequestDTO.AtualizarData request);

    ResponseEntity<ApiResponseDTO> calcularDiasEntreDatas();

    ResponseEntity<ApiResponseDTO> contaDiasDaSemanaEntreDatas();
}
