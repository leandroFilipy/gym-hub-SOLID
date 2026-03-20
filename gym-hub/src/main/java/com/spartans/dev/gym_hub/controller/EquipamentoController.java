package com.spartans.dev.gym_hub.controller;

import com.spartans.dev.gym_hub.dto.equipamento.EquipamentoRequest;
import com.spartans.dev.gym_hub.dto.equipamento.EquipamentoResponse;
import com.spartans.dev.gym_hub.interfaces.IEquipamentoService;
import com.spartans.dev.gym_hub.service.EquipamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/equipamentos")
@RequiredArgsConstructor
public class EquipamentoController {

    private final IEquipamentoService iEquipamentoService;

    @PostMapping("/cadastrar")
    public EquipamentoResponse criarEquipamento(EquipamentoRequest equipamentoRequest){
        try{
            return iEquipamentoService.create(equipamentoRequest);
        }catch (RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/listar")
    public List<EquipamentoResponse> listarEquipamentos (){
        try{
            return iEquipamentoService.listAll();
        }catch (RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/listar/{id}")
    public EquipamentoResponse listarEquipamentoPorId(@PathVariable long id){
        try{
            return iEquipamentoService.findById(id);
        }catch (RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("atualizar/{id}")
    public EquipamentoResponse atualizarEquipamento(@PathVariable long id, EquipamentoRequest equipamentoRequest){
        try{
            return iEquipamentoService.update(id, equipamentoRequest);
        }catch (RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity deletarEquipamento (@PathVariable long id){
        try{
            iEquipamentoService.delete(id);
            return ResponseEntity.status(201).body("O equipamento foi deletado");
        }catch (RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

}
