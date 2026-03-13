package com.spartans.dev.gym_hub.service;

import com.spartans.dev.gym_hub.dto.aula.AulaRequest;
import com.spartans.dev.gym_hub.dto.aula.AulaResponse;
import com.spartans.dev.gym_hub.mapper.aula.AulaMapper;
import com.spartans.dev.gym_hub.model.Aula;
import com.spartans.dev.gym_hub.repository.AulaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AulaService {

    private final AulaRepository aulaRepository;
    private final AulaMapper aulaMapper;


    public AulaResponse criarAula(AulaRequest aulaRequest){
        Aula aula = aulaMapper.paraEntidade(aulaRequest);
        if(aulaRepository.existsById(aula.getId())){

            throw new RuntimeException("Já existe uma aula com este id");
        }else {

        Aula aulaSalva = aulaRepository.save(aula);
        AulaResponse aulaResponse = aulaMapper.paraDTO(aulaSalva);

        return aulaResponse;
        }
    }

    public List<AulaResponse> listarAulas (){
        if(aulaRepository.findAll().isEmpty()){
            throw new RuntimeException("Não existe nenhum aluno cadastrado");

        }else {
            List<Aula> aulas = aulaRepository.findAll();
            List<AulaResponse> dto = new ArrayList<>();

            for (Aula aula : aulas) {
                dto.add(aulaMapper.paraDTO(aula));
            }
            return dto;
        }
    }

    public AulaResponse listarAulaPorId(long id){
        Aula aula = aulaRepository.findById(id).orElseThrow(() -> new RuntimeException("Não existe aluno com este ID"));
        AulaResponse aulaResponse = aulaMapper.paraDTO(aula);

        return aulaResponse;
    }

    public AulaResponse atualizarAula(long id, AulaRequest aulaRequest){
        Aula aula = aulaRepository.findById(id).orElseThrow(() -> new RuntimeException("Não existe aula com este id"));
        aula.setNome(aulaRequest.nome());
        aula.setDescricao(aulaRequest.descricao());
        aula.setDuracao(aulaRequest.duracao());
        Aula aulaSalva = aulaRepository.save(aula);
        AulaResponse aulaResponse = aulaMapper.paraDTO(aulaSalva);

        return aulaResponse;
    }
    public void deletarAula(long id){

        if(aulaRepository.existsById(id)){
            aulaRepository.deleteById(id);

        }else {
            throw new RuntimeException("Não existe uma aula com este ID");
        }

    }
}
