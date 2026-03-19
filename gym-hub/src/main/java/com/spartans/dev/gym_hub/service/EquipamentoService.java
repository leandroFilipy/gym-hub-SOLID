package com.spartans.dev.gym_hub.service;

import com.spartans.dev.gym_hub.dto.equipamento.EquipamentoRequest;
import com.spartans.dev.gym_hub.dto.equipamento.EquipamentoResponse;
import com.spartans.dev.gym_hub.mapper.equipamento.EquipamentoMapper;
import com.spartans.dev.gym_hub.model.Equipamento;
import com.spartans.dev.gym_hub.repository.EquipamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipamentoService {

    private final EquipamentoRepository equipamentoRepository;
    private final EquipamentoMapper equipamentoMapper;

    public EquipamentoResponse create(EquipamentoRequest equipamentoRequest){
        Equipamento equipamento = equipamentoMapper.paraEntidade(equipamentoRequest);

        EquipamentoResponse equipamentoResponse = equipamentoMapper.paraDTO(equipamento);

        return equipamentoResponse;
    }

    public List<EquipamentoResponse> listAll(){

            List<Equipamento> equipamentos = equipamentoRepository.findAll();
            List<EquipamentoResponse> dto = new ArrayList<>();

            for (Equipamento equipamento : equipamentos) {
                dto.add(equipamentoMapper.paraDTO(equipamento));
            }
            return dto;
    }

    public EquipamentoResponse findById(long id){

        Equipamento equipamento = equipamentoRepository.findById(id).orElseThrow(() -> new RuntimeException("Não existe um equipamento com este id"));
        EquipamentoResponse equipamentoResponse = equipamentoMapper.paraDTO(equipamento);

        return equipamentoResponse;
    }

    public EquipamentoResponse update(long id, EquipamentoRequest equipamentoRequest) {

        Equipamento equipamento = equipamentoRepository.findById(id).orElseThrow(() -> new RuntimeException("Não existe usuário com este id"));
        equipamento.setNome(equipamentoRequest.nome());
        equipamento.setDescricao(equipamentoRequest.descricao());
        equipamento.setAnatomia(equipamentoRequest.anatomia());

        Equipamento equipamentoSalvo = equipamentoRepository.save(equipamento);
        EquipamentoResponse equipamentoResponse = equipamentoMapper.paraDTO(equipamentoSalvo);
        return equipamentoResponse;
    }

    public void delete(long id) {
        if (equipamentoRepository.existsById(id)) {
            equipamentoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Não existe uma aula com este ID");
        }
    }

}
