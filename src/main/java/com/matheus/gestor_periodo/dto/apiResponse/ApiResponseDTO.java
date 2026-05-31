package com.matheus.gestor_periodo.dto.apiResponse;

import com.matheus.gestor_periodo.dto.diasSemana.DiaSemanaResponseDTO;
import com.matheus.gestor_periodo.dto.periodo.PeriodoResponseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Resposta padrão da API")
public class ApiResponseDTO {
    @Schema(description = "Código de status da operação")
    private int status;

    @Schema(description = "Mensagem de retorno")
    private String mensagem;

    @Schema(description = "Dados adicionais da resposta",
            oneOf = {String.class,
                    DiaSemanaResponseDTO.DiaSemana.class,
                    PeriodoResponseDTO.PeriodoDetalhado.class})
    private Object dados;
}
