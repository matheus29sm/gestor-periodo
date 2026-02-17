package com.matheus.gestor_periodo.controllers;

import com.matheus.gestor_periodo.dto.diasSemana.DiaSemanaResponseDTO;
import com.matheus.gestor_periodo.dto.periodo.PeriodoRequestDTO;
import com.matheus.gestor_periodo.services.PeriodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/periodo")
public class PeriodoController {

    @Autowired
    private PeriodoService periodoService;

    @GetMapping
    public String exibePeriodo(){
        return periodoService.obterPeriodo();
    }

    @GetMapping("/inicial")
    public String exibeDataInicial(){
        return periodoService.obterDataInicial();
    }

    @GetMapping("/final")
    public String exibeDataFinal(){
        return periodoService.obterDataFinal();
    }

    @PutMapping("/atualizarInicial")
    public String atualizaDataInicial(
            @RequestBody PeriodoRequestDTO.AtualizarData request)
    {
       return periodoService.atualizarDataInicial(request);
    }

    @PutMapping("/atualizarFinal")
    public String atualizaDataFinal(
            @RequestBody PeriodoRequestDTO.AtualizarData request)
    {
        return periodoService.atualizarDataFinal(request);
    }

    @GetMapping("/quantidade")
    public Long buscarQuantidadeDias() {
        return periodoService.calcularDiasEntreDatas();
    }

    @GetMapping("/distribuicao")
    public List<DiaSemanaResponseDTO.DiaSemana> buscarDistribuicaoDias(){
        return periodoService.contaDiasDaSemanaEntreDatas();
    }

}
