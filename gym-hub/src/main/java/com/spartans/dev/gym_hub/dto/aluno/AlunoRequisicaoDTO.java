package com.spartans.dev.gym_hub.dto.aluno;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.sql.Date;

public record AlunoRequisicaoDTO(

        @NotBlank(message = "O nome não pode estar em branco")
        @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
        String nome,

        @NotNull()
        @Positive(message = "A altura deve ser um valor positivo")
        Double altura,

        @NotNull()
        @Positive(message = "O peso deve ser um valor positivo")
        Double massaCorporal,

        @NotNull()
        @Past(message = "A data de nascimento deve ser uma data no passado")
        Date nascimento,

        @NotBlank(message = "O usuário é obrigatório")
        String user,

        @NotBlank(message = "A senha é obrigatória")
        @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
        String senha,

        @NotNull()
        Date dataCadastro,

        String imc,

        @NotBlank(message = "O CPF é obrigatório")
        @Pattern(regexp = "\\d{11}", message = "O CPF deve conter exatamente 11 dígitos numéricos")
        String cpf


) {
}
