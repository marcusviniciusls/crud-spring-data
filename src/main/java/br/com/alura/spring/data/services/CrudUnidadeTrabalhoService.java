package br.com.alura.spring.data.services;


import br.com.alura.spring.data.entities.Cargo;
import br.com.alura.spring.data.entities.Funcionario;
import br.com.alura.spring.data.entities.UnidadeTrabalho;
import br.com.alura.spring.data.exception.NullSalvarBanco;
import br.com.alura.spring.data.exception.ValidacaoException;
import br.com.alura.spring.data.repository.CargoRepository;
import br.com.alura.spring.data.repository.FuncionarioRepository;
import br.com.alura.spring.data.repository.UnidadeTrabalhoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.Scanner;

@Service
public class CrudUnidadeTrabalhoService {

    private Boolean controleFluxo = true;
    private final UnidadeTrabalhoRepository unidadeTrabalhoRepository;
    private final FuncionarioRepository funcionarioRepository;
    private final CargoRepository cargoRepository;

    public CrudUnidadeTrabalhoService(UnidadeTrabalhoRepository unidadeTrabalhoRepository, FuncionarioRepository funcionarioRepository, CargoRepository cargoRepository) {
        this.unidadeTrabalhoRepository = unidadeTrabalhoRepository;
        this.funcionarioRepository = funcionarioRepository;
        this.cargoRepository = cargoRepository;
    }

    Scanner sc = new Scanner(System.in);

    public void inicial(){

        System.out.println("Bem vindo ao cadastro de Unidades de Trabalho");
        while(controleFluxo){
            System.out.println("Selecione as opções abaixo:");
            System.out.println("1.Cadastrar \n2.Atualizar \n3.Visualizar todas as Unidade de Negócio \n4.Deletar Por Id");
            int opcao = sc.nextInt();
            switch (opcao){
                case 1:
                    salvar();
                    break;
                case 2:
                    atualizar();
                    break;
                case 3:
                    visualizarTodasUnidade();
                    break;
                case 4:
                    deletarPorId();
                    break;
                default:
                    System.out.println("Opção Incorreta!");
                    break;
            }
            System.out.println("Deseja continuar? \n 1.Sim \n 2.Não");
            Integer desejaContinuar = sc.nextInt();
            controleFluxo = (desejaContinuar == 1) ? true : false;
        }
    }

    public void salvar(){

        // Criar objetos
        Cargo cargo = null;
        Funcionario funcionario = null;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Bem Vindo ao cadastro de Unidade de Trabalho");

        System.out.print("Digite uma Descrição: ");
        String descricao = scanner.nextLine();

        System.out.print("Digite o Endereço: ");
        String endereco = scanner.nextLine();

        System.out.print("Caso tenha Complemento: ");
        String complemento = scanner.nextLine();

        System.out.print("Digite o Bairro: ");
        String bairro = scanner.nextLine();

        System.out.print("Digite o CEP: ");
        String cep = scanner.nextLine();

        System.out.print("Digite a Cidade: ");
        String cidade = scanner.nextLine();

        System.out.print("Digite o Estado: ");
        String estado = scanner.nextLine();

        System.out.print("Digite o número da Residencia: ");
        int numero = scanner.nextInt();
        scanner.nextLine();

        UnidadeTrabalho unidadeTrabalho =  new UnidadeTrabalho(descricao,endereco,numero,complemento,bairro,cep,cidade,estado);

        System.out.println("Funcionarios: ");

        System.out.print("Digite o salário do Funcionário: ");
        double salarioFuncionario = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Digite o nome do Funcionário: ");
        String nomeFuncionario = scanner.nextLine();

        System.out.print("Digite o CPF do Funcionário: ");
        String cpf = scanner.nextLine();

        System.out.print("Digite o nome do Cargo: ");
        String nomeCargo = scanner.nextLine();

        cargo = new Cargo(nomeCargo);
        cargoRepository.save(cargo);
        funcionario= new Funcionario(nomeFuncionario,cpf,salarioFuncionario,cargo);
        funcionario.adicionarListaUnidadeTrabalho(unidadeTrabalho);



        funcionarioRepository.save(funcionario);
        unidadeTrabalhoRepository.save(unidadeTrabalho);



        System.out.println("----------------------------------------");
        System.out.println(unidadeTrabalho.getListaFuncionarios());
        System.out.println("----------------------------------------");
        System.out.println(cargo);
        System.out.println("----------------------------------------");
        System.out.println(funcionario);
        System.out.println("----------------------------------------");
        System.out.println(unidadeTrabalho);


        System.out.println("Salvo com Sucesso");
    }
    public void atualizar(){
        System.out.println("Digite o id para atualizar: ");
        Integer id = sc.nextInt();

        Optional<UnidadeTrabalho> unidadeTrabalhoRecuperado = unidadeTrabalhoRepository.findById(id);

        if (unidadeTrabalhoRecuperado.isEmpty()){
            throw new ValidacaoException("ERROR! Unidade Trabalho não encontrado");
        }
        sc.nextLine();
        System.out.print("Digite uma Descrição: ");
        String descricao = sc.nextLine();
        sc.nextLine();
        System.out.print("Digite o Endereço: ");
        String endereco = sc.nextLine();
        sc.nextLine();
        System.out.print("Caso tenha Complemento: ");
        String complemento = sc.nextLine();
        sc.nextLine();
        System.out.print("Digite o Bairro: ");
        String bairro = sc.nextLine();
        sc.nextLine();
        System.out.print("Digite o CEP: ");
        String cep = sc.nextLine();
        sc.nextLine();
        System.out.print("Digite a Cidade: ");
        String cidade = sc.nextLine();
        sc.nextLine();
        System.out.print("Digite o Estado: ");
        String estado = sc.nextLine();
        sc.nextLine();
        System.out.print("Digite o número da Residencia: ");
        int numero = sc.nextInt();
        sc.nextLine();

        UnidadeTrabalho unidadeTrabalho =  new UnidadeTrabalho(id,descricao,endereco,numero,complemento,bairro,cep,cidade,estado);

        unidadeTrabalhoRepository.save(unidadeTrabalho);
    }
    public void visualizarTodasUnidade(){
        Iterable<UnidadeTrabalho> unidadeTrabalhosRecuperadaBanco = unidadeTrabalhoRepository.findAll();
        unidadeTrabalhosRecuperadaBanco.forEach(unidades -> System.out.println(unidades));
    }

    public void deletarPorId(){
        System.out.println("Digite o id para excluir: ");
        Integer id = sc.nextInt();

        Optional<UnidadeTrabalho> unidadeTrabalhoRecuperado = unidadeTrabalhoRepository.findById(id);

        if (unidadeTrabalhoRecuperado.isEmpty()){
            throw new ValidacaoException("ERROR! Cargo não encontrado");
        }
        unidadeTrabalhoRepository.deleteById(id);
        System.out.println("Registro Deletado Com Sucesso");
    }
}
