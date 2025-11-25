package br.com.alunoonline.api.dtos;

public record ProfessorCreateDTO(
        String nome,
        String cpf,
        String email,
        String senha
) {
}
