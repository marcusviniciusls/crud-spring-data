package br.com.alura.spring.data.services;

import br.com.alura.spring.data.entities.Cargo;
import br.com.alura.spring.data.entities.Funcionario;
import br.com.alura.spring.data.exception.ValidacaoException;
import br.com.alura.spring.data.repository.CargoRepository;
import br.com.alura.spring.data.repository.FuncionarioRepository;
import br.com.alura.spring.data.repository.UnidadeTrabalhoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.ArrayList;

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
    public void inicial() throws ValidationException {
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
        Scanner sc = new Scanner(System.in);

        Cargo cargo = new Cargo();
        Funcionario funcionario = new Funcionario();

        System.out.println("Bem vindo ao cadastro de Funcionário!");
        System.out.print("Digite o nome do Funcionário: ");
        String nomeFuncionario = sc.nextLine();

        System.out.print("Digite o CPF: ");
        String cpf = sc.nextLine();

        System.out.print("Digite o salário recebido: ");
        double salario = sc.nextDouble();

        funcionario = new Funcionario(nomeFuncionario,cpf,salario);

        while(controleFluxoSalvar) {
            System.out.println("Selecione uma das opções abaixo: \n1.Cadastrar um novo Cargo \n2.Visualizar Todos os Cargos \n3.Buscar Cargo por ID");
            int opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    System.out.print("Digite o nome do Cargo: ");
                    String nomeCargo = sc.next();
                    cargo.setNome(nomeCargo);
                    controleFluxoSalvar = false;
                    funcionario = new Funcionario(nomeFuncionario,cpf,salario,cargo);
                    cargoRepository.save(cargo);
                    funcionarioRepository.save(funcionario);
                    break;
                case 2:
                    Iterable<Cargo> listaCargo = cargoRepository.findAll();
                    List<Cargo> cargos = new ArrayList<>();
                    listaCargo.forEach(cargos::add);
                    if(cargos.isEmpty()){
                        System.out.println("Não há cadastro de Cargos");
                    }
                    else {
                        listaCargo.forEach(c -> System.out.println(c));
                    }
                    controleFluxoSalvar = true;
                    System.out.println("Retoranando ao menu de opções");
                    break;
                case 3:
                    System.out.print("Digite o ID do Cargo: ");
                    int idCargo = sc.nextInt();
                    Optional<Cargo> cargoRecuperadoBase = cargoRepository.findById(idCargo);
                    if (cargoRecuperadoBase.isEmpty()) {
                        System.out.println("Cargo Não Encontrado!");
                        controleFluxoSalvar = true;
                    } else {
                        funcionario.setCargo(cargoRecuperadoBase.get());
                        controleFluxoSalvar = false;
                        System.out.println("Cargo Vínculado ao Cadastro!");

                        funcionarioRepository.save(funcionario);
                    }
                    break;
                default:
                    System.out.println("Opção Incorreta! Tente Novamente.");
                    controleFluxoSalvar = true;
                    break;
            }
        }





        System.out.println("Funcionário Cadastrado com Sucesso!");
    }
    private void atualizar() throws ValidationException {


        System.out.println("Digite o Id do Funcionário para atualizar");
        Integer id = sc.nextInt();


        Optional<Funcionario> funcionarioAtualizar = funcionarioRepository.findById(id);
        if (funcionarioAtualizar.isEmpty()){
            throw new ValidacaoException("ID do funcionário não encontrado");
        } else {
            System.out.println("Funcionário encontrado!");
        }


        sc.nextLine();
        System.out.print("Digite o novo nome do Funcionário: ");
        String nomeFuncionario = sc.nextLine();

        System.out.print("Digite o CPF: ");
        String cpf = sc.nextLine();

        System.out.print("Digite o salário Novo: ");
        double salario = sc.nextDouble();


        System.out.print("Digite o Id do Cargo");
        Integer idCargo = sc.nextInt();


        Optional<Cargo> cargo = cargoRepository.findById(idCargo);
        if (cargo.isEmpty()){
            throw new ValidacaoException("ID do cargo não encontrado");
        } else {
            System.out.println("Cargo encontrado!");
        }
        Funcionario funcionario = new Funcionario(nomeFuncionario,cpf,salario);
        funcionario.setId(id);
        funcionario.setCargo(cargo.get());

        funcionarioRepository.save(funcionario);
        System.out.println("Funcionário Cadastrado com Sucesso!");


    }
    private void visualizarTodosFuncionarios(){
        System.out.print("Qual página você deseja visualizar? ");
        Integer page = sc.nextInt();

        PageRequest pageable = PageRequest.of(page,5, Sort.by(Sort.Direction.DESC, "salario"));
        Page<Funcionario> listaFuncionario = funcionarioRepository.findAll(pageable);

        System.out.println(listaFuncionario);
        System.out.println("Página atual: " + listaFuncionario.getNumber());
        System.out.println("Total de elemento: " + listaFuncionario.getTotalElements());
        listaFuncionario.forEach(func -> System.out.println(func));
    }

    private void deletarPorId(){
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite o número do Id do Funcionário para a Exclusão: ");
        Integer idFuncionarioExclusao = sc.nextInt();

        Optional<Funcionario> funcionario = funcionarioRepository.findById(idFuncionarioExclusao);

        if (funcionario.isEmpty()){
            throw new ValidacaoException("ID não encontrado: ERROR! Não foi possível excluir Funcionário");
        } else {
            funcionarioRepository.deleteById(idFuncionarioExclusao);
            System.out.println("Excluído com sucesso");
        }

    }



}
