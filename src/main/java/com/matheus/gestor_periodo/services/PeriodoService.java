package com.matheus.gestor_periodo.services;

import com.matheus.gestor_periodo.dto.PeriodoReponseDTO;
import com.matheus.gestor_periodo.dto.PeriodoRequestDTO;
import com.matheus.gestor_periodo.repository.PeriodoRepository;
import com.matheus.gestor_periodo.utils.DiasDaSemanaUtil;
import com.matheus.gestor_periodo.utils.FormataDataUtil;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@Service
public class PeriodoService {

    @Autowired
    private PeriodoRepository periodoRepository;
    @Autowired
    private DiasDaSemanaUtil  diasDaSemanaUtil;
    @Autowired
    private FormataDataUtil formataDataUtil;

    public String obterDataInicial() {
        return periodoRepository.buscarPeriodo(1l)
                .map(p -> formataDataUtil.formataData(p.getDataInicial()))
                .orElseThrow(() -> new ServiceException("Período não encontrado!"));
    }

    public String obterDataFinal() {
        return periodoRepository.buscarPeriodo(1l)
                .map(p -> formataDataUtil.formataData(p.getDataFinal()))
                .orElseThrow(() -> new ServiceException("Período não encontrado!"));
    }

    public String obterPeriodo() {
        PeriodoReponseDTO.Periodo periodo = periodoRepository.buscarPeriodo(1l)
                .orElseThrow(() -> new ServiceException("Período não encontrado!"));

        return  "DataInicial: " + formataDataUtil.formataData(periodo.getDataInicial())
                + ", " +
                "DataFinal: " + formataDataUtil.formataData(periodo.getDataFinal());
    }

    public String atualizarDataInicial(PeriodoRequestDTO.AtualizarData request){
            LocalDate novaDataInicial = request.getNovaData();
        LocalDate dataFinal = periodoRepository.buscarPeriodo(1l)
                .orElseThrow(() -> new ServiceException("Período não encontrado!")).getDataFinal();

            if (novaDataInicial.isAfter(dataFinal)){
                throw new ServiceException("A data inicial não pode ser posterior à data final.");
            }

            periodoRepository.atualizarDataInicial(1l, novaDataInicial);

            return "A data inicial foi atualizada para: " + formataDataUtil.formataData(novaDataInicial);

    }

    public String atualizarDataFinal(PeriodoRequestDTO.AtualizarData request){
        LocalDate novaDataFinal = request.getNovaData();

        periodoRepository.atualizarDataFinal(1l, novaDataFinal);

        return "A data final foi atualizada para: " + formataDataUtil.formataData(novaDataFinal);

    }

    public long calcularDiasEntreDatas() {
        PeriodoReponseDTO.Periodo periodo = periodoRepository.buscarPeriodo(1l)
                .orElseThrow(() -> new ServiceException("Período não encontrado!"));

        return ChronoUnit.DAYS.between(periodo.getDataInicial(), periodo.getDataFinal());
    }


    public Map<String, Long> contaDiasDaSemanaEntreDatas() {
        PeriodoReponseDTO.Periodo periodo = periodoRepository.buscarPeriodo(1l)
                .orElseThrow(() -> new ServiceException("Período não encontrado!"));

        Map<String, Long> dias = new HashMap<>();

        for (LocalDate data = periodo.getDataInicial(); !data.isAfter(periodo.getDataFinal()); data = data.plusDays(1)) {
            String diasSemana = diasDaSemanaUtil.obterDiaSemanaEmPortugue(data.getDayOfWeek());
            dias.put(diasSemana, dias.getOrDefault(diasSemana, 0L) + 1);
        }

        return dias;
    }
}
