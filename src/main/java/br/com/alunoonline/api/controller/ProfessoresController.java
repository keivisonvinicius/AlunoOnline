package br.com.alunoonline.api.controller;

import br.com.alunoonline.api.model.Professores;
import br.com.alunoonline.api.service.AlunoService;
import br.com.alunoonline.api.service.ProfessoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/professores")
public class ProfessoresController {
    @Autowired
    ProfessoresService professoresService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarProfessor(@RequestBody Professores professor){
        professoresService.criarProfessor(professor);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Professores> listarTodosProfessores(){
        return professoresService.listarTodosProfessores();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Professores> buscarProfessorPorId(@PathVariable Long id){
        return professoresService.buscarProfessorPorId(id);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarProfessorPorId(@PathVariable Long id){
         professoresService.deletarProfessorPorId(id);
    }
}
