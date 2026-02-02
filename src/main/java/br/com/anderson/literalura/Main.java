package br.com.anderson.literalura;

import br.com.anderson.literalura.model.DadosLivros;
import br.com.anderson.literalura.model.DadosResult;
import br.com.anderson.literalura.model.Livros;
import br.com.anderson.literalura.repository.AutoresRepository;
import br.com.anderson.literalura.repository.LivroRepository;
import br.com.anderson.literalura.service.ConsumoAPI;
import br.com.anderson.literalura.service.ConversaoDados;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private final Scanner sc = new Scanner(System.in);
    private final String ENDERECO = "https://gutendex.com/";
    ConsumoAPI consumoAPI = new ConsumoAPI();
    ConversaoDados conversao = new ConversaoDados();

    private LivroRepository repository;
    private AutoresRepository autoresRepository;

    public Main(LivroRepository repository , AutoresRepository autoresRepository) {
        this.repository = repository;
        this.autoresRepository = autoresRepository;
    }

    public void exibeDados(){
        var opcao = -1;
        while (opcao != 0) {

            System.out.println("----- Biblioteca Alura -----");
            System.out.println("----- Opções -----");
            System.out.println("0. Sair");
            System.out.println("1. Buscar livro por titulo");
            System.out.println("2. Buscar todos os livros");
            System.out.println("3. Listar autores");
            System.out.println("4. Listar autores vivos por ano");
            System.out.println("5. Listar livros em um determinado idioma");

            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao){
                case 5:
                    listaLivrosPorIdioma();
                    break;
                case 4:
                    listarAutoresVivosPorData();
                    break;
                case 3:
                    listarAutores();
                    break;
                case 2:
                    buscarTodosOsLivros();
                    break;
                case 1:
                    salvarLivro();
                    break;
                case 0:
                    System.out.println("Fechando o programa...");
                    break;
                default:
                    System.out.println("Opção invalida!");
                    break;
            }
        }
    }

    private DadosLivros getDadosLivros(){
        System.out.println("Digite o nome do livro: ");
        var livro = sc.nextLine();
        var json = consumoAPI.obterDados(ENDERECO + "books?search=%20" + livro.replace(" ", "%20").toLowerCase());
        DadosResult dadosResult = conversao.obterDados(json, DadosResult.class);

        if (dadosResult.resultado() != null && !dadosResult.resultado().isEmpty()){
            DadosLivros dadosLivros = dadosResult.resultado().get(0);
            return dadosLivros;
        } else {
            System.out.println("livro não encontrado!!!");
            return null;
        }
    }

    private void salvarLivro(){
        DadosLivros dadosLivros = getDadosLivros();

        if (dadosLivros != null){
            Livros livros = new Livros(dadosLivros);
            repository.save(livros);
            System.out.println(livros);
            System.out.println("\nLivro salvo com sucesso!");
        }
    }


    private void buscarTodosOsLivros(){

        try {
            repository.findAll().forEach(System.out::println);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void listarAutores() {
        try {
            autoresRepository.findAll().forEach(System.out::println);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void listarAutoresVivosPorData(){
        try {
            System.out.println("Digite o ano de nascimento do autor: ");
            var dataNascimento = sc.nextInt();
            if (!autoresRepository.findAutoresVivosNoAno(dataNascimento).isEmpty()) {
                autoresRepository.findAutoresVivosNoAno(dataNascimento).forEach(System.out::println);
            }else {
                System.out.println("Não existe nenhum autor vivo nesse ano!");
            }

        } catch (InputMismatchException e){
            System.out.println(e.getMessage());
        }
    }

    private void listaLivrosPorIdioma(){
        try {
            System.out.println("Insira o idioma para realizar a busca: ");
            System.out.println("es - espanhol");
            System.out.println("en - inglês");
            System.out.println("fr - francês");
            System.out.println("pt - português");

            var resposta = sc.nextLine();

            if (resposta.equalsIgnoreCase("es") || resposta.equalsIgnoreCase("en") ||
            resposta.equalsIgnoreCase("fr") || resposta.equalsIgnoreCase("pt")){
                if (!repository.findLivrosByLinguagensContainingIgnoreCase(resposta).isEmpty())
                    repository.findLivrosByLinguagensContainingIgnoreCase(resposta).forEach(System.out::println);
                else {
                    System.out.println("Não há livros associados a esse idioma");
                }
            } else {
                System.out.println("Idioma não disponivel ou inválido!");
            }
        } catch (InputMismatchException e){
            System.out.println(e.getMessage());
        }
    }
}
