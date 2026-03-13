package com.spartans.dev.gym_hub.mapper;

import com.spartans.dev.gym_hub.dto.equipamento.EquipamentoRequisicaoDTO;
import com.spartans.dev.gym_hub.dto.equipamento.EquipamentoRespostaDTO;
import com.spartans.dev.gym_hub.model.Equipamento;
import org.springframework.stereotype.Component;

@Component
public class EquipamentoMapper {


    public Equipamento paraEntidade(EquipamentoRequisicaoDTO equipamentoRequest) {

        return new Equipamento(equipamentoRequest.nome(),
                equipamentoRequest.descricao(),
                equipamentoRequest.anatomia()
        );
    }

    public EquipamentoRespostaDTO paraDTO(Equipamento equipamento) {

        return new EquipamentoRespostaDTO(
                equipamento.getId(),
                equipamento.getNome(),
                equipamento.getDescricao(),
                equipamento.getAnatomia()
        );
    }

}