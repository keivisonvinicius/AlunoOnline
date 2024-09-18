package br.com.alunoonline.api.service;

import br.com.alunoonline.api.model.Professores;
import br.com.alunoonline.api.repository.ProfessoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessoresService {
    @Autowired
    ProfessoresRepository professoresRepository;

    public void criarProfessor(Professores professor){
        professoresRepository.save(professor);
    }
    public List<Professores> listarTodosProfessores(){
        return professoresRepository.findAll();
    }
    public Optional<Professores> buscarProfessorPorId(Long id){
        return professoresRepository.findById(id);
    }
    public void deletarProfessorPorId(Long id){
        professoresRepository.deleteById(id);
    }
    public void atualizarProfessorPorId(Long id, Professores professor){
        Optional<Professores> professorDoBancoDeDados = buscarProfessorPorId(id);

        if(professorDoBancoDeDados.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "PROFESSOR NÃO ENCONTRADO NO BANCO DE DADOS");
        }
        Professores professorEditado= professorDoBancoDeDados.get();

        professorEditado.setNome(professor.getNome());
        professorEditado.setCpf(professor.getCpf());
        professorEditado.setEmail(professor.getEmail());
    }
}
