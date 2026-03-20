package com.spartans.dev.gym_hub.interfaces;

import com.spartans.dev.gym_hub.dto.professor.ProfessorRequest;
import com.spartans.dev.gym_hub.dto.professor.ProfessorResponse;
import com.spartans.dev.gym_hub.model.Professor;

public interface IProfessorMapper {
    Professor paraEntidade(ProfessorRequest professorRequest);
    ProfessorResponse paraDTO(Professor professor);
}
