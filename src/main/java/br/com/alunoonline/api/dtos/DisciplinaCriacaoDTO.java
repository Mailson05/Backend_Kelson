package br.com.alunoonline.api.dtos;

import br.com.alunoonline.api.model.Professor;

public record DisciplinaCriacaoDTO(
        String nome,
        Integer cargaHorario,
        Professor professor
) {
}
