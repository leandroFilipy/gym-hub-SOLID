package com.spartans.dev.gym_hub.service;

import com.spartans.dev.gym_hub.dto.AlunoRequest;
import com.spartans.dev.gym_hub.dto.AlunoResponse;
import com.spartans.dev.gym_hub.mapper.AlunoMapper;
import com.spartans.dev.gym_hub.model.Aluno;
import com.spartans.dev.gym_hub.repository.AlunoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AlunoService {


    private final AlunoRepository alunoRepository;
    private final AlunoMapper alunoMapper;

    public AlunoResponse criarAluno(AlunoRequest alunoRequest){

        Aluno aluno = alunoMapper.paraEntidade(alunoRequest);

        if(alunoRepository.existsById(aluno.getId())){
            throw new RuntimeException("O aluno já existe");
        }

        Aluno aluno2 = alunoRepository.save(aluno);
        AlunoResponse alunoResponse = alunoMapper.paraDTO(aluno2);

        return alunoResponse;
    }

    public List<AlunoResponse> listarAlunos (){

        List<Aluno> alunos = alunoRepository.findAll();
        List<AlunoResponse> dtos = new ArrayList<>();

        for(Aluno aluno: alunos){
            dtos.add(alunoMapper.paraDTO(aluno));
        }

        return dtos;
    }

    public AlunoResponse listarPorId(long id){

        Aluno aluno = alunoRepository.findById(id).orElseThrow(() -> new RuntimeException("Não existe um aluno com este id"));
        AlunoResponse alunoResponse = alunoMapper.paraDTO(aluno);

        return alunoResponse;
    }

    public AlunoResponse atualizarAluno(long id, AlunoRequest alunoRequest){

        Aluno aluno = alunoRepository.findById(id).orElseThrow(() -> new RuntimeException("Não existe um aluno com este id"));
        aluno.setNome(alunoRequest.nome());
        aluno.setAltura(alunoRequest.altura());
        aluno.setMassaCorporal(alunoRequest.massaCorporal());
        aluno.setDataNascimento(alunoRequest.dataNascimento());
        aluno.setUser(alunoRequest.user());
        aluno.setSenha(alunoRequest.senha());
        aluno.setImc(alunoRequest.imc());
        aluno.setCpf(alunoRequest.cpf());

        Aluno aluno1 = alunoRepository.save(aluno);
        AlunoResponse alunoResponse = alunoMapper.paraDTO(aluno1);
        return alunoResponse;
    }

    public void deletarAluno(long id){
        if(alunoRepository.existsById(id)){
            alunoRepository.deleteById(id);

        }else {
            throw new RuntimeException("Este aluno não existe");
        }
    }


}
