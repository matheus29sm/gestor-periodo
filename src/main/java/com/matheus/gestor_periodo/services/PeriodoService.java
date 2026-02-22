package com.matheus.gestor_periodo.services;

import com.matheus.gestor_periodo.dto.diasSemana.DiaSemanaResponseDTO;
import com.matheus.gestor_periodo.dto.periodo.PeriodoReponseDTO;
import com.matheus.gestor_periodo.dto.periodo.PeriodoRequestDTO;
import com.matheus.gestor_periodo.repository.PeriodoRepository;
import com.matheus.gestor_periodo.utils.DiasDaSemanaUtil;
import com.matheus.gestor_periodo.utils.FormataDataUtil;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class PeriodoService {

    @Autowired
    private PeriodoRepository periodoRepository;
    @Autowired
    private DiasDaSemanaUtil  diasDaSemanaUtil;
    @Autowired
    private FormataDataUtil formataDataUtil;

    public ResponseEntity<String> obterDataInicial() {
        String response = periodoRepository.buscarPeriodo(1l)
                .map(p -> formataDataUtil.formataData(p.getDataInicial()))
                .orElseThrow(() -> new ServiceException("Período não encontrado!"));
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<String> obterDataFinal() {
         String response = periodoRepository.buscarPeriodo(1l)
                .map(p -> formataDataUtil.formataData(p.getDataFinal()))
                .orElseThrow(() -> new ServiceException("Período não encontrado!"));

         return ResponseEntity.ok(response);
    }

    public ResponseEntity<String> obterPeriodo() {
        PeriodoReponseDTO.Periodo periodo = periodoRepository.buscarPeriodo(1l)
                .orElseThrow(() -> new ServiceException("Período não encontrado!"));

         String response = "DataInicial: " + formataDataUtil.formataData(periodo.getDataInicial())
                + ", " +
                "DataFinal: " + formataDataUtil.formataData(periodo.getDataFinal());

         return ResponseEntity.ok(response);
    }

    public ResponseEntity<String> atualizarDataInicial(PeriodoRequestDTO.AtualizarData request){
            LocalDate novaDataInicial = request.getNovaData();
        LocalDate dataFinal = periodoRepository.buscarPeriodo(1l)
                .orElseThrow(() -> new ServiceException("Período não encontrado!")).getDataFinal();

            if (novaDataInicial.isAfter(dataFinal)){
                throw new ServiceException("A data inicial não pode ser posterior à data final.");
            }

            periodoRepository.atualizarDataInicial(1l, novaDataInicial);

            String response ="A data inicial foi atualizada para: " + formataDataUtil.formataData(novaDataInicial);
            return ResponseEntity.ok(response);
    }

    public ResponseEntity<String> atualizarDataFinal(PeriodoRequestDTO.AtualizarData request){
        LocalDate novaDataFinal = request.getNovaData();

        periodoRepository.atualizarDataFinal(1l, novaDataFinal);

        String response = "A data final foi atualizada para: " + formataDataUtil.formataData(novaDataFinal);

        return ResponseEntity.ok(response);

    }

    public ResponseEntity<Long> calcularDiasEntreDatas() {
        PeriodoReponseDTO.Periodo periodo = periodoRepository.buscarPeriodo(1l)
                .orElseThrow(() -> new ServiceException("Período não encontrado!"));

        Long total = ChronoUnit.DAYS.between(periodo.getDataInicial(), periodo.getDataFinal());
        return ResponseEntity.ok(total);
    }


    public ResponseEntity<List<DiaSemanaResponseDTO.DiaSemana>> contaDiasDaSemanaEntreDatas() {
        PeriodoReponseDTO.Periodo periodo = periodoRepository.buscarPeriodo(1L)
                .orElseThrow(() -> new ServiceException("Período não encontrado!"));

        List<DiaSemanaResponseDTO.DiaSemana> dias = new ArrayList<>();

        for (LocalDate data = periodo.getDataInicial(); !data.isAfter(periodo.getDataFinal()); data = data.plusDays(1)) {
            String diaSemana = diasDaSemanaUtil.obterDiaSemanaEmPortugue(data.getDayOfWeek());

            Optional<DiaSemanaResponseDTO.DiaSemana> existente = dias.stream()
                    .filter(d -> d.getDia().equals(diaSemana))
                    .findFirst();

            if (existente.isPresent()) {
                existente.get().setQuantidade(existente.get().getQuantidade() + 1);
            } else {
                dias.add(new DiaSemanaResponseDTO.DiaSemana(diaSemana, 1l));
            }
        }

        return ResponseEntity.ok(dias);
    }

}
