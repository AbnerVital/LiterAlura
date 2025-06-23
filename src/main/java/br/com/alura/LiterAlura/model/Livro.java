package br.com.alura.LiterAlura.model;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.ArrayList;
import java.util.List;

public class Livro {
    private String titulo;
    private String autor;
    private Double totalDownloads;
    private String idioma;

    public Livro(DadosLivros dados) {
        this.titulo = dados.livros().getFirst().titulo();
        this.autor = dados.livros().getFirst().autor().getFirst().nome();
        this.totalDownloads = dados.livros().getFirst().totalDownloads();
        this.idioma = dados.livros().getFirst().idioma().getFirst();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }


    public Double getTotalDownloads() {
        return totalDownloads;
    }

    public void setTotalDownloads(Double totalDownloads) {
        this.totalDownloads = totalDownloads;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    @Override
    public String toString() {
        return "Título: " + titulo + " | " +
                "Autor: " + autor + " | " +
                "Total de Downloads: " + totalDownloads.intValue() + " | " +
                "Idioma: " + idioma;
    }
}
