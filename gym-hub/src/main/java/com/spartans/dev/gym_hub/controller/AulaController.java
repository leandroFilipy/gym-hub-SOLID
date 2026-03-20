package com.spartans.dev.gym_hub.controller;

import com.spartans.dev.gym_hub.dto.aula.AulaRequest;
import com.spartans.dev.gym_hub.dto.aula.AulaResponse;
import com.spartans.dev.gym_hub.interfaces.IAulaService;
import com.spartans.dev.gym_hub.service.AulaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/aulas")
public class AulaController {

    private final IAulaService iAulaService;

    @PostMapping("/cadastrar")
    public AulaResponse create(@RequestBody AulaRequest aula) {
        return iAulaService.create(aula);

    }

    @GetMapping("/listarTodos")
    public List<AulaResponse> listAll() {
        return iAulaService.listAll();

    }


    @GetMapping("/listarId/{id}")
    public AulaResponse listId(@PathVariable("id") Long id) {
        return iAulaService.findById(id);

    }

    @PutMapping("/atualizar/{id}")
    public AulaResponse update(@PathVariable("id")long id, @RequestBody AulaRequest aulaRequisicaoDTO) {
        return iAulaService.update(id,aulaRequisicaoDTO);

    }


    @DeleteMapping("/deletar/{id}")
    public void delete(@PathVariable("id")Long id){
        iAulaService.delete(id);
    }


}
