package com.matheus.gestor_periodo.dto;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class PeriodoReponseDTO {

    public interface Periodo {
        LocalDate getDataInicial();
        LocalDate getDataFinal();
    }

}
