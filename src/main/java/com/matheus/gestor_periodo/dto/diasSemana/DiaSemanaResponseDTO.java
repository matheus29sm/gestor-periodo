package com.matheus.gestor_periodo.dto.diasSemana;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class DiaSemanaResponseDTO {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DiaSemana {
        private String dia;
        private Long quantidade;
    }

}
