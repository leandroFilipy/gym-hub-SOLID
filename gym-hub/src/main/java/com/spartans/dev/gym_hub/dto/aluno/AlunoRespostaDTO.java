package com.spartans.dev.gym_hub.dto.aluno;

import java.sql.Date;

public record AlunoRespostaDTO(

        Long id,
        String nome,
        Double altura,
        Double massaCorporal,
        Date nascimento,
        String user,
        String senha,
        Date dataCadastro,
        String imc,
        String cpf

) {
}
