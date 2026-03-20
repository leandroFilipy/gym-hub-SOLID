package com.spartans.dev.gym_hub.mapper.aula;

import com.spartans.dev.gym_hub.dto.aula.AulaRequest;
import com.spartans.dev.gym_hub.dto.aula.AulaResponse;
import com.spartans.dev.gym_hub.interfaces.IAulaMapper;
import com.spartans.dev.gym_hub.model.Aula;
import jakarta.persistence.Column;
import org.springframework.stereotype.Component;

@Component
public class AulaMapper implements IAulaMapper {

    @Override
    public Aula paraEntidade(AulaRequest aulaRequest){

        return new Aula(aulaRequest.nome(),
                aulaRequest.descricao(),
                aulaRequest.duracao());
    }

    @Override
    public AulaResponse paraDTO(Aula aula){

        return new AulaResponse(
                aula.getId(),
                aula.getNome(),
                aula.getDescricao(),
                aula.getDuracao()
        );
    }


}
