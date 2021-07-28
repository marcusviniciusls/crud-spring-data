package br.com.alura.spring.data;

import br.com.alura.spring.data.services.CrudCargoService;
import br.com.alura.spring.data.services.CrudFuncionarioService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {

	private final CrudCargoService crudCargoService;
	private final CrudFuncionarioService crudFuncionarioService;
	private Boolean system = true;

	public SpringDataApplication(CrudCargoService crudCargoService, CrudFuncionarioService crudFuncionarioService){
		this.crudCargoService = crudCargoService;
		this.crudFuncionarioService = crudFuncionarioService;
	}
	public static void main(String[] args) {
		SpringApplication.run(SpringDataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner sc = new Scanner(System.in);
		crudFuncionarioService.inicial(sc);
	}
}
