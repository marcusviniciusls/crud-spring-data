package br.com.alura.spring.data.services;

import br.com.alura.spring.data.entities.Cargo;
import br.com.alura.spring.data.exception.ValidacaoException;
import br.com.alura.spring.data.repository.CargoRepository;
import br.com.alura.spring.data.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.ArrayList;

@Service
public class CrudCargoService {

    private boolean controleFluxoLacoRepeticao = true;
    private final CargoRepository cargoRepository;

    public CrudCargoService(CargoRepository cargoRepository) {
        this.cargoRepository = cargoRepository;
    }

    Scanner sc = new Scanner(System.in);

    public void inicial(){
        System.out.println("Bem vindo ao cadastro de Cargos:");

        while(controleFluxoLacoRepeticao){
            System.out.println("0.Sair \n1.Cadastrar Cargo \n2.Atualizar Cargo \n3. Visualizar Todos o Cargos \n4.Deletar Cargo Por Id");
            int opcao = sc.nextInt();
            switch (opcao){
                case 1:
                    salvar();
                    break;
                case 2:
                    atualizar();
                    break;
                case 3:
                    visualizarTodosCargos();
                    break;
                case 4:
                    deletarPorId();
                    break;
                default:
                    controleFluxoLacoRepeticao = false;
                    break;
            }
            System.out.println("Deseja continuar? \n 1.Sim \n 2.Não");
            Integer desejaContinuar = sc.nextInt();
            controleFluxoLacoRepeticao = (desejaContinuar == 1) ? true : false;
        }



    }

    private void salvar(){

        System.out.print("Digite o nome do cargo: ");
        sc.nextLine();
        String nomeCargo = sc.next();
        Cargo cargo = new Cargo(nomeCargo);

        cargoRepository.save(cargo);
        System.out.println("Cargo cadastrado com sucesso");
    }

    private void atualizar() {
        System.out.println("Digite o número do ID que deseja recuperar: ");
        Integer idRecuperado = sc.nextInt();

        Optional<Cargo> cargoRecuperado = cargoRepository.findById(idRecuperado);

        if (cargoRecuperado.isEmpty()){
            throw new ValidacaoException("ERROR! Cargo não encontrado");
        }
        System.out.print("Digite o novo nome do cadastro: ");
        sc.nextLine();
        String nomeParaAtualizar = sc.nextLine();
        Cargo cargo = new Cargo(idRecuperado,nomeParaAtualizar);

        cargoRepository.save(cargo);

        System.out.println("Cadastro atualizado!");
    }

    private void visualizarTodosCargos(){
       Iterable<Cargo> cargos = cargoRepository.findAll();
       cargos.forEach(cargo -> System.out.println(cargo));
    }

    private void deletarPorId(){
        System.out.println("Digite o Id que deseja excluir do banco de dados");
        Integer idParaDeletar = sc.nextInt();

        Optional<Cargo> cargoRecuperado = cargoRepository.findById(idParaDeletar);

        if (cargoRecuperado.isEmpty()){
            throw new ValidacaoException("ERROR! Cargo não encontrado");
        }

        cargoRepository.deleteById(idParaDeletar);
        System.out.println("Registro Deletado Com Sucesso");
    }


}
