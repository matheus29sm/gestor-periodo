package com.matheus.gestor_periodo.dto.periodo;

import java.time.LocalDate;

public class PeriodoReponseDTO {

    public interface Periodo {
        LocalDate getDataInicial();
        LocalDate getDataFinal();
    }

}
