package br.com.alunoonline.api.controller;

import br.com.alunoonline.api.model.Disciplina;
import br.com.alunoonline.api.service.DisciplinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/disciplinas")
public class DisciplinaController {
    @Autowired
    DisciplinaService disciplinaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarDisciplina(@RequestBody Disciplina disciplina){
        disciplinaService.criarDisciplina(disciplina);
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Disciplina> listarTodasDisciplinas(){
        return disciplinaService.listarDisciplinas();
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Disciplina> buscarDisciplinaPorId(Long id){
        return disciplinaService.buscarDisciplinasPorId(id);
    }
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarDisciplina(@PathVariable Long id){
        disciplinaService.deletarDisciplinaPorId(id);
    }

    @GetMapping("/professor/{professorId}")
    @ResponseStatus(HttpStatus.OK)
    public List <Disciplina> listarDisciplinasDoProf(@PathVariable Long professorId){
        return disciplinaService.listarDisciplinasDoProf(professorId);
    }
}
