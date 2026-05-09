package com.matheus.gestor_periodo.dto.periodo;

import com.matheus.gestor_periodo.dto.diasSemana.DiaSemanaResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

public class PeriodoReponseDTO {

    public interface Periodo {
        LocalDate getDataInicial();
        LocalDate getDataFinal();
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PeriodoDetalhado {
        private String dataInicial;
        private String dataFinal;
        private Long totalDias;
        private List<DiaSemanaResponseDTO.DiaSemana> distribuicaoDiasSemana;
    }

}
