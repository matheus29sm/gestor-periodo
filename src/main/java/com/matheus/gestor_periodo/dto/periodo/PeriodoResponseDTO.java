package com.matheus.gestor_periodo.dto.periodo;

import com.matheus.gestor_periodo.dto.diasSemana.DiaSemanaResponseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

public class PeriodoResponseDTO {

    public interface Periodo {
        LocalDate getDataInicial();
        LocalDate getDataFinal();
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Schema(description = "Informações detalhadas do período")
    public static class PeriodoDetalhado {
        @Schema(description = "Data inicial do período", example = "01/05/2026")
        private String dataInicial;

        @Schema(description = "Data final do período", example = "17/05/2026")
        private String dataFinal;

        @Schema(description = "Total de dias entre as datas", example = "16")
        private Long totalDias;

        @Schema(description = "Distribuição dos dias da semana entre as datas")
        private List<DiaSemanaResponseDTO.DiaSemana> distribuicaoDiasSemana;
    }

}
