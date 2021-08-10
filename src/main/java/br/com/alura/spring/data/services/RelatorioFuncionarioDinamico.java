package br.com.alura.spring.data.services;

import br.com.alura.spring.data.entities.Funcionario;
import br.com.alura.spring.data.repository.FuncionarioRepository;
import br.com.alura.spring.data.speficication.SpecificationFuncionarios;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
public class RelatorioFuncionarioDinamico {

    private final FuncionarioRepository funcionarioRepository;
    Scanner sc = new Scanner(System.in);


    public RelatorioFuncionarioDinamico(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public void inicial(){
        System.out.print("Digite um valor por gentileza? ");
        String nomePesquisa = sc.next();


        List<Funcionario> listaFuncionarios = funcionarioRepository.findAll(Specification.where(SpecificationFuncionarios.nome(nomePesquisa)));
    }
}
