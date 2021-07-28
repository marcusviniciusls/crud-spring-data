package br.com.alura.spring.data.services;

import br.com.alura.spring.data.entities.Cargo;
import br.com.alura.spring.data.repository.CargoRepository;
import br.com.alura.spring.data.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class CrudCargoService {

    private boolean controleFluxoLacoRepeticao = true;
    private final CargoRepository cargoRepository;

    public CrudCargoService(CargoRepository cargoRepository) {
        this.cargoRepository = cargoRepository;
    }



    public void inicial(Scanner sc){
        System.out.println("Bem vindo ao cadastro de Cargos:");
        while(controleFluxoLacoRepeticao){
            System.out.println("0.Sair \n1.Cadastrar Cargo \n2.Atualizar Cargo \n3. Visualizar Todos o Cargos \n4.Deletar Cargo Por Id");
            int opcao = sc.nextInt();
            switch (opcao){
                case 1:
                    salvar(sc);
                    break;
                case 2:
                    atualizar(sc);
                    break;
                case 3:
                    visualizarTodosCargos();
                    break;
                case 4:
                    deletarPorId(sc);
                    break;
                default:
                    controleFluxoLacoRepeticao = false;
                    break;
            }
            if(controleFluxoLacoRepeticao) {
                System.out.println("Deseja sair: \n 1.Sim \n 2.Não");
                int ficarOuSair = sc.nextInt();
                if (ficarOuSair == 2) {
                    controleFluxoLacoRepeticao = true;
                } else {
                    controleFluxoLacoRepeticao = false;
                    System.out.println("Obrigado por utilizar os melhores recursos em tecnologia!");
                }
            }
            else {
                System.out.println("Obrigado por utilizar os melhores recursos em tecnologia!");
            }
        }



    }

    private void salvar(Scanner sc){

        System.out.println("Digite o nome do cargo");
        String nomeCargo = sc.next();
        Cargo cargo = new Cargo(nomeCargo);
        cargoRepository.save(cargo);
        System.out.println("Cargo cadastrado com sucesso");
    }

    private void atualizar(Scanner sc) {
        System.out.println("Digite o número do ID que deseja recuperar: ");
        Integer idRecuperado = sc.nextInt();
        System.out.println("Digite o novo nome do cadastro");
        String nomeParaAtualizar = sc.next();

        Cargo cargo = new Cargo(nomeParaAtualizar);
        cargo.setId(idRecuperado);

        cargoRepository.save(cargo);

        System.out.println("Cadastro atualizado!");
    }

    private void visualizarTodosCargos(){
       Iterable<Cargo> cargos = cargoRepository.findAll();
       cargos.forEach(cargo -> System.out.println(cargo));
    }

    private void deletarPorId(Scanner scanner){
        System.out.println("Digite o Id que deseja excluir do banco de dados");
        Integer idParaDeletar = scanner.nextInt();
        cargoRepository.deleteById(idParaDeletar);
        System.out.println("Registro Deletado Com Sucesso");
    }
}
