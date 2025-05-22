package br.com.alunoonline.api.service;


import br.com.alunoonline.api.dtos.AtualizarNotasRequestDTO;
import br.com.alunoonline.api.dtos.DisciplinasAlunoResponseDTO;
import br.com.alunoonline.api.dtos.HistoricoAlunoResponseDTO;
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
import java.util.stream.Collectors;


@Service
public class MatriculaAlunoService {

    private static final Double MEDIA_PARA_APROVACAO = 7.0;
    private  static final Integer QTD_NOTAS = 2;

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

    public void atualizarNotas(Long matriculaAlunoId, AtualizarNotasRequestDTO atualizarNotasRequestDTO){

        MatriculaAluno matriculaAluno = buscarMatriculaOuLancarExcecao(matriculaAlunoId);

        if (atualizarNotasRequestDTO.getNota1() != null){
            matriculaAluno.setNota1(atualizarNotasRequestDTO.getNota1());
        }

        if (atualizarNotasRequestDTO.getNota2() != null){
            matriculaAluno.setNota2(atualizarNotasRequestDTO.getNota2());
        }

        calcularMediaEModificarStatus(matriculaAluno);
        matriculaAlunoRepository.save(matriculaAluno);

    }
    public HistoricoAlunoResponseDTO emitirHistorico(Long alunoId) {
        List<MatriculaAluno> matriculaAlunos = matriculaAlunoRepository.findByAlunoId(alunoId);

        if (matriculaAlunos.isEmpty()) { //se a matricula estiver vazia, eu lanço um nova excecao
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Esse aluno não possui matriculas");
        }

        HistoricoAlunoResponseDTO historicoAluno = new HistoricoAlunoResponseDTO();
        historicoAluno.setNomeAluno(matriculaAlunos.get(0).getAluno().getNome());
        historicoAluno.setEmailAluno(matriculaAlunos.get(0).getAluno().getEmail());
        historicoAluno.setCpfAluno(matriculaAlunos.get(0).getAluno().getCpf());

        List<DisciplinasAlunoResponseDTO> disciplinas = matriculaAlunos.stream() // varrer lista com poucas linhas
                .map(this::mapearParaDisciplinasAlunoResponseDTO)
                .toList();

        historicoAluno.setDisciplinas(disciplinas);
        return historicoAluno;

    }


    private MatriculaAluno buscarMatriculaOuLancarExcecao(Long matriculaAlunoId) {
        return matriculaAlunoRepository.findById(matriculaAlunoId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, //404
                        "Matricula do Aluno não encontrada"));
    }


    private void calcularMediaEModificarStatus(MatriculaAluno matriculaAluno){
        Double nota1 = matriculaAluno.getNota1();
        Double nota2 = matriculaAluno.getNota2();

        if (nota1 != null && nota2 != null) {
            Double media = (nota1 + nota2) / QTD_NOTAS;
            matriculaAluno.setStatus(media >= MEDIA_PARA_APROVACAO ? MatriculaAlunoStatusEnum.APROVADO : MatriculaAlunoStatusEnum.REPROVADO);
        }
    }

    private DisciplinasAlunoResponseDTO mapearParaDisciplinasAlunoResponseDTO(MatriculaAluno matriculaAluno) {
        DisciplinasAlunoResponseDTO response = new DisciplinasAlunoResponseDTO();
        response.setNomeDisciplina(matriculaAluno.getDisciplina().getNome());
        response.setNomeProfessor(matriculaAluno.getDisciplina().getProfessor().getNome());
        response.setNota1(matriculaAluno.getNota1());
        response.setNota2(matriculaAluno.getNota2());
        response.setMedia(calcularMedia(matriculaAluno.getNota1(), matriculaAluno.getNota2()));
        response.setStatus(matriculaAluno.getStatus());
        return response;

    }

    private Double calcularMedia( Double nota1, Double nota2 ) {

        return (nota1 != null && nota2 != null) ? (nota1+nota2) / QTD_NOTAS : null;

    }
}
