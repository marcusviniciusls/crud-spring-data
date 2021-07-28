package br.com.alura.spring.data.entities;

import javax.persistence.*;
import java.util.List;

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

    @ManyToMany
    @JoinTable(name = "unidadetrabalho_funcionario", joinColumns ={@JoinColumn(name = "unidade_trabalho_id")}, inverseJoinColumns = {@JoinColumn(name="funcionario_id")})
    private List<Funcionario> listaFuncionarios;

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
}
