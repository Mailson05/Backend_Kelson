package br.com.alunoonline.api.model;


import br.com.alunoonline.api.enums.MatriculaAlunoStatusEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "matricula_aluno")
@Entity
public class MatriculaAluno {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;


    @ManyToOne
    @JoinColumn(name = "disciplina_id")
    private Disciplina disciplina;

    private Double nota1;

    private Double nota2;


    @Enumerated(EnumType.STRING) // Dizendo que foi enumerado por String ( para aparecer la no banco )
    private MatriculaAlunoStatusEnum status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public Double getNota1() {
        return nota1;
    }

    public void setNota1(Double nota1) {
        this.nota1 = nota1;
    }

    public Double getNota2() {
        return nota2;
    }

    public void setNota2(Double nota2) {
        this.nota2 = nota2;
    }

    public MatriculaAlunoStatusEnum getStatus() {
        return status;
    }

    public void setStatus(MatriculaAlunoStatusEnum status) {
        this.status = status;
    }
}
