package br.com.alunoonline.api.service;


import br.com.alunoonline.api.dtos.DadosCriacaoAlunosDTO;
import br.com.alunoonline.api.dtos.ProfessorCreateDTO;
import br.com.alunoonline.api.model.Aluno;
import br.com.alunoonline.api.model.Professor;
import br.com.alunoonline.api.repository.ProfessorRepository;
import br.com.alunoonline.api.usuario.Role;
import br.com.alunoonline.api.usuario.Usuario;
import br.com.alunoonline.api.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;


@Service
public class ProfessorService {

    @Autowired
    ProfessorRepository professorRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    UsuarioRepository usuarioRepository;

    public void criarProfessor(ProfessorCreateDTO professorCreateDTO){
        Professor novoProfessor = new Professor();
        novoProfessor.setNome(professorCreateDTO.nome());
        novoProfessor.setCpf(professorCreateDTO.cpf());
        novoProfessor.setEmail(professorCreateDTO.email());

        professorRepository.save(novoProfessor);

        Usuario novoUsuario = new Usuario();
        novoUsuario.setLogin(professorCreateDTO.email());
        novoUsuario.setSenha(passwordEncoder.encode(professorCreateDTO.senha()));
        novoUsuario.setRole(Role.PROFESSOR);

        usuarioRepository.save(novoUsuario);
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
