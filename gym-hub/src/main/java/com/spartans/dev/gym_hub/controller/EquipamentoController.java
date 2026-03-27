package com.spartans.dev.gym_hub.controller;

import com.spartans.dev.gym_hub.dto.equipamento.EquipamentoRequest;
import com.spartans.dev.gym_hub.dto.equipamento.EquipamentoResponse;
import com.spartans.dev.gym_hub.service.equipamentoService.IEquipamentoService;
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
            return iEquipamentoService.create(equipamentoRequest);
    }

    @GetMapping("/list")
    public List<EquipamentoResponse> list (){
            return iEquipamentoService.listAll();
    }

    @GetMapping("/list/{id}")
    public EquipamentoResponse listById(@PathVariable long id){
            return iEquipamentoService.findById(id);
    }

    @PutMapping("update/{id}")
    public EquipamentoResponse update(@PathVariable long id, @RequestBody EquipamentoRequest equipamentoRequest){
            return iEquipamentoService.update(id, equipamentoRequest);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete (@PathVariable long id){
            iEquipamentoService.delete(id);
            return ResponseEntity.status(201).body("O equipamento foi deletado");
    }

}
