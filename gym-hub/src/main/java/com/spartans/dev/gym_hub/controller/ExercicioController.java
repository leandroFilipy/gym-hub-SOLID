package com.spartans.dev.gym_hub.controller;

import com.spartans.dev.gym_hub.dto.exercicio.ExercicioRequest;
import com.spartans.dev.gym_hub.dto.exercicio.ExercicioResponse;
import com.spartans.dev.gym_hub.interfaces.IExercicioService;
import com.spartans.dev.gym_hub.service.ExercicioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/exercicios")
public class ExercicioController {


    private final IExercicioService iExercicioService;

    @PostMapping("/cadastrar")
    public ExercicioResponse create(@RequestBody ExercicioRequest exercicioRequisicaoDTO) {
        return iExercicioService.create(exercicioRequisicaoDTO);

    }

    @GetMapping("/listarTodos")
    public List<ExercicioResponse> listAll() {
        return iExercicioService.listAll();

    }


    @GetMapping("/listarId/{id}")
    public ExercicioResponse listById(@PathVariable("id") Long id) {
        return iExercicioService.findById(id);

    }

    @PutMapping("/atualizar/{id}")
    public ExercicioResponse update(@PathVariable("id")long id, @RequestBody ExercicioRequest exercicioRequisicaoDTO) {
        return iExercicioService.update(id,exercicioRequisicaoDTO);

    }


    @DeleteMapping("/deletar/{id}")
    public void delete(@PathVariable("id")long id){
        iExercicioService.delete(id);
    }
}
