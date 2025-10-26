package com.matheus.gestor_datas.controllers;

import com.matheus.gestor_datas.dto.DatasDTO;
import com.matheus.gestor_datas.services.DatasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/datas")
public class DatasController {

    @Autowired
    private DatasService datasService;

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

    @PutMapping("/atualiza")
    public String atualizaDataInicial(
            @RequestBody DatasDTO.AtualizarDataInicial request)
    {
       return datasService.atualizarDataInicial(request);
    }

    @GetMapping("/dias/quantidade")
    public Long buscaQuantidadeDias() {
        return datasService.calcularDiasEntreDatas();
    }

    @GetMapping("/dias/distribuicao")
    public Map<String, Long> buscaDiasSemana(){
        return datasService.contaDiasDaSemanaEntreDatas();
    }

}
