package br.com.anderson.literalura.repository;

import br.com.anderson.literalura.model.Autores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AutoresRepository extends JpaRepository<Autores , Long> {

    @Query("SELECT a FROM Autores a WHERE :ano BETWEEN a.dataNascimento AND a.dataFalescimento")
    List<Autores> findAutoresVivosNoAno(@Param("ano") Integer ano);

}
