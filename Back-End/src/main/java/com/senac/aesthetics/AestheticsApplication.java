package com.senac.aesthetics;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AestheticsApplication implements ApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(AestheticsApplication.class, args);

		System.out.println("\n############################");
		System.out.println("### Aplicativo Iniciado! ###");
		System.out.println("############################\n");

	}

	// REMOVER O MÉTODO ABAIXO E A INTERFACE ApplicationRunner NA PRODUÇÃO!
	@Override
	public void run(ApplicationArguments args) throws Exception {

		Boolean inserirDadosDeTeste = false;

		if (inserirDadosDeTeste) {
			// System.out.println("\nInserindo dados de teste...\n");

			// TODO: COLOCAR OS VALORES DE TESTE!

			// System.out.println("Valores de teste inseridos!\n");
		}
	}

}
