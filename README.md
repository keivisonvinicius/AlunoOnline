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

