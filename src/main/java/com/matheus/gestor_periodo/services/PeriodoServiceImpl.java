package com.matheus.gestor_periodo.services;

import com.matheus.gestor_periodo.dto.apiResponse.ApiResponseDTO;
import com.matheus.gestor_periodo.dto.diasSemana.DiaSemanaResponseDTO;
import com.matheus.gestor_periodo.dto.periodo.PeriodoResponseDTO;
import com.matheus.gestor_periodo.dto.periodo.PeriodoRequestDTO;
import com.matheus.gestor_periodo.helper.PeriodoHelper;
import com.matheus.gestor_periodo.repository.PeriodoRepository;
import com.matheus.gestor_periodo.utils.FormataDataUtil;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PeriodoServiceImpl implements PeriodoService{

    @Autowired
    private PeriodoHelper periodoHelper;
    @Autowired
    private PeriodoRepository periodoRepository;
    @Autowired
    private FormataDataUtil formataDataUtil;

    @Override
    public ResponseEntity<ApiResponseDTO> buscarPeriodo() {
        var periodo = buscarPeriodoPadrao();

        String response = "DataInicial: " + formataDataUtil.formataData(periodo.getDataInicial())
                + " - " +
                "DataFinal: " + formataDataUtil.formataData(periodo.getDataFinal());

        return ResponseEntity.ok(new ApiResponseDTO(200, "Período obtido com sucesso", response));
    }

    @Override
    public ResponseEntity<ApiResponseDTO> buscarDataInicial() {
        LocalDate dataInicial = buscarPeriodoPadrao().getDataInicial();
        String response =  formataDataUtil.formataData(dataInicial);

        return ResponseEntity.ok(new ApiResponseDTO(200, "Data inicial obtida com sucesso", response));
    }

    @Override
    public ResponseEntity<ApiResponseDTO> buscarDataFinal() {
        LocalDate dataFinal =  buscarPeriodoPadrao().getDataFinal();
        String response =  formataDataUtil.formataData(dataFinal);

         return ResponseEntity.ok(new ApiResponseDTO(200, "Data final obtida com sucesso", response));
    }

    @Override
    public ResponseEntity<ApiResponseDTO> atualizarDataInicial(PeriodoRequestDTO.AtualizarData request){
        LocalDate novaDataInicial = request.getNovaData();
        LocalDate dataFinal =  buscarPeriodoPadrao().getDataFinal();

            if (novaDataInicial.isAfter(dataFinal)){
                throw new ServiceException("A data inicial não pode ser posterior à data final.");
            }

            periodoRepository.atualizarDataInicial(1L, novaDataInicial);

            String response = formataDataUtil.formataData(novaDataInicial);

        return ResponseEntity.ok(new ApiResponseDTO(200, "A data inicial foi atualizada com sucesso", response));
    }

    @Override
    public ResponseEntity<ApiResponseDTO> atualizarDataFinal(PeriodoRequestDTO.AtualizarData request){
        LocalDate novaDataFinal = request.getNovaData();
        LocalDate dataInicial = buscarPeriodoPadrao().getDataInicial();

        if (novaDataFinal.isBefore((dataInicial))){
            throw new ServiceException("A data final não pode ser anterior à data inicial.");
        }

        periodoRepository.atualizarDataFinal(1L, novaDataFinal);

        String response = formataDataUtil.formataData(novaDataFinal);

        return ResponseEntity.ok(new ApiResponseDTO(200, "A data final foi atualizada com sucesso", response));

    }

    @Override
    public ResponseEntity<ApiResponseDTO> calcularDiasEntreDatas() {
        var periodo = buscarPeriodoPadrao();

        Long total = periodoHelper.calcularTotalDias(periodo.getDataInicial(), periodo.getDataFinal());

        return ResponseEntity.ok(new ApiResponseDTO(200, "Total de dias calculado com sucesso", total));
    }

    @Override
    public ResponseEntity<ApiResponseDTO> contaDiasDaSemanaEntreDatas() {
        var periodo = buscarPeriodoPadrao();

        List<DiaSemanaResponseDTO.DiaSemana> dias =
                periodoHelper.calcularDistribuicao(periodo.getDataInicial(), periodo.getDataFinal());

        return ResponseEntity.ok(new ApiResponseDTO(200, "Dias da semana calculados e distribuidos com sucesso", dias));
    }

    @Override
    public ResponseEntity<ApiResponseDTO> buscarPeriodoDetalhado() {
        var periodo = buscarPeriodoPadrao();

        String dataInicial = formataDataUtil.formataData(periodo.getDataInicial());
        String dataFinal = formataDataUtil.formataData(periodo.getDataFinal());

        Long totalDias = periodoHelper.calcularTotalDias(periodo.getDataInicial(), periodo.getDataFinal());

        List<DiaSemanaResponseDTO.DiaSemana> distribuicao =
                periodoHelper.calcularDistribuicao(periodo.getDataInicial(), periodo.getDataFinal());

        PeriodoResponseDTO.PeriodoDetalhado response = new PeriodoResponseDTO.PeriodoDetalhado(dataInicial, dataFinal, totalDias, distribuicao);

        return ResponseEntity.ok(new ApiResponseDTO(200, "Período detalhado obtido com sucesso", response));
    }

    private PeriodoResponseDTO.Periodo buscarPeriodoPadrao() {
        return periodoRepository.buscarPeriodo(1L)
                .orElseThrow(() -> new ServiceException("Período não encontrado!"));
    }

}
