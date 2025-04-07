package br.com.alunoonline.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor // construtores sem argumentos
@AllArgsConstructor // construtores com argumentos
// @Data   -  ja faz get e setter
@Table(name = "aluno") //criar tabela no banco de dados s
@Entity //Informar que e uma entidade
public class Aluno {

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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Id // identificando como chave primario
    @GeneratedValue(strategy = GenerationType.AUTO) // no pessoal usar identity
    private Long id;
    private String nome;
    private String cpf;
    private String email;


}
