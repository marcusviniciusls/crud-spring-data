package br.com.alura.spring.data.entities;

import javax.persistence.*;
import java.util.List;
import java.util.*;

@Entity
@Table(name = "unidades_trabalho")
public class UnidadeTrabalho {

    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String descricao;
    private String endereco;
    private Integer numero;
    private String complemento;
    private String bairro;
    private String cep;
    private String cidade;
    private String estado;

    @ManyToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "unidadetrabalho_funcionario", joinColumns ={@JoinColumn(name = "unidade_trabalho_id")})
    private List<Funcionario> listaFuncionarios = new ArrayList<>();

    // Métodos Gets

    public Integer getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getEndereco() {
        return endereco;
    }

    public Integer getNumero() {
        return numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCep() {
        return cep;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }

    // Construtor

    public UnidadeTrabalho(String descricao, String endereco, Integer numero, String complemento, String bairro, String cep, String cidade, String estado) {
        this.descricao = descricao;
        this.endereco = endereco;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cep = cep;
        this.cidade = cidade;
        this.estado = estado;
    }

    public void adicionarFuncionarioLista(Funcionario funcionario){
        listaFuncionarios.add(funcionario);
    }

    public List<Funcionario> getFuncionarios(){
        return this.listaFuncionarios;
    }

    @Override
    public String toString() {
        return "UnidadeTrabalho{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", endereco='" + endereco + '\'' +
                ", numero=" + numero +
                ", complemento='" + complemento + '\'' +
                ", bairro='" + bairro + '\'' +
                ", cep='" + cep + '\'' +
                ", cidade='" + cidade + '\'' +
                ", estado='" + estado + '\'' +
                ", listaFuncionarios=" + listaFuncionarios +
                '}';
    }
}
