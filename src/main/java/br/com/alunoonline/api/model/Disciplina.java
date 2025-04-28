package br.com.alunoonline.api.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
//@Data
@Table(name = "disciplina")
@Entity
public class Disciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;

    private String nome;

    private Integer cargaHoraria;

    @ManyToOne// Relacionamento ( muitas disciplinas para um professor)
    @JoinColumn(name = "professor_id") //chave estrageira ( nome da tabela referenciando com chave primaria, junção de coluna)
    private Professor professor; // Relacionamento JPA ( fazendo com que o banco diga 1 para N )

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(Integer cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }
}
