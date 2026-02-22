package com.matheus.gestor_periodo.controllers;

import com.matheus.gestor_periodo.dto.apiResponse.ApiResponseDTO;
import com.matheus.gestor_periodo.dto.periodo.PeriodoRequestDTO;
import com.matheus.gestor_periodo.services.PeriodoService;
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
        return periodoService.obterPeriodo();
    }

    @GetMapping("/inicial")
    public ResponseEntity<ApiResponseDTO> exibeDataInicial(){
        return periodoService.obterDataInicial();
    }

    @GetMapping("/final")
    public ResponseEntity<ApiResponseDTO> exibeDataFinal(){
        return periodoService.obterDataFinal();
    }

    @PutMapping("/atualizarInicial")
    public ResponseEntity<ApiResponseDTO> atualizaDataInicial(
            @RequestBody PeriodoRequestDTO.AtualizarData request)
    {
       return periodoService.atualizarDataInicial(request);
    }

    @PutMapping("/atualizarFinal")
    public ResponseEntity<ApiResponseDTO> atualizaDataFinal(
            @RequestBody PeriodoRequestDTO.AtualizarData request)
    {
        return periodoService.atualizarDataFinal(request);
    }

    @GetMapping("/quantidade")
    public ResponseEntity<ApiResponseDTO> buscarQuantidadeDias() {
        return periodoService.calcularDiasEntreDatas();
    }

    @GetMapping("/distribuicao")
    public ResponseEntity<ApiResponseDTO> buscarDistribuicaoDias(){
        return periodoService.contaDiasDaSemanaEntreDatas();
    }

}
