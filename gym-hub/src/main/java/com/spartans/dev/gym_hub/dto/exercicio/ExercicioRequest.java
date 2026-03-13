package com.spartans.dev.gym_hub.dto.exercicio;

public record ExercicioRequest(

        String nome,
        String descricao,
        String videoURL,
        int series,
        int repeticoes,
        String musculoAlvo
) {
}