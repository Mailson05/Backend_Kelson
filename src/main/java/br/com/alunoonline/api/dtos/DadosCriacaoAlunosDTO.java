package br.com.alunoonline.api.dtos;

public record DadosCriacaoAlunosDTO(
        String nome,
        String cpf,
        String email,
        String senha
) {
}
