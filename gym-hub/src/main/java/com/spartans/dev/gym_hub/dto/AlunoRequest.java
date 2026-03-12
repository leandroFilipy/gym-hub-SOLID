package com.spartans.dev.gym_hub.dto;

import jakarta.persistence.Column;

import java.util.Date;

public record AlunoRequest(
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
