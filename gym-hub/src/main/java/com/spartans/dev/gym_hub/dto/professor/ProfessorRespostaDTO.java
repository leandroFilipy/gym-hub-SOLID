package com.spartans.dev.gym_hub.dto.professor;

public record ProfessorRespostaDTO(


        Long id,

        String nome,

        String cref,

        String especialidade,

        String sobre,

        Double avaliacao,

        String cpf


) {
}
