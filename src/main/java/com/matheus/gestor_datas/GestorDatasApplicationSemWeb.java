package com.matheus.gestor_datas;

import com.matheus.gestor_datas.services.DatasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class GestorDatasApplicationSemWeb implements CommandLineRunner {

	@Autowired
	private DatasService datasService;

	public static void main(String[] args) {
		SpringApplication.run(GestorDatasApplicationSemWeb.class, args);
	}

	public void run(String[] args) {
		String dataInicial = String.valueOf(datasService.getDataInicial());
		String dataFinal = String.valueOf(datasService.getDataFinal());
		String quantidadeDias = String.valueOf(datasService.calcularDiasEntreDatas());
		String datas = datasService.toString();

		System.out.println(dataInicial);
		System.out.println(dataFinal);
		System.out.println(quantidadeDias + " Dias");
		System.out.println(datas);
		System.out.println(datasService.contaDiasDaSemanaEntreDatas());

		datasService.setDataInicial(LocalDate.of(1999,05,29));
		dataInicial = String.valueOf(datasService.getDataInicial());
		quantidadeDias = String.valueOf(datasService.calcularDiasEntreDatas());
		datas = datasService.toString();

		System.out.println(dataInicial);
		System.out.println(quantidadeDias + " Dias");
		System.out.println(datas);
		System.out.println(datasService.contaDiasDaSemanaEntreDatas());

		System.exit(0);
	}

}
