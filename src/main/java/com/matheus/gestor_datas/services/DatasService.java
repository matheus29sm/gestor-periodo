package com.matheus.gestor_datas.services;

import com.matheus.gestor_datas.dto.DatasDTO;
import com.matheus.gestor_datas.utils.DiasDaSemanaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;


@Service
public class DatasService {

    @Autowired
    private DiasDaSemanaUtil  diasDaSemanaUtil;

    private LocalDate dataInicial = LocalDate.of(2024, 8, 26);
    private LocalDate dataFinal = LocalDate.now();

    public LocalDate getDataInicial() {
        return this.dataInicial;
    }

    public LocalDate getDataFinal() {
        return this.dataFinal;
    }

    public void setDataInicial(LocalDate dataInicial){
        if (dataInicial.isAfter(this.dataFinal)) {
            throw new IllegalArgumentException("A data inicial não pode ser posterior à data final.");
        }
        this.dataInicial = dataInicial;
    }

    public long calcularDiasEntreDatas() {
        return ChronoUnit.DAYS.between(dataInicial, dataFinal);
    }
    
    public void atualizaDataFinal(){
        this.dataFinal = LocalDate.now();
    }

    public String atualizarDataInicial(DatasDTO.AtualizarDataInicial request){
        try{
            LocalDate novaDataInicial = request.getNovaData();
            setDataInicial(novaDataInicial);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return "A data inicial foi atualizada para: " + novaDataInicial.format(formatter);
        } catch(IllegalArgumentException e){
            return "Error: " + e.getMessage();
        }
    }


    public String formataData(LocalDate data){
        return data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    @Override
    public String toString() {
        return  "DataInicial: " + formataData(dataInicial)
                + ", " +
                "DataFinal: " + formataData(dataFinal);
    }

    public Map<String, Long> contaDiasDaSemanaEntreDatas() {
        Map<String, Long> dias = new HashMap<>();
        for (LocalDate data = dataInicial; !data.isAfter(dataFinal); data = data.plusDays(1)) {
            String diasSemana = diasDaSemanaUtil.obterDiaSemanaEmPortugue(data.getDayOfWeek());
            dias.put(diasSemana, dias.getOrDefault(diasSemana, 0L) + 1);
        }
        return dias;
    }
}
