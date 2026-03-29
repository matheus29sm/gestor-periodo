package com.matheus.gestor_periodo.dto.periodo;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

public class PeriodoRequestDTO {

        @Data
        public static class AtualizarData{
            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
            @NotNull(message = "A nova data não pode ser nula")
            private LocalDate novaData;
        }

}
