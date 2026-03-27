package com.spartans.dev.gym_hub.mapper;

import com.spartans.dev.gym_hub.dto.exercicio.ExercicioRequest;
import com.spartans.dev.gym_hub.dto.exercicio.ExercicioResponse;
import com.spartans.dev.gym_hub.mapper.exercicio.IExercicioMapper;
import com.spartans.dev.gym_hub.model.Exercicio;
import org.springframework.stereotype.Component;

@Component
public class ExercicioMapper implements IExercicioMapper {


    @Override
    public Exercicio paraEntidade(ExercicioRequest exercicioRequisicaoDTO) {
        return new Exercicio(exercicioRequisicaoDTO.nome(), exercicioRequisicaoDTO.descricao(), exercicioRequisicaoDTO.videoURL(), exercicioRequisicaoDTO.series(), exercicioRequisicaoDTO.repeticoes(), exercicioRequisicaoDTO.musculoAlvo());
    }

    @Override
    public ExercicioResponse paraDTO(Exercicio exercicio) {
        return new ExercicioResponse(exercicio.getId(), exercicio.getNome(), exercicio.getDescricao(), exercicio.getVideoURL(), exercicio.getSeries(), exercicio.getRepeticoes(), exercicio.getMusculoAlvo());

    }


}