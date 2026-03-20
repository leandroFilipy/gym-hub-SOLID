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
@RequestMapping("/equipment")
@RequiredArgsConstructor
public class EquipamentoController {

    private final IEquipamentoService iEquipamentoService;

    @PostMapping("/register")
    public EquipamentoResponse create(@RequestBody EquipamentoRequest equipamentoRequest){
        try{
            return iEquipamentoService.create(equipamentoRequest);
        }catch (RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/list")
    public List<EquipamentoResponse> list (){
        try{
            return iEquipamentoService.listAll();
        }catch (RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/list/{id}")
    public EquipamentoResponse listById(@PathVariable long id){
        try{
            return iEquipamentoService.findById(id);
        }catch (RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("update/{id}")
    public EquipamentoResponse update(@PathVariable long id, @RequestBody EquipamentoRequest equipamentoRequest){
        try{
            return iEquipamentoService.update(id, equipamentoRequest);
        }catch (RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete (@PathVariable long id){
        try{
            iEquipamentoService.delete(id);
            return ResponseEntity.status(201).body("O equipamento foi deletado");
        }catch (RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

}
