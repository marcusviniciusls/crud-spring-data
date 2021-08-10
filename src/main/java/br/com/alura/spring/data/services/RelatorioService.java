package br.com.alura.spring.data.services;

import br.com.alura.spring.data.entities.Funcionario;
import br.com.alura.spring.data.entities.FuncionarioProjecao;
import br.com.alura.spring.data.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@Service
public class RelatorioService{

    private final FuncionarioRepository funcionarioRepository;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    boolean controleFluxo = true;
    Scanner scanner = new Scanner(System.in);

    public RelatorioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public void inicial(){
        while(controleFluxo) {
            System.out.println("Selecione as opções abaixo:");
            System.out.println("1.Buscar Funcionário por Nome \n2.Busca Funcionário nome, data contratação e salário maior\n3.Busca Funcionário data contratação \n" +
                    "4. Pesquisa Funcionário Salário");
            int opcao = scanner.nextInt();
            switch (opcao) {
                case 1:
                    buscarFuncionarioNome();
                    break;
                case 2:
                    buscaFuncionarioNomeSalarioMaiorData();
                    break;
                case 3:
                    buscarFuncionarioDataContratacao();
                    break;
                case 4:
                    pesquisaFuncionarioSalario();
                    break;
                default:
                    System.out.println("Opção Incorreta!");
                    break;
            }
            System.out.println("Deseja continuar? \n 1.Sim \n 2.Não");
            Integer desejaContinuar = scanner.nextInt();
            controleFluxo = (desejaContinuar == 1) ? true : false;
        }
    }

    private void buscarFuncionarioNome(){
        System.out.println("Qual nome deseja pesquisar? ");
        String nomeParaPesquisar = scanner.next();
        List<Funcionario> listaFuncionarioPesquisadosPorNome = funcionarioRepository.findByNome(nomeParaPesquisar);
        listaFuncionarioPesquisadosPorNome.forEach(System.out::println);
    }

    private void buscaFuncionarioNomeSalarioMaiorData(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Qual nome deseja pesquisar: ");
        String nome = sc.next();

        System.out.println("Qual data de contratação deseja pesquisar: ");
        String data = sc.next();
        LocalDate localData = LocalDate.parse(data,formatter);

        System.out.println("Qual salário deseja pesquisar: ");
        Double salario = sc.nextDouble();

        List<Funcionario> listaFuncionario = funcionarioRepository.findNomeSalarioMaiorDataContratacao(nome,salario,localData);
        listaFuncionario.forEach(System.out::println);
    }

    private void buscarFuncionarioDataContratacao(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Qual data de contratação deseja pesquisar: ");
        String data = sc.next();
        LocalDate localData = LocalDate.parse(data,formatter);

        List<Funcionario> listaFuncionario = funcionarioRepository.findDataContratacaoMaior(localData);
        listaFuncionario.forEach(System.out::println);
    }

    private void pesquisaFuncionarioSalario(){
        List<FuncionarioProjecao> lista = funcionarioRepository.findFuncionarioSalario();
        lista.forEach(f -> System.out.println("Funcionário: id:" + f.getId() + " Nome: " + f.getNome() + " Salário: " + f.getSalario()));
    }


}
