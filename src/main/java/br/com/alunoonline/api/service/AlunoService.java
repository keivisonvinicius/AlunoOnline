package br.com.alunoonline.api.service;

import br.com.alunoonline.api.model.Aluno;
import br.com.alunoonline.api.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@Service
public class AlunoService {

    @Autowired
    AlunoRepository alunoRepository;

    public void criarAluno(Aluno aluno) {
        alunoRepository.save(aluno);
    }

    public List<Aluno> listarTodosAlunos() {
        return alunoRepository.findAll();
    }

    public Optional<Aluno> buscarAlunoPorId(Long id) {
        return alunoRepository.findById(id);
    }

    public void deletarAlunoPorId(Long id) {
        alunoRepository.deleteById(id);
    }

    public void atualizarAlunoPorId(Long id, Aluno aluno) {
        Optional<Aluno> alunoDoBancoDeDados = buscarAlunoPorId(id);

        if (alunoDoBancoDeDados.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não encontrado no banco de dados");
        }

        // Atualiza os atributos do aluno
        Aluno alunoEditado = alunoDoBancoDeDados.get();
        alunoEditado.setNome(aluno.getNome());
        alunoEditado.setCpf(aluno.getCpf());
        alunoEditado.setEmail(aluno.getEmail());

        // Salva o aluno atualizado
        alunoRepository.save(alunoEditado);
    }

    public List<Aluno> pesquisar(String nome, String email, String cpf) {
        return alunoRepository.findAll(new Specification<Aluno>() {
            @Override
            public Predicate toPredicate(Root<Aluno> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();

                if (nome != null && !nome.isEmpty()) {
                    predicates.add(cb.like(cb.lower(root.get("nome")), "%" + nome.toLowerCase() + "%"));
                }

                if (email != null && !email.isEmpty()) {
                    predicates.add(cb.like(cb.lower(root.get("email")), "%" + email.toLowerCase() + "%"));
                }

                if (cpf != null && !cpf.isEmpty()) {
                    predicates.add(cb.equal(root.get("cpf"), cpf));
                }

                return cb.and(predicates.toArray(new Predicate[0]));
            }
        });
    }
}


