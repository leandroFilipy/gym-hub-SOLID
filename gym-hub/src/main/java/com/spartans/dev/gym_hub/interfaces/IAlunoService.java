package com.spartans.dev.gym_hub.interfaces;

import com.spartans.dev.gym_hub.dto.aluno.AlunoRequest;
import com.spartans.dev.gym_hub.dto.aluno.AlunoResponse;

import java.util.List;

public interface IAlunoService {
    AlunoResponse create(AlunoRequest alunoRequest);
    List<AlunoResponse> listAll();
    AlunoResponse findById(long id);
    AlunoResponse update(long id, AlunoRequest alunoRequest);
    void delete(long id);


}
