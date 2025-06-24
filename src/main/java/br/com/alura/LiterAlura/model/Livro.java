package br.com.alura.LiterAlura.model;

import jakarta.persistence.*;

@Entity
@Table(name = "livros")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String titulo;

    @ManyToOne
    private Autor autor;

    private Double totalDownloads;
    private String idioma;

    public Livro(){}

    public Livro(DadosLivro dados) {
        this.titulo = dados.titulo();
        this.totalDownloads = dados.totalDownloads();
        this.idioma = dados.idioma().getFirst();
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
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

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return  "---- LIVRO ----\n" +
                "Título: " + titulo + "\n" +
                "Autor: " + autor.getNome() + "\n" +
                "Total de Downloads: " + totalDownloads.intValue() + "\n" +
                "Idioma: " + idioma + "\n" +
                "---------------";
    }
}
