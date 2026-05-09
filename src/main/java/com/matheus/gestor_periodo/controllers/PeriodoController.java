package com.matheus.gestor_periodo.controllers;

import com.matheus.gestor_periodo.dto.apiResponse.ApiResponseDTO;
import com.matheus.gestor_periodo.dto.periodo.PeriodoRequestDTO;
import com.matheus.gestor_periodo.services.PeriodoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/periodo")
public class PeriodoController {

    @Autowired
    private PeriodoService periodoService;

    @GetMapping
    public ResponseEntity<ApiResponseDTO> exibePeriodo(){
        return periodoService.buscarPeriodo();
    }

    @GetMapping("/inicial")
    public ResponseEntity<ApiResponseDTO> exibeDataInicial(){
        return periodoService.buscarDataInicial();
    }

    @GetMapping("/final")
    public ResponseEntity<ApiResponseDTO> exibeDataFinal(){
        return periodoService.buscarDataFinal();
    }

    @PutMapping("/atualizarInicial")
    public ResponseEntity<ApiResponseDTO> atualizarDataInicial(
            @Valid @RequestBody PeriodoRequestDTO.AtualizarData request)
    {
       return periodoService.atualizarDataInicial(request);
    }

    @PutMapping("/atualizarFinal")
    public ResponseEntity<ApiResponseDTO> atualizarDataFinal(
            @Valid @RequestBody PeriodoRequestDTO.AtualizarData request)
    {
        return periodoService.atualizarDataFinal(request);
    }

    @GetMapping("/quantidade")
    public ResponseEntity<ApiResponseDTO> exibeQuantidadeDias() {
        return periodoService.calcularDiasEntreDatas();
    }

    @GetMapping("/distribuicao")
    public ResponseEntity<ApiResponseDTO> exibeDistribuicaoDias(){
        return periodoService.contaDiasDaSemanaEntreDatas();
    }

}
