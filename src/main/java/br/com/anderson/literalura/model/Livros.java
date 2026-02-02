package br.com.anderson.literalura.model;

import jakarta.persistence.*;
import br.com.anderson.literalura.model.Autores;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "Livros")
public class Livros {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;

    @OneToMany(mappedBy = "livro", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Autores> autores;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> linguagens;
    private Integer totalDownloads;


    public Livros() {
    }

    public Livros(DadosLivros dadosLivros) {
        this.titulo = dadosLivros.titulo();
        if (dadosLivros.autores() != null) {
            this.autores = dadosLivros.autores().stream()
                    .map(Autores::new)
                    .collect(Collectors.toList());
            this.autores.forEach(a -> a.setLivro(this));
        } else {
            this.autores = new ArrayList<>();
        }
        this.linguagens = dadosLivros.linguagens();
        this.totalDownloads = dadosLivros.totalDownloads();
    }

    public Long getId() {
        return id;
    }

    public Livros setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitulo() {
        return titulo;
    }

    public Livros setTitulo(String titulo) {
        this.titulo = titulo;
        return this;
    }

    public List<String> getLinguagens() {
        return linguagens;
    }

    public Livros setLinguagens(List<String> linguagens) {
        this.linguagens = linguagens;
        return this;
    }

    public List<Autores> getAutores() {
        return autores;
    }

    public Livros setAutores(List<Autores> autores) {
        this.autores = autores;
        return this;
    }

    public Integer getTotalDownloads() {
        return totalDownloads;
    }

    public Livros setTotalDownloads(Integer totalDownloads) {
        this.totalDownloads = totalDownloads;
        return this;
    }

    @Override
    public String toString() {
        return  "----- LIVRO -----" +
                "\nTitulo = " + titulo +
                "\nAutores = " + autores.stream()
                .map(Autores::getNome)
                .toList() +
                "\nLinguagens = " + linguagens.get(0) +
                "\nTotal de downloads = " + totalDownloads +
                "\n-----------------------" + "\n";
    }
}
