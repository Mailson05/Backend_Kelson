package br.com.alunoonline.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor // construtores sem argumentos
@AllArgsConstructor // construtores com argumentos
@Data //ja faz get e setter
@Table(name = "aluno") //criar tabela no banco de dados s
@Entity //Informar que e uma entidade
public class Aluno {
    private String nome;
    private String cpf;
    private String email;
    @Id // identificando como chave primario
    @GeneratedValue(strategy = GenerationType.AUTO) // no pessoal usar identity
    private Long id;

}
