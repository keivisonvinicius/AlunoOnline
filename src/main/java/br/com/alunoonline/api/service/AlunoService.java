package br.com.alunoonline.api.service;

import br.com.alunoonline.api.model.Aluno;
import br.com.alunoonline.api.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    @Autowired
    AlunoRepository alunoRepository;

    public void criarAluno(Aluno aluno){
        alunoRepository.save(aluno);
    }

    public List<Aluno> listarTodosAlunos(){
        return alunoRepository.findAll();
    }

    public Optional<Aluno>  buscarAlunoPorId(Long id){
        return alunoRepository.findById(id);
    }

    public void deletarAlunoPorId(Long id){
        alunoRepository.deleteById(id);
    }
    
    public void atualizarAlunoPorId(Long id, Aluno aluno){
        // PRIMEIRO PASSO: VER SE O ALUNO EXISTE NO BD
        Optional<Aluno> alunoDoBancoDeDados = buscarAlunoPorId(id);

        //E SE NÃO EXISTE???
        if(alunoDoBancoDeDados.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ALUNO NÃO ENCONTRADO NO BANCO DE DADOS");
        }
        //SE CHEGAR AQUI, SIGNIFICA QUE O ALUNO EXISTE ENTAO
        //VOU ARMAZENÁ-LO EM UMA VARIÁVEL
        Aluno alunoEditado = alunoDoBancoDeDados.get();

        //COM ESSE ALUNO EEDITADO DE CIMA, FAÇO
        // OS SETS NECESSÁRIOS PARA ATUALIZAR OS ATRIBUTOS DELE.
        alunoEditado.setNome(aluno.getNome());
        alunoEditado.setCpf(aluno.getCpf());
        alunoEditado.setEmail(aluno.getEmail());

        //COM O ALUNO TOTALMENTE EDITADO ACIMA
        // EU DEVOLVO ELE TOTALMENTE ATUALIZADO/EDITADO PARA O BANCO DE DADOS
    }
}