package br.com.anderson.literalura.service;

public interface IConversaoDados {
    <T> T obterDados(String json , Class<T> classe);
}
