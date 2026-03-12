package com.spartans.dev.gym_hub.mapper;

import com.spartans.dev.gym_hub.dto.AlunoRequest;
import com.spartans.dev.gym_hub.dto.AlunoResponse;
import com.spartans.dev.gym_hub.model.Aluno;
import org.springframework.stereotype.Component;

@Component
public class AlunoMapper {

    public Aluno paraEntidade (AlunoRequest alunoRequest){

        return new Aluno(alunoRequest.nome(),
                alunoRequest.altura(),
                alunoRequest.massaCorporal(),
                alunoRequest.dataNascimento(),
                alunoRequest.user(),
                alunoRequest.senha(),
                alunoRequest.dataCadastro(),
                alunoRequest.imc(),
                alunoRequest.cpf());
    }

    public AlunoResponse paraDTO (Aluno aluno){

        return new AlunoResponse(
                aluno.getId(),
                aluno.getNome(),
                aluno.getAltura(),
                aluno.getMassaCorporal(),
                aluno.getDataNascimento(),
                aluno.getUser(),
                aluno.getSenha(),
                aluno.getDataCadastro(),
                aluno.getImc(),
                aluno.getCpf()
        );
    }
}
