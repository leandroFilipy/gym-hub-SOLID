package com.spartans.dev.gym_hub.controller;

import com.spartans.dev.gym_hub.dto.exercicio.ExercicioRequest;
import com.spartans.dev.gym_hub.dto.exercicio.ExercicioResponse;
import com.spartans.dev.gym_hub.service.ExercicioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/exercicios")
public class ExercicioController {


    private final ExercicioService exercicioService;

    @PostMapping("/cadastrar")
    public ExercicioRespostaDTO create(@RequestBody ExercicioRequisicaoDTO exercicioRequisicaoDTO) {
        return exercicioService.create(exercicioRequisicaoDTO);

    }

    @GetMapping("/listarTodos")
    public List<ExercicioRespostaDTO> listAll() {
        return exercicioService.listAll();

    }


    @GetMapping("/listarId/{id}")
    public ExercicioResponse listId(@PathVariable("id") Long id) {
        return exercicioService.findById(id);

    }

    @PutMapping("/atualizar/{id}")
    public ExercicioResponse update(@PathVariable("id")long id, @RequestBody ExercicioRequest exercicioRequisicaoDTO) {
        return exercicioService.update(id,exercicioRequisicaoDTO);

    }


    @DeleteMapping("/deletar/{id}")
    public void delete(@PathVariable("id")Long id){
        exercicioService.delete(id);
    }
}
