package com.matheus.gestor_periodo.unit.helper;

import com.matheus.gestor_periodo.helper.PeriodoHelper;
import com.matheus.gestor_periodo.utils.DiasDaSemanaUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


@ExtendWith(MockitoExtension.class)
class PeriodoHelperTest {

    @InjectMocks
    private PeriodoHelper periodoHelper;
    @Spy
    private DiasDaSemanaUtil diasDaSemanaUtil;

    private final LocalDate INICIO = LocalDate.of(2026, 3, 1);
    private final LocalDate FIM = LocalDate.of(2026, 3, 11);

    @Test
    @DisplayName("Deve calcular total de dias corretamente")
    void deveCalcularTotalDias() {
        Long quantidadeEsperada = 10L;

        Long quantidade = periodoHelper.calcularTotalDias(INICIO, FIM);

        assertEquals(quantidadeEsperada, quantidade);
    }

    @Test
    @DisplayName("Deve distribuir corretamente dias da semana no intervalo")
    void deveDistribuirDiasDaSemana() {
        var distribuicao = periodoHelper.calcularDistribuicao(INICIO, FIM);

        assertEquals(7, distribuicao.size());
        assertFalse(distribuicao.isEmpty());
        assertTrue(distribuicao.stream().anyMatch(d -> "Segunda-feira".equals(d.getDia())));
    }

    }