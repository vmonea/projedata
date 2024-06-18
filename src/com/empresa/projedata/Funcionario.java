package com.empresa.projedata;

import java.math.BigDecimal; // Importa a classe BigDecimal para trabalhar com valores precisos.
import java.time.LocalDate;


//A classe está herdando a "PESSOA" e adicionando novos atributos
public class Funcionario extends Pessoa {
    private BigDecimal salario;
    private String funcao;

// Super esta chamando os atributos construtores da classe "PESSOA"
    public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
        super(nome, dataNascimento);
        this.salario = salario;
        this.funcao = funcao;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }
}

