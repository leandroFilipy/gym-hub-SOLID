package com.spartans.dev.gym_hub.service.professorService;

import com.spartans.dev.gym_hub.dto.professor.ProfessorRequest;
import com.spartans.dev.gym_hub.dto.professor.ProfessorResponse;

import java.util.List;

public interface IProfessorService {
    ProfessorResponse create(ProfessorRequest professorRequest);
    List<ProfessorResponse> listAll();
    ProfessorResponse findById(long id);
    ProfessorResponse update(long id, ProfessorRequest professorRequest);
    void delete (long id);
}
