package com.spartans.dev.gym_hub.mapper.exercicio;

import com.spartans.dev.gym_hub.dto.exercicio.ExercicioRequest;
import com.spartans.dev.gym_hub.dto.exercicio.ExercicioResponse;
import com.spartans.dev.gym_hub.model.Exercicio;

public interface IExercicioMapper {
    Exercicio paraEntidade(ExercicioRequest exercicioRequest);
    ExercicioResponse paraDTO(Exercicio exercicio);

}
