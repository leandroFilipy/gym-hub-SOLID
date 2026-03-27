package com.spartans.dev.gym_hub.mapper.aluno;

import com.spartans.dev.gym_hub.dto.aluno.AlunoRequest;
import com.spartans.dev.gym_hub.dto.aluno.AlunoResponse;
import com.spartans.dev.gym_hub.model.Aluno;

public interface IAlunoMapper {

    Aluno paraEntidade(AlunoRequest alunoRequest);
    AlunoResponse paraDTO(Aluno aluno);
}
