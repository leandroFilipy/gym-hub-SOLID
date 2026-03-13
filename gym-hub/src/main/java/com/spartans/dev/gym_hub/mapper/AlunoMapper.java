package com.spartans.dev.gym_hub.mapper;

import com.spartans.dev.gym_hub.dto.aluno.AlunoRequisicaoDTO;
import com.spartans.dev.gym_hub.dto.aluno.AlunoRespostaDTO;
import com.spartans.dev.gym_hub.model.Aluno;
import org.springframework.stereotype.Component;

@Component
public class AlunoMapper {

    public Aluno paraEntidade(AlunoRequisicaoDTO alunoRequisicaoDTO) {

        return new Aluno(alunoRequisicaoDTO.nome(),
                alunoRequisicaoDTO.altura(),
                alunoRequisicaoDTO.massaCorporal(),
                alunoRequisicaoDTO.nascimento(),
                alunoRequisicaoDTO.user(),
                alunoRequisicaoDTO.senha(),
                alunoRequisicaoDTO.dataCadastro(),
                alunoRequisicaoDTO.imc(),
                alunoRequisicaoDTO.cpf());
    }

    public AlunoRespostaDTO paraRespostaDTO(Aluno aluno) {

        return new AlunoRespostaDTO(
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



