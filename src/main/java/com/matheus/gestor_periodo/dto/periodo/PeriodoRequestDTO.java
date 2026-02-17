package com.matheus.gestor_periodo.dto.periodo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

public class PeriodoRequestDTO {

        @Data
        public static class AtualizarData{
            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
            private LocalDate novaData;
        }

}
