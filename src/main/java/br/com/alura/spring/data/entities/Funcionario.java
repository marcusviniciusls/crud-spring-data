package br.com.alura.spring.data.entities;



import javax.persistence.*;
import java.time.LocalDate;

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
}
