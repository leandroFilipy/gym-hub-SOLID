package com.spartans.dev.gym_hub.mapper.equipamento;

import com.spartans.dev.gym_hub.dto.equipamento.EquipamentoRequest;
import com.spartans.dev.gym_hub.dto.equipamento.EquipamentoResponse;
import com.spartans.dev.gym_hub.model.Equipamento;
import org.springframework.stereotype.Component;

@Component
public class EquipamentoMapper implements IEquipamentoMapper {


    @Override
    public Equipamento paraEntidade(EquipamentoRequest equipamentoRequest){

        return new Equipamento(equipamentoRequest.nome(),
                equipamentoRequest.descricao(),
                equipamentoRequest.anatomia()
        );
    }

    @Override
    public EquipamentoResponse paraDTO (Equipamento equipamento){

        return new EquipamentoResponse(
                equipamento.getId(),
            equipamento.getNome(),
                equipamento.getDescricao(),
                equipamento.getAnatomia()
        );
    }

}
