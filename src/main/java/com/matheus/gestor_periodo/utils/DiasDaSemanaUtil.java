package com.matheus.gestor_periodo.utils;

import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

import static com.matheus.gestor_periodo.constants.DiasDaSemanaConstant.DOMINGO;
import static com.matheus.gestor_periodo.constants.DiasDaSemanaConstant.QUARTA;
import static com.matheus.gestor_periodo.constants.DiasDaSemanaConstant.QUINTA;
import static com.matheus.gestor_periodo.constants.DiasDaSemanaConstant.SABADO;
import static com.matheus.gestor_periodo.constants.DiasDaSemanaConstant.SEGUNDA;
import static com.matheus.gestor_periodo.constants.DiasDaSemanaConstant.SEXTA;
import static com.matheus.gestor_periodo.constants.DiasDaSemanaConstant.TERCA;

@Component
public class DiasDaSemanaUtil {

    public String  obterDiaSemanaEmPortugue(DayOfWeek diaSemana) {
        switch (diaSemana) {
            case MONDAY: return SEGUNDA;
            case TUESDAY: return TERCA;
            case WEDNESDAY: return QUARTA;
            case THURSDAY: return QUINTA;
            case FRIDAY: return SEXTA;
            case SATURDAY: return SABADO;
            case SUNDAY: return DOMINGO;
            default: throw new IllegalArgumentException("Dia inválido: " + diaSemana);
        }
    }
}
