package com.matheus.gestor_periodo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Gestor de Período API")
                        .version("1.0")
                        .description("Documentação da API de gestão de períodos."))
                .servers(List.of(
                        new Server().url("http://localhost:8080").description("Ambiente local")
                ));
    }
}

