package com.spartans.dev.gym_hub.mapper.equipamento;

import com.spartans.dev.gym_hub.dto.equipamento.EquipamentoRequest;
import com.spartans.dev.gym_hub.dto.equipamento.EquipamentoResponse;
import com.spartans.dev.gym_hub.model.Equipamento;

public interface IEquipamentoMapper {
    Equipamento paraEntidade(EquipamentoRequest equipamentoRequest);
    EquipamentoResponse paraDTO(Equipamento equipamento);
}
