package br.com.alura.spring.data.entities;

import javax.persistence.*;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "cargos")
public class Cargo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;

    @OneToMany(mappedBy = "cargo")
    private List<Funcionario> listaFuncionario = new ArrayList<>();

    // Construtores
    public Cargo(String nome) {
        this.nome = nome;
    }

    public Cargo(){}

    // Métodos Gets
    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return "Cargo{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void adicionarListaFuncionario(Funcionario funcionario){
        this.adicionarListaFuncionario(funcionario);
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Cargo(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }
}
