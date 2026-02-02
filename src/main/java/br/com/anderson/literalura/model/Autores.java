package br.com.anderson.literalura.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Autores {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Integer dataNascimento;
    private Integer dataFalescimento;

    @ManyToOne
    private Livros livro;

    public Autores() {
    }

    public Autores(DadosAutor dadosAutor){
        this.nome = dadosAutor.nome();
        this.dataNascimento = dadosAutor.dataNascimento();
        this.dataFalescimento = dadosAutor.dataFalescimento();
    }

    public String getNome() {
        return nome;
    }

    public Autores setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public Integer getDataNascimento() {
        return dataNascimento;
    }

    public Autores setDataNascimento(Integer dataNascimento) {
        this.dataNascimento = dataNascimento;
        return this;
    }

    public Integer getDataFalescimento() {
        return dataFalescimento;
    }

    public Autores setDataFalescimento(Integer dataFalescimento) {
        this.dataFalescimento = dataFalescimento;
        return this;
    }

    public Long getId() {
        return id;
    }

    public Autores setId(Long id) {
        this.id = id;
        return this;
    }

    public Livros getLivro() {
        return livro;
    }

    public Autores setLivro(Livros livro) {
        this.livro = livro;
        return this;
    }

    @Override
    public String toString() {
        return  "\nNome do autor = " + nome +
                "\nData de Nascimento = " + dataNascimento +
                "\nData de Falescimento = " + dataFalescimento +
                "\nLivros = " + livro.getTitulo() + "\n";
    }
}
