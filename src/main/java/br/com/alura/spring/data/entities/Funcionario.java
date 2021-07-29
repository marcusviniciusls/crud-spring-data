package br.com.alura.spring.data.entities;




import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "funcionarios")
public class Funcionario {

    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String cpf;
    private Double salario;
    @Column(name = "data_contratacao")
    private LocalDate dataContratacao = LocalDate.now();

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name = "cargo_id", nullable = false)
    public Cargo cargo;

    @Fetch(FetchMode.SELECT)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "funcionarios_unidades", joinColumns = {
            @JoinColumn(name = "fk_funcionario") },
            inverseJoinColumns = { @JoinColumn(name = "fk_unidade") })
    private List<UnidadeTrabalho> unidadeTrabalhos;



    // Métodos Gets
    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public Double getSalario() {
        return salario;
    }

    public LocalDate getDataContratacao() {
        return dataContratacao;
    }



    //Construtores
    public Funcionario(){}

    public Funcionario(String nome, String cpf, Double salario, Cargo cargo) {
        this.nome = nome;
        this.cpf = cpf;
        this.salario = salario;
        this.cargo = cargo;
    }

    public Funcionario(String nome, String cpf, Double salario) {
        this.nome = nome;
        this.cpf = cpf;
        this.salario = salario;
    }

    @Override
    public String toString() {
        return "Funcionario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", salario=" + salario +
                ", dataContratacao=" + dataContratacao +
                ", cargo=" + cargo +
                '}';
    }

    // Métodos Sets


    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }
}
