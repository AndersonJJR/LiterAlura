# 📚 LiterAlura

<p align="center">
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white" />
  <img src="https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=spring&logoColor=white" />
  <img src="https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white" />
</p>

## 💻 Sobre o Projeto

O **LiterAlura** é uma aplicação de catálogo de livros desenvolvida como parte do desafio da formação Java da **Alura** (Oracle Next Education).

O objetivo do projeto é consumir a API externa [Gutendex](https://gutendex.com/) para buscar informações sobre livros, manipulando dados JSON, armazenando-os em um banco de dados relacional e oferecendo interação via console.

## ⚙️ Funcionalidades

O sistema oferece um menu interativo no console com as seguintes opções:

- 🔍 **Buscar livro pelo título**: Consulta a API, retorna os dados e salva no banco de dados.
- 📋 **Listar livros registrados**: Exibe todos os livros já salvos no sistema.
- 👥 **Listar autores registrados**: Mostra os autores dos livros salvos.
- 📅 **Listar autores vivos em um determinado ano**: Filtra autores pela data de nascimento e falecimento.
- 🌐 **Listar livros em um determinado idioma**: Filtra os livros por idioma (EN, PT, FR, etc.).

## 🛠️ Tecnologias Utilizadas

- **Java**: Linguagem principal (versão 17+).
- **Spring Boot**: Framework para inicialização e configuração.
- **Spring Data JPA**: Para persistência de dados e criação de repositórios.
- **PostgreSQL**: Banco de dados relacional.
- **Jackson**: Para desserialização de dados JSON (consumo da API).
- **Gutendex API**: API pública de livros utilizada.

## 🚀 Como Executar

### Pré-requisitos

Certifique-se de ter instalado:
- Java JDK 17 ou superior.
- Maven.
- PostgreSQL.

### Passos

1. **Clone o repositório**:
   ```bash
   git clone https://github.com/AndersonJJR/LiterAlura.git

### 2. **Configure o Banco de Dados**

Crie o banco PostgreSQL e configure as credenciais:

1. **Abra o PostgreSQL (pgAdmin ou DBeaver)** e execute:
   ```sql
   CREATE DATABASE literalura;

2. **Edite o arquivo** src/main/resources/application.properties:

# Configuração do banco
spring.datasource.url=jdbc:postgresql://localhost:5432/literalura
spring.datasource.username=postgres (exemplo)
spring.datasource.password=123456 (exemplo)

# JPA/Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

### 3. **Execute a Aplicação**

```bash
# Opção 1: Via IDE (Recomendado)
# 1. Abra o projeto no IntelliJ
# 2. Execute a classe: LiteraluraApplication.java
# 3. Aguarde o log "Started LiteraluraApplication"

# Opção 2: Via terminal/Maven
mvn spring-boot:run
```

## Estrutura do Projeto

```markdown
## 🗂️ Estrutura do Projeto

src/
├── main/
│ ├── java/com/AndersonJJR/literalura/
│ │ ├── LiteraluraApplication.java # Classe principal (Spring Boot)
│ │ ├── model/
│ │ │ ├── Livro.java # Entidade Livro (@Entity)
│ │ │ ├── Autor.java # Entidade Autor (@Entity)
│ │ │ ├── dados/
│ │ │ │ ├── DadosLivro.java # Record DTO da API
│ │ │ │ └── DadosAutor.java # Record DTO da API
│ │ ├── repository/
│ │ │ ├── LivroRepository.java # Spring Data JPA
│ │ │ └── AutorRepository.java # Spring Data JPA
│ │ ├── service/
│ │ │ ├── LiteraluraService.java # Consome API Gutendex
│ │ │ └── ConverteDados.java # Converte JSON → Entidades
│ │ └── principal/
│ │ └── Principal.java # Menu interativo Console
│ └── resources/
│ └── application.properties # Configurações banco/API
└── test/ # Testes unitários (opcional)
```

**Responsabilidades por pacote:**
- `model` → Entidades JPA + DTOs da API
- `repository` → Consultas SQL customizadas
- `service` → Lógica de negócio + consumo HTTP
- `principal` → Interface de usuário (Scanner)
