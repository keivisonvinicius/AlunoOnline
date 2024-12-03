# Aluno Online API

A **API Aluno Online** é uma plataforma de gestão de alunos, professores, disciplinas e matrículas desenvolvida com **Spring Boot**. Esta API permite a criação, atualização, listagem e exclusão de alunos, professores e disciplinas, além de fornecer funcionalidades para matrícula de alunos em disciplinas e gerenciamento de notas.

Com suporte a **PostgreSQL**, esta API oferece uma maneira simples e eficaz de gerenciar informações acadêmicas, proporcionando um sistema eficiente para escolas ou universidades.

## Funcionalidades

- Cadastro e gerenciamento de alunos
- Cadastro e gerenciamento de professores
- Cadastro e gerenciamento de disciplinas
- Matrícula de alunos em disciplinas
- Atualização de notas e histórico dos alunos
- Suporte a trancamento de matrículas

## Tecnologias Utilizadas

- **Spring Boot**
- **Hibernate/JPA**
- **PostgreSQL** (pode ser configurado para outros bancos)
- **Maven**
- **Swagger/OpenAPI**
- **Java 17**

---
### Arquitetura de Pastas

O projeto é estruturado em diversas camadas, cada uma responsável por uma parte específica do sistema, garantindo uma separação de responsabilidades clara. A arquitetura utilizada neste projeto é baseada nos seguintes componentes:

#### 1. **Model (`br.com.alunoonline.api.model`)**
   - A camada **Model** contém as classes que representam os dados principais do sistema. Essas classes são usadas para mapear os dados que são persistidos no banco de dados.
   - **Exemplo: `Aluno.java`**
     - A classe `Aluno` é anotada com `@Entity`, indicando que ela é uma entidade do banco de dados.
     - Possui atributos como `id`, `nome`, `email` e `cpf`.
     - Utiliza anotações do **Lombok** (`@Data`, `@NoArgsConstructor`, `@AllArgsConstructor`) para reduzir a necessidade de código boilerplate (getters, setters, construtores).
     - A classe `Aluno` é mapeada para uma tabela no banco de dados com a ajuda do **JPA (Java Persistence API)**.

#### 2. **Repository (`br.com.alunoonline.api.repository`)**
   - A camada **Repository** é responsável por fornecer acesso aos dados, ou seja, interage diretamente com o banco de dados.
   - **Exemplo: `AlunoRepository.java`**
     - O `AlunoRepository` é uma interface que estende `JpaRepository<Aluno, Long>`, o que significa que ele possui operações prontas de CRUD (Create, Read, Update, Delete) para a entidade `Aluno`.
     - O **Spring Data JPA** cuida de todas as implementações dos métodos para acesso aos dados, como `findAll()`, `findById()`, `save()`, `deleteById()`.

#### 3. **Service (`br.com.alunoonline.api.service`)**
   - A camada **Service** contém a lógica de negócios. Ela é responsável por processar as regras e operações do sistema.
   - **Exemplo: `AlunoService.java`**
     - O `AlunoService` contém métodos como `criarAluno()`, `listarTodosAlunos()`, `buscarAlunoPorId()`, `deletarAlunoPorId()`, e `atualizarAlunoPorId()`.
     - O serviço faz uso do **AlunoRepository** para manipular os dados da entidade `Aluno`, mas também inclui verificações de regras de negócios, como garantir que um aluno exista no banco antes de atualizá-lo.
     - Caso algum aluno não seja encontrado ao buscar ou atualizar, é lançada uma exceção `ResponseStatusException` para retornar um código HTTP adequado.

#### 4. **Controller (`br.com.alunoonline.api.controller`)**
   - A camada **Controller** é responsável por receber as requisições HTTP, chamar os serviços necessários e devolver as respostas ao cliente.
   - **Exemplo: `AlunoController.java`**
     - O `AlunoController` define os endpoints REST para a entidade `Aluno`, como:
       - `POST /alunos` para criar um novo aluno.
       - `GET /alunos` para listar todos os alunos.
       - `GET /alunos/{id}` para buscar um aluno específico pelo ID.
       - `PUT /alunos/{id}` para atualizar os dados de um aluno existente.
       - `DELETE /alunos/{id}` para excluir um aluno.
     - Cada método da controller usa anotações como `@PostMapping`, `@GetMapping`, `@PutMapping`, `@DeleteMapping`, e `@ResponseStatus` para definir o comportamento de cada operação.

