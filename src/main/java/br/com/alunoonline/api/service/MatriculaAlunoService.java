package br.com.alunoonline.api.service;


import br.com.alunoonline.api.enums.MatriculaAlunoStatusEnum;
import br.com.alunoonline.api.model.MatriculaAluno;
import br.com.alunoonline.api.repository.MatriculaAlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;


@Service
public class MatriculaAlunoService {

    @Autowired
    MatriculaAlunoRepository matriculaAlunoRepository;



    public void criarMatricula(MatriculaAluno matriculaAluno) {
        matriculaAluno.setStatus(MatriculaAlunoStatusEnum.MATRICULADO);
        matriculaAlunoRepository.save(matriculaAluno);

    }
    public List<MatriculaAluno> listarTodasMatriculaAluno(){
        return matriculaAlunoRepository.findAll();
    }

    public Optional<MatriculaAluno>buscarMatriculaAlunoPorId(long id){
        return matriculaAlunoRepository.findById(id);
    }

    public void deletarMatriculaAlunoPorId(Long id) {
        Optional<MatriculaAluno> matriculaAluno = matriculaAlunoRepository.findById(id);
        if (matriculaAluno.isPresent()) {
            matriculaAlunoRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public void atualizarMatriculaAlunoPorId(Long id, MatriculaAluno matriculaAluno){
        Optional <MatriculaAluno> matriculaAlunoDoBancoDeDados = buscarMatriculaAlunoPorId(id);
        if (matriculaAlunoDoBancoDeDados.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "MatriculaAluno Não encotrado no Banco de Dados");
        }
        MatriculaAluno matriculaAlunoParaEditar= matriculaAlunoDoBancoDeDados.get();
        matriculaAlunoParaEditar.setAluno(matriculaAluno.getAluno());
        matriculaAlunoParaEditar.setDisciplina(matriculaAluno.getDisciplina());
    }

    public void trancarMatricula(Long matriculaAlunoId) {
        // Antes de trancar, verifica se a matricula existe
        MatriculaAluno matriculaAluno = matriculaAlunoRepository.findById(matriculaAlunoId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Matricula Aluno não encontrada"));

        // So vai deixar trancar, se o status ATUAL for MATRICULAOD
        if (matriculaAluno.getStatus().equals(MatriculaAlunoStatusEnum.MATRICULADO)){
            matriculaAluno.setStatus(MatriculaAlunoStatusEnum.TRANCADO);
            matriculaAlunoRepository.save(matriculaAluno);
        }else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Só é possivel trancar com o status MATRICULADO");

        }
    }

}
