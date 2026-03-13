package com.spartans.dev.gym_hub.controller;

import com.spartans.dev.gym_hub.dto.aula.AulaRequisicaoDTO;
import com.spartans.dev.gym_hub.dto.aula.AulaRespostaDTO;
import com.spartans.dev.gym_hub.model.Aula;
import com.spartans.dev.gym_hub.service.AulaService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/aulas")
public class AulaController {

    private final AulaService aulaService;

    @PostMapping("/cadastrar")
    public AulaRespostaDTO create(@RequestBody AulaRequisicaoDTO aula) {
        return aulaService.create(aula);

    }

    @GetMapping("/listarTodos")
    public List<AulaRespostaDTO> listAll() {
        return aulaService.listAll();

    }


    @GetMapping("/listarId/{id}")
    public AulaRespostaDTO listId(@PathVariable("id") Long id) {
        return aulaService.findById(id);

    }

    @PutMapping("/atualizar/{id}")
    public AulaRespostaDTO update(@PathVariable("id")long id, @RequestBody AulaRequisicaoDTO aulaRequisicaoDTO) {
        return aulaService.update(id,aulaRequisicaoDTO);

    }


    @DeleteMapping("/deletar/{id}")
    public void delete(@PathVariable("id")Long id){
        aulaService.delete(id);
    }


}