---

### Fluxo de Dados

1. **Requisição HTTP**: O cliente faz uma requisição HTTP para a **Controller**.
2. **Controller**: A **Controller** recebe a requisição, valida os dados, e encaminha para o **Service** correspondente.
3. **Service**: O **Service** processa a lógica de negócios, realiza interações com o banco de dados via **Repository** e retorna os resultados.
4. **Resposta HTTP**: A **Controller** retorna a resposta HTTP apropriada para o cliente.

---

### Exemplo de Fluxo

1. O usuário envia uma requisição `POST /alunos` com os dados do novo aluno.
2. A **Controller** (`AlunoController`) chama o método `criarAluno()` da **Service** (`AlunoService`).
3. O **Service** usa o **Repository** (`AlunoRepository`) para salvar os dados do aluno no banco de dados.
4. A **Controller** retorna uma resposta com status `201 CREATED`.

---


# Endpoints

## Alunos

| Método | Endpoint         | Descrição                          | Exemplo de Body (JSON)                                         |
|--------|------------------|------------------------------------|---------------------------------------------------------------|
| POST   | /alunos          | Cria um novo aluno.               | { "nome": "João", "cpf": "123456789", "email": "joao@email.com" } |
| GET    | /alunos          | Lista todos os alunos.            | -                                                             |
| GET    | /alunos/{id}     | Busca um aluno pelo ID.           | -                                                             |
| PUT    | /alunos/{id}     | Atualiza os dados de um aluno.    | { "nome": "João Silva", "cpf": "987654321", "email": "joaosilva@email.com" } |
| DELETE | /alunos/{id}     | Deleta um aluno pelo ID.          | -                                                             |

## Professores

| Método | Endpoint         | Descrição                          | Exemplo de Body (JSON)                                         |
|--------|------------------|------------------------------------|---------------------------------------------------------------|
| POST   | /professores     | Cria um novo professor.            | { "nome": "Maria", "cpf": "123456789", "email": "maria@email.com" } |
| GET    | /professores     | Lista todos os professores.       | -                                                             |
| GET    | /professores/{id}| Busca um professor pelo ID.       | -                                                             |
| PUT    | /professores/{id}| Atualiza os dados de um professor. | { "nome": "Maria Silva", "cpf": "987654321", "email": "mariasilva@email.com" } |
| DELETE | /professores/{id}| Deleta um professor pelo ID.      | -                                                             |

## Disciplinas

| Método | Endpoint             | Descrição                                | Exemplo de Body (JSON)                                     |
|--------|----------------------|------------------------------------------|-----------------------------------------------------------|
| POST   | /disciplinas         | Cria uma nova disciplina.                | { "nome": "Matemática", "professorId": 1 }                 |
| GET    | /disciplinas         | Lista todas as disciplinas.              | -                                                         |
| GET    | /disciplinas/{id}    | Busca uma disciplina pelo ID.           | -                                                         |
| DELETE | /disciplinas/{id}    | Deleta uma disciplina pelo ID.          | -                                                         |
| GET    | /disciplinas/professor/{professorId} | Lista disciplinas de um professor pelo ID. | -                                                         |

## Matrículas

| Método | Endpoint                         | Descrição                          | Exemplo de Body (JSON)                                         |
|--------|----------------------------------|------------------------------------|---------------------------------------------------------------|
| POST   | /matriculas                      | Cria uma nova matrícula.           | { "alunoId": 1, "disciplinaId": 2 }                            |
| PUT    | /matriculas/{id}/trancar         | Tranca uma matrícula (status muda para "TRANCADO"). | -                                                             |
| PUT    | /matriculas/{id}/notas           | Atualiza as notas de uma matrícula.| { "nota1": 8.5, "nota2": 9.0 }                                 |
| GET    | /matriculas/aluno/{alunoId}/historico | Emite o histórico de um aluno.   | -                                                             |

---

