package br.com.anderson.literalura;

import br.com.anderson.literalura.repository.AutoresRepository;
import br.com.anderson.literalura.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiterAluraApplication implements CommandLineRunner {

    @Autowired
    private LivroRepository repository;
    @Autowired
    private AutoresRepository autoresRepository;

    public static void main(String[] args) {
        SpringApplication.run(LiterAluraApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Main main = new Main(repository, autoresRepository);
        main.exibeDados();
    }
}
