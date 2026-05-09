package com.matheus.gestor_periodo.helper;

import com.matheus.gestor_periodo.dto.diasSemana.DiaSemanaResponseDTO;
import com.matheus.gestor_periodo.utils.DiasDaSemanaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PeriodoHelper {

    @Autowired
    private DiasDaSemanaUtil diasDaSemanaUtil;

    public Long calcularTotalDias(LocalDate dataInicio, LocalDate dataFim) {
        return ChronoUnit.DAYS.between(dataInicio, dataFim);
    }

    public List<DiaSemanaResponseDTO.DiaSemana> calcularDistribuicao(LocalDate dataInicio, LocalDate dataFim) {
        List<DiaSemanaResponseDTO.DiaSemana> dias = new ArrayList<>();

        for (LocalDate data = dataInicio; !data.isAfter(dataFim); data = data.plusDays(1)) {
            String diaSemana = diasDaSemanaUtil.obterDiaSemanaEmPortugue(data.getDayOfWeek());

            Optional<DiaSemanaResponseDTO.DiaSemana> existente = dias.stream()
                    .filter(d -> d.getDia().equals(diaSemana))
                    .findFirst();

            if (existente.isPresent()) {
                existente.get().setQuantidade(existente.get().getQuantidade() + 1);
            } else {
                dias.add(new DiaSemanaResponseDTO.DiaSemana(diaSemana, 1L));
            }
        }
        return dias;
    }
}

