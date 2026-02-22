package com.matheus.gestor_periodo.dto.apiResponse;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponseDTO {
    private int status;
    private String mensagem;
    private Object dados;
}
