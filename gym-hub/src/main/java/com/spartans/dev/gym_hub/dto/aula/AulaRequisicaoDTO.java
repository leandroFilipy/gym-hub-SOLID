package com.spartans.dev.gym_hub.dto.aula;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AulaRequisicaoDTO(

        @NotBlank(message = "O nome da aula é obrigatório")
        @Size(min = 5, max = 50, message = "O nome da aula deve ter entre 5 e 50 caracteres")
        String nome,

        @NotBlank(message = "A descrição não pode estar em branco")
        @Size(min = 10, message = "A descrição deve ter pelo menos 10 caracteres para ser informativa")
        String descricao,

        @NotBlank(message = "A duração deve ser informada (ex: 60 min ou 01:00)")
        String duracao

) {
}
