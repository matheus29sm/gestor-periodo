package com.matheus.gestor_periodo.controllers;

import com.matheus.gestor_periodo.dto.apiResponse.ApiResponseDTO;
import com.matheus.gestor_periodo.dto.periodo.PeriodoRequestDTO;
import com.matheus.gestor_periodo.services.PeriodoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/periodo")
public class PeriodoController {

    @Autowired
    private PeriodoService periodoService;

    @Operation(summary = "Exibe o período atual",
            description = "Retorna uma string com as datas do período.")
    @GetMapping
    public ResponseEntity<ApiResponseDTO> exibePeriodo(){
        return periodoService.buscarPeriodo();
    }

    @Operation(summary = "Exibe a data inicial",
            description = "Retorna a data inicial do período.")
    @GetMapping("/inicial")
    public ResponseEntity<ApiResponseDTO> exibeDataInicial(){
        return periodoService.buscarDataInicial();
    }

    @Operation(summary = "Exibe a data final",
            description = "Retorna a data final do período.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Data final retornada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno")
    })
    @GetMapping("/final")
    public ResponseEntity<ApiResponseDTO> exibeDataFinal(){
        return periodoService.buscarDataFinal();
    }

    @Operation(summary = "Atualiza a data inicial",
            description = "Recebe uma nova data e atualiza a data inicial do período.")
    @PutMapping("/atualizarInicial")
    public ResponseEntity<ApiResponseDTO> atualizarDataInicial(
            @Valid @RequestBody PeriodoRequestDTO.AtualizarData request)
    {
       return periodoService.atualizarDataInicial(request);
    }

    @Operation(summary = "Atualiza a data final",
            description = "Recebe uma nova data e atualiza a data final do período.")
    @PutMapping("/atualizarFinal")
    public ResponseEntity<ApiResponseDTO> atualizarDataFinal(
            @Valid @RequestBody PeriodoRequestDTO.AtualizarData request)
    {
        return periodoService.atualizarDataFinal(request);
    }

    @Operation(summary = "Exibe quantidade de dias",
            description = "Calcula a quantidade de dias entre a data inicial e final do periodo.")
    @GetMapping("/quantidade")
    public ResponseEntity<ApiResponseDTO> exibeQuantidadeDias() {
        return periodoService.calcularDiasEntreDatas();
    }

    @Operation(summary = "Exibe distribuição de dias",
            description = "Conta quantos dias da semana existem entre a data inicial e final do periodo.")
    @GetMapping("/distribuicao")
    public ResponseEntity<ApiResponseDTO> exibeDistribuicaoDias(){
        return periodoService.contaDiasDaSemanaEntreDatas();
    }

    @Operation(summary = "Exibe período detalhado",
            description = "Retorna todas as informações geradas sobre o período.")
    @GetMapping("/detalhado")
    public ResponseEntity<ApiResponseDTO> exibePeriodoDetalhado() {
        return periodoService.buscarPeriodoDetalhado();
    }
}
