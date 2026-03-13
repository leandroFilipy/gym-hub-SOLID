package com.spartans.dev.gym_hub.dto.equipamento;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EquipamentoRequest (

        @NotBlank(message = "O nome do equipamento é obrigatório")
        @Size(min = 2, max = 100, message = "O nome deve ter entre 2 e 100 caracteres")
        String nome,

        @NotBlank(message = "A descrição do equipamento não pode estar em branco")
        @Size(min = 5, message = "A descrição deve ser um pouco mais detalhada")
        String descricao,

        @NotBlank(message = "Informe a musculatura/anatomia que este equipamento trabalha")
        String anatomia
){
}
