package com.spartans.dev.gym_hub.dto.professor;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;

public record ProfessorRequisicaoDTO(
        @NotBlank(message = "O nome do professor é obrigatório")
        @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
        String nome,

        @NotBlank(message = "O CREF é obrigatório")
        @Pattern(regexp = "\\d{5,10}-[GA]/[A-Z]{2}", message = "O formato do CREF deve ser 00000-G/SP")
        String cref,

        @NotBlank(message = "A especialidade não pode estar em branco")
        String especialidade,

        @NotBlank(message = "O campo 'sobre' é necessário para o perfil")
        @Size(max = 500, message = "O resumo deve ter no máximo 500 caracteres")
        String sobre,

        @DecimalMin(value = "0.0")
        @DecimalMax(value = "5.0", message = "A avaliação máxima é 5.0")
        Double avaliacao,

        @NotBlank(message = "O CPF é obrigatório")
        @Pattern(regexp = "\\d{11}", message = "O CPF deve conter apenas os 11 dígitos numéricos")
        String cpf


) {
}
