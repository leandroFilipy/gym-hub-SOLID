package com.spartans.dev.gym_hub.interfaces;

import com.spartans.dev.gym_hub.dto.exercicio.ExercicioRequest;
import com.spartans.dev.gym_hub.dto.exercicio.ExercicioResponse;
import com.spartans.dev.gym_hub.model.Exercicio;

import java.util.List;

public interface IExercicioService {
    ExercicioResponse create(ExercicioRequest exercicioRequest);
    List<ExercicioResponse> listAll();
    ExercicioResponse findById(long id);
    ExercicioResponse update(long id, ExercicioRequest exercicioRequest);
    void delete(long id);
}
