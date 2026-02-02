package br.com.anderson.literalura.repository;

import br.com.anderson.literalura.model.Autores;
import br.com.anderson.literalura.model.Livros;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LivroRepository extends JpaRepository<Livros, Long> {

    List<Livros> findLivrosByLinguagensContainingIgnoreCase(String linguagens);

}
