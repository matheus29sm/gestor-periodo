package com.matheus.gestor_periodo.dto.periodo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

public class PeriodoRequestDTO {

        @Data
        public static class AtualizarData{
            @Schema(
                    description = "Nova data para atualizar o período",
                    example = "17/05/2026",
                    type = "string",
                    pattern = "dd/MM/yyyy"
            )
            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
            @NotNull(message = "A nova data não pode ser nula")
            private LocalDate novaData;
        }

}
