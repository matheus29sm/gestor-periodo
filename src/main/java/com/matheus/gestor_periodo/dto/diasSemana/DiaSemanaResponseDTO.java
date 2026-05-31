package com.matheus.gestor_periodo.dto.diasSemana;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class DiaSemanaResponseDTO {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Schema(description = "Distribuição de dias da semana")
    public static class DiaSemana {
        @Schema(description = "Nome do dia da semana", example = "Segunda-feira")
        private String dia;

        @Schema(description = "Quantidade de ocorrências desse dia", example = "3")
        private Long quantidade;
    }

}
