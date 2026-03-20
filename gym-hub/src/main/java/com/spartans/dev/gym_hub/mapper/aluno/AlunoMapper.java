package com.spartans.dev.gym_hub.mapper.aluno;

import com.spartans.dev.gym_hub.dto.aluno.AlunoRequest;
import com.spartans.dev.gym_hub.dto.aluno.AlunoResponse;
import com.spartans.dev.gym_hub.interfaces.IAlunoMapper;
import com.spartans.dev.gym_hub.model.Aluno;
import org.springframework.stereotype.Component;

@Component
public class AlunoMapper implements IAlunoMapper {

    @Override
    public Aluno paraEntidade (AlunoRequest alunoRequest){

        return new Aluno(alunoRequest.nome(),
                alunoRequest.altura(),
                alunoRequest.massaCorporal(),
                alunoRequest.nascimento(),
                alunoRequest.user(),
                alunoRequest.senha(),
                java.time.LocalDateTime.now(),
                alunoRequest.cpf());
    }

    @Override
    public AlunoResponse paraDTO (Aluno aluno){

        return new AlunoResponse(
                aluno.getId(),
                aluno.getNome(),
                aluno.getAltura(),
                aluno.getMassaCorporal(),
                aluno.getNascimento(),
                aluno.getUser(),
                aluno.getSenha(),
                aluno.getDataCadastro(),
                aluno.getImc(),
                aluno.getCpf()
        );
    }
}
