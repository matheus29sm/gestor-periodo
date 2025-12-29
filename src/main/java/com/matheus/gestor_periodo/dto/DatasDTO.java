package com.matheus.gestor_periodo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

public class DatasDTO {

        @Data
        public static class AtualizarDataInicial{
            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
            private LocalDate novaData;
        }
}
