package br.com.alura.spring.data;

import br.com.alura.spring.data.exception.ValidacaoException;
import br.com.alura.spring.data.services.CrudCargoService;
import br.com.alura.spring.data.services.CrudFuncionarioService;
import br.com.alura.spring.data.services.CrudUnidadeTrabalhoService;
import br.com.alura.spring.data.services.RelatorioService;
import org.postgresql.util.PSQLException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {

	private final CrudCargoService crudCargoService;
	private final CrudFuncionarioService crudFuncionarioService;
	private final CrudUnidadeTrabalhoService crudUnidadeTrabalhoService;
	private final RelatorioService relatorioService;
	private Boolean controleFluxo = true;

	public SpringDataApplication(CrudCargoService crudCargoService, CrudFuncionarioService crudFuncionarioService, CrudUnidadeTrabalhoService crudUnidadeTrabalhoService,
								 RelatorioService relatorioService){
		this.crudCargoService = crudCargoService;
		this.crudFuncionarioService = crudFuncionarioService;
		this.crudUnidadeTrabalhoService = crudUnidadeTrabalhoService;
		this.relatorioService = relatorioService;
	}
	public static void main(String[] args) {
		try {
			SpringApplication.run(SpringDataApplication.class, args);
		}
		catch (ValidacaoException e){
			System.out.println("ERROR! " + e.getMessage());
		}

	}

	@Override
	public void run(String... args) throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.println("Bem vindo!");
		System.out.println("1.Cargos \n2.Funcionários \n3.Unidade de Trabalho \n4.Relatórios");
		int opcao = sc.nextInt();

		while(controleFluxo){
			switch (opcao){
				case 1:
					crudCargoService.inicial();
				case 2:
					crudFuncionarioService.inicial();
				case 3:
					crudUnidadeTrabalhoService.inicial();
				case 4:
					relatorioService.inicial();
				default:
					System.out.println("Opção Incorreta!");
					break;
			}
			System.out.println("Deseja continuar? \n 1.Sim \n 2.Não");
			Integer desejaContinuar = sc.nextInt();
			controleFluxo = (desejaContinuar == 1) ? true : false;
		}


	}
}
