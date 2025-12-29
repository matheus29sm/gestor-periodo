package com.matheus.gestor_periodo.controllers;

import com.matheus.gestor_periodo.dto.DatasDTO;
import com.matheus.gestor_periodo.services.PeriodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/periodo")
public class PeriodoController {

    @Autowired
    private PeriodoService datasService;

    @GetMapping
    public String exibeDatas(){
        return datasService.toString();
    }

    @GetMapping("/inicial")
    public LocalDate exibeInicial(){
        return datasService.getDataInicial();
    }

    @GetMapping("/final")
    public LocalDate exibeFinal(){
        return datasService.getDataFinal();
    }

    @PutMapping("/atualizarInicial")
    public String atualizaDataInicial(
            @RequestBody DatasDTO.AtualizarDataInicial request)
    {
       return datasService.atualizarDataInicial(request);
    }

    @GetMapping("/quantidade")
    public Long buscarQuantidadeDias() {
        return datasService.calcularDiasEntreDatas();
    }

    @GetMapping("/distribuicao")
    public Map<String, Long> buscarDistribuicaoDias(){
        return datasService.contaDiasDaSemanaEntreDatas();
    }

}
