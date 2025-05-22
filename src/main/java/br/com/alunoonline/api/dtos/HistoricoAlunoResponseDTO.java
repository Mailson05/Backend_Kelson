package br.com.alunoonline.api.dtos;

import lombok.Data;

import java.util.List;
@Data

public class HistoricoAlunoResponseDTO {
    private String nomeAluno;
    private String emailAluno;
    private String cpfAluno;
    private List<DisciplinasAlunoResponseDTO> disciplinas;

    public String getNomeAluno() {
        return nomeAluno;
    }

    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
    }

    public String getEmailAluno() {
        return emailAluno;
    }

    public void setEmailAluno(String emailAluno) {
        this.emailAluno = emailAluno;
    }

    public String getCpfAluno() {
        return cpfAluno;
    }

    public void setCpfAluno(String cpfAluno) {
        this.cpfAluno = cpfAluno;
    }

    public List<DisciplinasAlunoResponseDTO> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<DisciplinasAlunoResponseDTO> disciplinas) {
        this.disciplinas = disciplinas;
    }
}
