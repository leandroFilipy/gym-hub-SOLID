package com.spartans.dev.gym_hub.mapper.professor;

import com.spartans.dev.gym_hub.dto.professor.ProfessorRequest;
import com.spartans.dev.gym_hub.dto.professor.ProfessorResponse;
import com.spartans.dev.gym_hub.model.Professor;
import org.springframework.stereotype.Component;

@Component
public class ProfessorMapper implements IProfessorMapper {

    @Override
    public Professor paraEntidade(ProfessorRequest professorRequest){

        return new Professor(
                professorRequest.nome(),
                professorRequest.cref(),
                professorRequest.especialidade(),
                professorRequest.sobre(),
                professorRequest.avaliacao(),
                professorRequest.cpf()
        );
    }

    @Override
    public ProfessorResponse paraDTO(Professor professor){

        return new ProfessorResponse(
                professor.getId(),
                professor.getNome(),
                professor.getCref(),
                professor.getEspecialidade(),
                professor.getSobre(),
                professor.getAvaliacao(),
                professor.getCpf()
        );
    }
}
