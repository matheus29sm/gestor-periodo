package com.matheus.gestor_periodo.unit.services;

import com.matheus.gestor_periodo.dto.apiResponse.ApiResponseDTO;
import com.matheus.gestor_periodo.dto.periodo.PeriodoReponseDTO;
import com.matheus.gestor_periodo.dto.periodo.PeriodoRequestDTO;
import com.matheus.gestor_periodo.repository.PeriodoRepository;
import com.matheus.gestor_periodo.services.PeriodoServiceImpl;
import com.matheus.gestor_periodo.utils.DiasDaSemanaUtil;
import com.matheus.gestor_periodo.utils.FormataDataUtil;
import org.hibernate.service.spi.ServiceException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PeriodoServiceImplTest {

    @InjectMocks
    private PeriodoServiceImpl periodoService;
    @Mock
    private PeriodoRepository periodoRepository;
    @Mock
    private DiasDaSemanaUtil diasDaSemanaUtil;
    @Mock
    private FormataDataUtil formataDataUtil;
    @Mock
    private PeriodoReponseDTO.Periodo periodo;
    @Mock
    private PeriodoRequestDTO.AtualizarData atualizarData;

    private final LocalDate INICIO = LocalDate.of(2026, 3, 1);
    private final LocalDate FIM = LocalDate.of(2026, 3, 11);


    @Nested
    class ObterPeriodoTests {

        @Test
        @DisplayName("Deve obter período com sucesso")
        void deveObterPeriodoComSucesso() {
            when(periodoRepository.buscarPeriodo(1L)).thenReturn(Optional.of(periodo));
            when(periodo.getDataInicial()).thenReturn(INICIO);
            when(periodo.getDataFinal()).thenReturn(FIM);

            ResponseEntity<ApiResponseDTO> response = periodoService.obterPeriodo();

            assertEquals(200, response.getBody().getStatus());
            assertTrue(response.getBody().getMensagem().contains("Período obtido com sucesso"));
        }

        @Test
        @DisplayName("Deve lançar exceção quando período não encontrado")
        void deveLancarExcecaoQuandoPeriodoNaoEncontradoEmObterPeriodo() {
            when(periodoRepository.buscarPeriodo(1L)).thenReturn(Optional.empty());

            ServiceException serviceException = assertThrows(ServiceException.class,
                    () -> periodoService.obterPeriodo());

            assertEquals("Período não encontrado!", serviceException.getMessage());
        }

    }

    @Nested
    class ObterDatasTests {

        @Test
        @DisplayName("Deve obter data inicial com sucesso")
        void deveObterDataInicialComSucesso() {
            when(periodoRepository.buscarPeriodo(1L)).thenReturn(Optional.of(periodo));
            when(periodo.getDataInicial()).thenReturn(INICIO);
            when(formataDataUtil.formataData(INICIO)).thenReturn("01/03/2026");

            ResponseEntity<ApiResponseDTO> response = periodoService.obterDataInicial();

            assertTrue(response.getBody().getMensagem().contains("Data inicial obtida com sucesso"));
            assertEquals("01/03/2026", response.getBody().getDados());
        }

        @Test
        @DisplayName("Deve lançar exceção quando período não encontrado")
        void deveObterDataFinalComSucesso() {
            when(periodoRepository.buscarPeriodo(1L)).thenReturn(Optional.of(periodo));
            when(periodo.getDataFinal()).thenReturn(FIM);
            when(formataDataUtil.formataData(FIM)).thenReturn("11/03/2026");

            ResponseEntity<ApiResponseDTO> response = periodoService.obterDataFinal();

            assertTrue(response.getBody().getMensagem().contains("Data final obtida com sucesso"));
            assertEquals("11/03/2026", response.getBody().getDados());

        }

    }

    @Nested
    @DisplayName("Atualização de datas")
    class AtualizarDataTests {

        @Test
        @DisplayName("Deve atualizar data inicial com sucesso")
        void deveAtualizarDataInicialComSucesso() {
            LocalDate novaData = LocalDate.of(2026, 03, 01);

            when(periodoRepository.buscarPeriodo(1L)).thenReturn(Optional.of(periodo));
            when(periodo.getDataFinal()).thenReturn(FIM);
            when(atualizarData.getNovaData()).thenReturn(novaData);
            when(formataDataUtil.formataData(novaData)).thenReturn("01/03/2026");

            ResponseEntity<ApiResponseDTO> response = periodoService.atualizarDataInicial(atualizarData);

            assertTrue(response.getBody().getMensagem().contains("A data inicial foi atualizada com sucesso"));
            assertEquals("01/03/2026", response.getBody().getDados());
            verify(periodoRepository).atualizarDataInicial(1L, novaData);
        }

        @Test
        @DisplayName("Deve lançar exceção quando nova data inicial é posterior à data final")
        void deveLancarExcecaoQuandoNovaDataInicialPosteriorADataFinal() {
            LocalDate novaData = LocalDate.of(2026, 05, 31);

            when(periodoRepository.buscarPeriodo(1L)).thenReturn(Optional.of(periodo));
            when(periodo.getDataFinal()).thenReturn(FIM);
            when(atualizarData.getNovaData()).thenReturn(novaData);

            ServiceException serviceException = assertThrows(ServiceException.class,
                    () -> periodoService.atualizarDataInicial(atualizarData));

            assertEquals("A data inicial não pode ser posterior à data final.", serviceException.getMessage());
        }

        @Test
        @DisplayName("Deve atualizar data final com sucesso")
        void deveAtualizarDataFinalComSucesso() {
            LocalDate novaData = LocalDate.of(2026, 05, 31);

            when(atualizarData.getNovaData()).thenReturn(novaData);
            when(formataDataUtil.formataData(novaData)).thenReturn("31/05/2026");

            ResponseEntity<ApiResponseDTO> response = periodoService.atualizarDataFinal(atualizarData);

            assertTrue(response.getBody().getMensagem().contains("A data final foi atualizada com sucesso"));
            assertEquals("31/05/2026", response.getBody().getDados());
            verify(periodoRepository).atualizarDataFinal(1L, novaData);
        }

    }
    @Nested
    class CalculoIntervaloTests {

        @Test
        @DisplayName("Deve calcular dias entre as datas do período com sucesso")
        void deveCalcularDiasEntreDatasComSucesso() {
            when(periodoRepository.buscarPeriodo(1L)).thenReturn(Optional.of(periodo));
            when(periodo.getDataInicial()).thenReturn(INICIO);
            when(periodo.getDataFinal()).thenReturn(FIM);

            ResponseEntity<ApiResponseDTO> response = periodoService.calcularDiasEntreDatas();

            assertTrue(response.getBody().getMensagem().contains("Total de dias calculado com sucesso"));
            assertEquals(10L, response.getBody().getDados());
        }

    }

    @Nested
    class DistribuicaoDiasSemanaTests {

        @Test
        @DisplayName("Deve gerar a distribuição dias da semana entre datas do período com sucesso")
        void deveGerarDistribuicaoDiasDaSemanaEntreDatasComSucesso() {
            when(periodoRepository.buscarPeriodo(1L)).thenReturn(Optional.of(periodo));
            when(periodo.getDataInicial()).thenReturn(INICIO);
            when(periodo.getDataFinal()).thenReturn(FIM);
            when(diasDaSemanaUtil.obterDiaSemanaEmPortugue(any())).thenReturn("Segunda-feira");

            ResponseEntity<ApiResponseDTO> response = periodoService.contaDiasDaSemanaEntreDatas();

            List<?> dias = (List<?>) response.getBody().getDados();
            assertFalse(dias.isEmpty());
            assertTrue(response.getBody().getMensagem().contains("Dias da semana calculados e distribuidos com sucesso"));
        }
    }
}