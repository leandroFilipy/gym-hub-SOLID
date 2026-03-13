package com.spartans.dev.gym_hub.mapper;

import com.spartans.dev.gym_hub.dto.aula.AulaRequisicaoDTO;
import com.spartans.dev.gym_hub.dto.aula.AulaRespostaDTO;
import com.spartans.dev.gym_hub.dto.professor.ProfessorRequisicaoDTO;
import com.spartans.dev.gym_hub.dto.professor.ProfessorRespostaDTO;
import com.spartans.dev.gym_hub.model.Aula;
import com.spartans.dev.gym_hub.model.Professor;
import jakarta.persistence.Entity;
import org.springframework.stereotype.Component;

@Component
public class ProfessorMapper {

    public Professor paraEntidade(ProfessorRequisicaoDTO professorRequisicaoDTO) {
        return new Professor(professorRequisicaoDTO.nome(), professorRequisicaoDTO.cref(), professorRequisicaoDTO.especialidade(), professorRequisicaoDTO.sobre(), professorRequisicaoDTO.avaliacao(), professorRequisicaoDTO.cpf());
    }

    public ProfessorRespostaDTO paraRespostaDTO(Professor professor) {
        return new ProfessorRespostaDTO(professor.getId(), professor.getNome(), professor.getCref(), professor.getEspecialidade(), professor.getSobre(), professor.getAvaliacao(), professor.getCpf());
    }

}
