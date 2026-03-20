package com.spartans.dev.gym_hub.service;

import com.spartans.dev.gym_hub.dto.aluno.AlunoRequest;
import com.spartans.dev.gym_hub.dto.aluno.AlunoResponse;
import com.spartans.dev.gym_hub.exceptions.AlunoNotFoundException;
import com.spartans.dev.gym_hub.mapper.aluno.AlunoMapper;
import com.spartans.dev.gym_hub.model.Aluno;
import com.spartans.dev.gym_hub.repository.AlunoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AlunoService implements IAlunoService{


    private final AlunoRepository alunoRepository;
    private final AlunoMapper alunoMapper;

    public AlunoResponse create(AlunoRequest alunoRequest){

        Aluno aluno = alunoMapper.paraEntidade(alunoRequest);
        aluno.setImc(calcularIMC(aluno.getMassaCorporal(), aluno.getAltura()));
        aluno.setDataCadastro(LocalDateTime.now());
            Aluno aluno2 = alunoRepository.save(aluno);
            AlunoResponse alunoResponse = alunoMapper.paraDTO(aluno2);
            return alunoResponse;
    }

    public List<AlunoResponse> listAll (){

            List<Aluno> alunos = alunoRepository.findAll();
            List<AlunoResponse> dtos = new ArrayList<>();

            for (Aluno aluno : alunos) {
                dtos.add(alunoMapper.paraDTO(aluno));
            }
            return dtos;
    }

    public AlunoResponse findById(long id){

        Aluno aluno = alunoRepository.findById(id).orElseThrow(() -> new AlunoNotFoundException("Não existe um aluno com este id"));
        AlunoResponse alunoResponse = alunoMapper.paraDTO(aluno);

        return alunoResponse;
    }

    public AlunoResponse update(long id, AlunoRequest alunoRequest){

        Aluno aluno = alunoRepository.findById(id).orElseThrow(() -> new AlunoNotFoundException("Não existe um aluno com este id"));
        aluno.setNome(alunoRequest.nome());
        aluno.setAltura(alunoRequest.altura());
        aluno.setMassaCorporal(alunoRequest.massaCorporal());
        aluno.setNascimento(alunoRequest.nascimento());
        aluno.setUser(alunoRequest.user());
        aluno.setSenha(alunoRequest.senha());
        aluno.setCpf(alunoRequest.cpf());
        aluno.setImc(calcularIMC(aluno.getMassaCorporal(), aluno.getAltura()));
        Aluno aluno1 = alunoRepository.save(aluno);
        AlunoResponse alunoResponse = alunoMapper.paraDTO(aluno1);
        return alunoResponse;
    }

    public void delete(long id){
        if(alunoRepository.existsById(id)){
            alunoRepository.deleteById(id);

        }else {
           new AlunoNotFoundException("Este aluno não existe");
        }
    }

    public double calcularIMC(double peso, double altura){

        double imc = peso / (altura * altura);
        return Math.round(imc * 100.0) / 100.0;
    }

}
