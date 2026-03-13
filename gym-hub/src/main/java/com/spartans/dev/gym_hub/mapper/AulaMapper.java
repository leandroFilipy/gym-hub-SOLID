package com.spartans.dev.gym_hub.mapper;

import com.spartans.dev.gym_hub.dto.aula.AulaRequisicaoDTO;
import com.spartans.dev.gym_hub.dto.aula.AulaRespostaDTO;
import com.spartans.dev.gym_hub.model.Aula;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public class AulaMapper {

    public Aula paraEntidade(AulaRequisicaoDTO aulaRequisicaoDTO){
        return new Aula(aulaRequisicaoDTO.nome(), aulaRequisicaoDTO.descricao(), aulaRequisicaoDTO.duracao());
    }

    public AulaRespostaDTO paraRespostaDTO(Aula aula){
        return new AulaRespostaDTO(aula.getId(), aula.getNome(), aula.getDescricao(), aula.getDuracao());
    }

}
