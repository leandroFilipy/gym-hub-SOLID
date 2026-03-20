package com.spartans.dev.gym_hub.interfaces;

import com.spartans.dev.gym_hub.dto.equipamento.EquipamentoRequest;
import com.spartans.dev.gym_hub.dto.equipamento.EquipamentoResponse;

import java.util.List;

public interface IEquipamentoService {
    EquipamentoResponse create(EquipamentoRequest equipamentoRequest);
    List<EquipamentoResponse> listAll();
    EquipamentoResponse findById(long id);
    EquipamentoResponse update(long id, EquipamentoRequest equipamentoRequest);
    void delete(long id);
}
