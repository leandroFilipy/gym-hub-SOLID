package com.spartans.dev.gym_hub.controller;

import com.spartans.dev.gym_hub.dto.exercicio.ExercicioRequest;
import com.spartans.dev.gym_hub.dto.exercicio.ExercicioResponse;
import com.spartans.dev.gym_hub.service.exercicioService.IExercicioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/exercise")
public class ExercicioController {


    private final IExercicioService iExercicioService;

    @PostMapping("/register")
    public ExercicioResponse create(@RequestBody ExercicioRequest exercicioRequisicaoDTO) {
        return iExercicioService.create(exercicioRequisicaoDTO);

    }

    @GetMapping("/list")
    public List<ExercicioResponse> listAll() {
        return iExercicioService.listAll();

    }


    @GetMapping("/list/{id}")
    public ExercicioResponse listById(@PathVariable("id") Long id) {
        return iExercicioService.findById(id);

    }

    @PutMapping("/update/{id}")
    public ExercicioResponse update(@PathVariable("id")long id, @RequestBody ExercicioRequest exercicioRequisicaoDTO) {
        return iExercicioService.update(id,exercicioRequisicaoDTO);

    }


    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id")long id){
        iExercicioService.delete(id);
    }
}
