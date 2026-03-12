package com.spartans.dev.gym_hub.dto;

import java.util.Date;

public record AlunoResponse(
        long id,
        String nome,
        double altura,
        double massaCorporal,
        Date dataNascimento,
        String user,
        String senha,
        Date dataCadastro,
        String imc,
        String cpf
) {
}
