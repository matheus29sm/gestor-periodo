package com.matheus.gestor_datas.controllers;

import com.matheus.gestor_datas.services.DatasService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/datas")
public class DatasController {

    private final DatasService datasService;

    public DatasController(DatasService datasService) {
        this.datasService = datasService;
    }

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


    @PostMapping("/atualiza")
    public String atualizaDataInicial(@RequestBody Integer[] dataArray) {
        try{
            LocalDate novaDataInicial = LocalDate.of(dataArray[0], dataArray[1], dataArray[2]);
            datasService.setDataInicial(novaDataInicial);
            return "A data inicial foi atualizada para: " + novaDataInicial;
        } catch(IllegalArgumentException e){
            return "Error: " + e.getMessage();
        }
    }

    @GetMapping("/dias/quantidade")
    public Long buscaQuantidadeDias() {
        return datasService.calcularDiasEntreDatas();
    }

    @GetMapping("/dias/distribuicao")
    public Map<String, Long> buscaDiasSemana(){ return datasService.contaDiasDaSemanaEntreDatas();}

}
