package br.com.alura.LiterAlura.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public class Autor {
    private String nome;
    private Integer dtNascimento;
    private Integer dtFalecimento;

    public Autor(DadosLivros dados) {
        this.nome = dados.livros().getFirst().autor().getFirst().nome();
        this.dtNascimento = dados.livros().getFirst().autor().getFirst().dtNascimento();
        this.dtFalecimento = dados.livros().getFirst().autor().getFirst().dtFalecimento();
    }

    public Integer getDtFalecimento() {
        return dtFalecimento;
    }

    public void setDtFalecimento(Integer dtFalecimento) {
        this.dtFalecimento = dtFalecimento;
    }

    public Integer getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(Integer dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Autor: " + nome + " | " +
                "Ano de nascimento: " + dtNascimento + " | " +
                "Ano de falecimento: " + dtFalecimento;
    }
}
