package br.com.alura.spring.data.services;

import br.com.alura.spring.data.entities.Cargo;
import br.com.alura.spring.data.entities.Funcionario;
import br.com.alura.spring.data.repository.CargoRepository;
import br.com.alura.spring.data.repository.FuncionarioRepository;
import br.com.alura.spring.data.repository.UnidadeTrabalhoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Scanner;

@Service
public class CrudFuncionarioService {

    private boolean controleFluxo = true;
    private final FuncionarioRepository funcionarioRepository;
    private final CargoRepository cargoRepository;
    private final UnidadeTrabalhoRepository unidadeTrabalhoRepository;

    public CrudFuncionarioService(FuncionarioRepository funcionarioRepository, CargoRepository cargoRepository, UnidadeTrabalhoRepository unidadeTrabalhoRepository) {
        this.funcionarioRepository = funcionarioRepository;
        this.cargoRepository = cargoRepository;
        this.unidadeTrabalhoRepository = unidadeTrabalhoRepository;
    }

    Scanner sc = new Scanner(System.in);
    public void inicial(){
        System.out.println("Bem vindo ao cadastro de Funcionário! ");
        while(controleFluxo){
            System.out.println("Selecione as opções abaixo:");
            System.out.println("1.Cadastrar \n2.Atualizar \n3.Visualizar todos os Funcionários \n4.Deletar Por Id");
            int opcao = sc.nextInt();
            switch (opcao){
                case 1:
                    salvar();
                    break;
                case 2:
                    atualizar();
                    break;
                case 3:
                    visualizarTodosFuncionarios();
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

    private void salvar(){
        boolean controleFluxoSalvar = true;
        System.out.println("Bem vindo ao cadastro de Funcionário!");
        System.out.print("Digite o nome do Funcionário: ");
        String nomeFuncionario = sc.nextLine();
        System.out.print("Digite o CPF: ");
        String cpf = sc.nextLine();

        System.out.print("Digite o salário recebido: ");
        double salario = sc.nextDouble();

        System.out.println("Digite o nome do Cargo: ");
        String nomeCargo = sc.nextLine();
        Cargo cargo = new Cargo(nomeCargo);
        Funcionario funcionario = new Funcionario(nomeFuncionario,cpf,salario,cargo);
        cargoRepository.save(cargo);
        funcionarioRepository.save(funcionario);
        //cargo.adicionarListaFuncionario(funcionario);
        System.out.println("Funcionário Cadastrado com Sucesso!");
    }
    private void atualizar(){


        System.out.println("Digite o Id do Funcionário para atualizar");
        Integer id = sc.nextInt();

        System.out.print("Digite o novo nome do Funcionário: ");
        String nomeFuncionario = sc.nextLine();

        System.out.print("Digite o CPF: ");
        String cpf = sc.nextLine();

        System.out.print("Digite o salário Novo: ");
        double salario = sc.nextDouble();

        System.out.print("Digite o Id do Cargo");
        Integer idCargo = sc.nextInt();

        Optional<Cargo> cargo = cargoRepository.findById(idCargo);

        Funcionario funcionario = new Funcionario(nomeFuncionario,cpf,salario);
        funcionario.setId(id);
        funcionario.setCargo(cargo.get());

        funcionarioRepository.save(funcionario);
        System.out.println("Funcionário Cadastrado com Sucesso!");


    }
    private void visualizarTodosFuncionarios(){
        Iterable<Funcionario> listaFuncionario = funcionarioRepository.findAll();
        listaFuncionario.forEach(func -> System.out.println(func));
    }

    private void deletarPorId(){
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite o número do Id do Funcionário para a Exclusão: ");
        Integer idFuncionarioExclusao = sc.nextInt();

        funcionarioRepository.deleteById(idFuncionarioExclusao);
    }



}
