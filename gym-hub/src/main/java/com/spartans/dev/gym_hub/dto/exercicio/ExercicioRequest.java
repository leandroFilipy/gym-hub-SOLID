package com.spartans.dev.gym_hub.dto.exercicio;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record ExercicioRequest(

        @NotBlank(message = "O nome do exercício é obrigatório")
        String nome,

        @NotBlank(message = "A descrição não pode estar em branco")
        String descricao,

        @NotBlank(message = "A URL do vídeo de instrução é obrigatória")
        String videoURL,

        @Positive(message = "O número de séries deve ser maior que zero")
        @Min(value = 1, message = "O mínimo de séries é 1")
        int series,

        @Positive(message = "O número de repetições deve ser maior que zero")
        @Min(value = 1, message = "O mínimo de repetições é 1")
        int repeticoes,

        @NotBlank(message = "Informe o músculo alvo deste exercício")
        String musculoAlvo

) {
}