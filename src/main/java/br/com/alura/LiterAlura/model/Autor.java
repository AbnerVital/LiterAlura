package br.com.alura.LiterAlura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nome;
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Livro> livros  = new ArrayList<>();
    private Integer dtNascimento;
    private Integer dtFalecimento;

    private Autor(){}

    public Autor (DadosAutor dados) {
        this.nome = dados.nome();
        this.dtNascimento = dados.dtNascimento();
        this.dtFalecimento = dados.dtFalecimento();
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Autor: " + nome + "\n" +
                "Ano de nascimento: " + dtNascimento + "\n" +
                "Ano de falecimento: " + dtFalecimento + "\n" +
                "Livros: " + livros.stream().map(l -> l.getTitulo()).toList();
    }
}
