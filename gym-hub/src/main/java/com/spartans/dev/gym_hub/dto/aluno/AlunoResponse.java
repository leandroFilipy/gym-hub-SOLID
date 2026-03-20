package com.spartans.dev.gym_hub.dto.aluno;

import java.time.LocalDateTime;
import java.util.Date;

public record AlunoResponse(
        long id,
        String nome,
        double altura,
        double massaCorporal,
        Date nascimento,
        String user,
        String senha,
        LocalDateTime dataCadastro,
        double imc,
        String cpf
) {
}
