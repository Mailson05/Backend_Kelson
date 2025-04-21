package br.com.alunoonline.api.service;


import br.com.alunoonline.api.model.Professor;
import br.com.alunoonline.api.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;


@Service
public class ProfessorService {

    @Autowired
    ProfessorRepository professorRepository;

    public void criarProfessor(Professor professor){
        professorRepository.save(professor);

    }

    public List<Professor> listarTodosProfessores(){
        return professorRepository.findAll();

    }

    public Optional<Professor> buscarProfessorPorId(Long id){
        return professorRepository.findById(id);

    }

    public void deletarProfessorPorId(Long id){
        Optional<Professor> professor=professorRepository.findById(id);
        if (professor.isPresent()){
                professorRepository.deleteById(id);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Não encontrato o Professor");
        }
    }

    public void atualizarProfessorPorId(Long id,Professor professor){
        Optional<Professor> professorDoBancoDeDados = buscarProfessorPorId(id);

        if (professorDoBancoDeDados.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Professor não encontrado no anco de dados");
        }

        Professor professorParaEditar = professorDoBancoDeDados.get();

        professorParaEditar.setNome(professor.getNome());
        professorParaEditar.setCpf(professor.getCpf());
        professorParaEditar.setEmail(professor.getEmail());

        professorRepository.save(professorParaEditar);

    }
}
