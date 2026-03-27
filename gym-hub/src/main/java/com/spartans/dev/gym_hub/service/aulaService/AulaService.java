package com.spartans.dev.gym_hub.service.aulaService;

import com.spartans.dev.gym_hub.dto.aula.AulaRequest;
import com.spartans.dev.gym_hub.dto.aula.AulaResponse;
import com.spartans.dev.gym_hub.exceptions.AlunoNotFoundException;
import com.spartans.dev.gym_hub.exceptions.AulaNotFoundException;
import com.spartans.dev.gym_hub.mapper.aula.IAulaMapper;
import com.spartans.dev.gym_hub.model.Aula;
import com.spartans.dev.gym_hub.repository.AulaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AulaService implements IAulaService {

    private final AulaRepository aulaRepository;
    private final IAulaMapper iAulaMapper;


    public AulaResponse create(AulaRequest aulaRequest){
        Aula aula = iAulaMapper.paraEntidade(aulaRequest);

        Aula aulaSalva = aulaRepository.save(aula);
        AulaResponse aulaResponse = iAulaMapper.paraDTO(aulaSalva);

        return aulaResponse;
    }

    public List<AulaResponse> listAll (){

            List<Aula> aulas = aulaRepository.findAll();
            List<AulaResponse> dto = new ArrayList<>();

            for (Aula aula : aulas) {
                dto.add(iAulaMapper.paraDTO(aula));
            }
            return dto;
    }

    public AulaResponse findById(long id){
        Aula aula = aulaRepository.findById(id).orElseThrow(() -> new AlunoNotFoundException("Não existe aluno com este ID"));
        AulaResponse aulaResponse = iAulaMapper.paraDTO(aula);

        return aulaResponse;
    }

    public AulaResponse update(long id, AulaRequest aulaRequest){
        Aula aula = aulaRepository.findById(id).orElseThrow(() -> new AlunoNotFoundException("Não existe aula com este id"));
        aula.setNome(aulaRequest.nome());
        aula.setDescricao(aulaRequest.descricao());
        aula.setDuracao(aulaRequest.duracao());
        Aula aulaSalva = aulaRepository.save(aula);
        AulaResponse aulaResponse = iAulaMapper.paraDTO(aulaSalva);

        return aulaResponse;
    }
    public void delete(long id){

        if(aulaRepository.existsById(id)){
            aulaRepository.deleteById(id);

        }else {
            throw new AulaNotFoundException("Não existe uma aula com este ID");
        }

    }
}
