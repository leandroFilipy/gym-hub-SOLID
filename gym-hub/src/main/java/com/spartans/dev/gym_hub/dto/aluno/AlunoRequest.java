package com.spartans.dev.gym_hub.dto.aluno;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDateTime;
import java.util.Date;

public record AlunoRequest(

        @Email(message = "Este formato de email esta incorreto")
        @NotBlank(message = "O campo não deve ser nulo")
        String nome,
        @Positive(message = "A altura deve ser positiva")
        @NotBlank(message = "O campo não deve ser nulo")
        double altura,

        @Positive(message = "O peso corporal não compátivel")
        double massaCorporal,

        @PastOrPresent(message = "A data deve ser compátivel")
        @NotBlank(message = "O campo não deve ser nulo")
        Date nascimento,

        @NotBlank(message = "O campo não deve ser nulo")
        @Length(min = 6, max = 40)
        String user,

        @Length(min = 6, max = 40)
        @NotBlank(message = "O campo não deve ser nulo")
        String senha,

        @PastOrPresent(message = "Data incompátivel")
        LocalDateTime dataCadastro,

        @CPF(message = "Formato de CPF incorreto")
        @NotBlank(message = "O campo não deve ser nulo")
        String cpf
) {
}
