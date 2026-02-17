package com.matheus.gestor_periodo.constants;

public class QueriesConstant {

    public static final String BUSCAR_PERIODO = """
    SELECT p.DATA_INICIAL AS dataInicial,
        p.DATA_FINAL AS dataFinal
    FROM PERIODO p
    WHERE p.ID = ?1
    """;

    public static final String CADASTRAR_DATA_INICIAL = """
            UPDATE PERIODO
            SET DATA_INICIAL = ?2
            WHERE id = ?1;
            """;

    public static final String CADASTRAR_DATA_FINAL = """
            UPDATE PERIODO
            SET DATA_FINAL = ?2
            WHERE id = ?1;
            """;
}
