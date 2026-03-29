package com.matheus.gestor_periodo.integration;

import com.matheus.gestor_periodo.utils.FormataDataUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class PeriodoIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private FormataDataUtil formataDataUtil;

    private final static LocalDate DATAFINAL = LocalDate.now();

    @Nested
    @DisplayName("Cenários de Sucesso")
    class SucessoTests {

        @Test
        @DisplayName("Deve retornar período com sucesso")
        void deveRetornarPeriodoComSucesso() throws Exception {
            String response = "DataInicial: 26/08/2024 - "
                    + "DataFinal: " + formataDataUtil.formataData(DATAFINAL);

            mockMvc.perform(get("/periodo"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.status").value(200))
                    .andExpect(jsonPath("$.mensagem")
                            .value("Período obtido com sucesso"))
                    .andExpect(jsonPath("$.dados").value(response));
        }

        @Test
        @DisplayName("Deve retornar data inicial com sucesso")
        void deveRetornarDataInicial() throws Exception {
            String response = "26/08/2024";

            mockMvc.perform(get("/periodo/inicial"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.status").value(200))
                    .andExpect(jsonPath("$.mensagem")
                            .value("Data inicial obtida com sucesso"))
                    .andExpect(jsonPath("$.dados").value(response));
        }

        @Test
        @DisplayName("Deve retornar data final com sucesso")
        void deveRetornarDataFinal() throws Exception {
            String response = formataDataUtil.formataData(DATAFINAL);

            mockMvc.perform(get("/periodo/final"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.status").value(200))
                    .andExpect(jsonPath("$.mensagem")
                            .value("Data final obtida com sucesso"))
                    .andExpect(jsonPath("$.dados").value(response));
        }

        @Test
        @DisplayName("Deve atualizar a data inicial com sucesso")
        void deveAtualizaDataInicialComSucesso() throws Exception {
            String novaData = "28/03/2026";
            String request = String.format("""
                    {
                      "novaData": "%s"
                    }
                    """, novaData);

            mockMvc.perform(put("/periodo/atualizarInicial")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(request))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.status").value(200))
                    .andExpect(jsonPath("$.mensagem")
                            .value("A data inicial foi atualizada com sucesso"))
                    .andExpect(jsonPath("$.dados").value(novaData));
        }

        @Test
        @DisplayName("Deve atualizar a data final com sucesso")
        void deveAtualizaDataFinalComSucesso() throws Exception {
            String novaData = "28/03/2026";
            String request = String.format("""
                    {
                      "novaData": "%s"
                    }
                    """, novaData);

            mockMvc.perform(put("/periodo/atualizarFinal")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(request))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.status").value(200))
                    .andExpect(jsonPath("$.mensagem")
                            .value("A data final foi atualizada com sucesso"))
                    .andExpect(jsonPath("$.dados").value(novaData));
        }

        @Test
        void deveRetornarQuantidadeDiasComSucesso() throws Exception {
            mockMvc.perform(get("/periodo/quantidade"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.status").value(200))
                    .andExpect(jsonPath("$.mensagem")
                            .value("Total de dias calculado com sucesso"))
                    .andExpect(jsonPath("$.dados").isNotEmpty())
                    .andExpect(jsonPath("$.dados").isNumber());
        }

        @Test
        void deveRetornarDistribuicaoDiasComSucesso() throws Exception {
            mockMvc.perform(get("/periodo/distribuicao"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.status").value(200))
                    .andExpect(jsonPath("$.mensagem")
                            .value("Dias da semana calculados e distribuidos com sucesso"))
                    .andExpect(jsonPath("$.dados").isNotEmpty());
        }
    }

    @Nested
    @DisplayName("Cenários de Falha")
    class FalhaTests {

        @Test
        @DisplayName("Deve tentar atualizar a data inicial com nova data vazia")
        void deveTentarAtualizaDataInicialDataVazia() throws Exception {
            String request = """
               {
               "novaData": null
               }
               """;

            mockMvc.perform(put("/periodo/atualizarInicial")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(request))
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.status").value(400))
                    .andExpect(jsonPath("$.mensagem")
                            .value("A nova data não pode ser nula"));
        }

        @Test
        @DisplayName("Deve tentar atualizar a data inicial com formato de data inválido")
        void deveTentarAtualizaDataInicialComFormatoDataInvalido() throws Exception {
            String request = """
               {
               "novaData": "29-03-2026"
               }
               """;

            mockMvc.perform(put("/periodo/atualizarInicial")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(request))
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.status").value(400))
                    .andExpect(jsonPath("$.mensagem")
                            .value("O body da requisição é inválido ou está vazio"));
        }

        @Test
        @DisplayName("Deve tentar atualizar a data inicial com formato de data inválido")
        void deveTentarAtualizaDataInicialSemNovaData() throws Exception {
            mockMvc.perform(put("/periodo/atualizarInicial")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.status").value(400))
                    .andExpect(jsonPath("$.mensagem")
                            .value("O body da requisição é inválido ou está vazio"));
        }

        @Test
        @DisplayName("Deve tentar atualizar a data final com formato de data inválido")
        void deveTentarAtualizaDataFinalComFormatoDataInvalido() throws Exception {
            String request = """
               {
               "novaData": null
               }
               """;

            mockMvc.perform(put("/periodo/atualizarFinal")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(request))
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.status").value(400))
                    .andExpect(jsonPath("$.mensagem")
                            .value("A nova data não pode ser nula"));
        }

    }
}