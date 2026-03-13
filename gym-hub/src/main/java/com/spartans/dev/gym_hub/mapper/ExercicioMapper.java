package com.spartans.dev.gym_hub.mapper;

import com.spartans.dev.gym_hub.dto.exercicio.ExercicioRequisicaoDTO;
import com.spartans.dev.gym_hub.dto.exercicio.ExercicioRespostaDTO;
import com.spartans.dev.gym_hub.model.Exercicio;
import org.springframework.stereotype.Component;

@Component
public class ExercicioMapper {


    public Exercicio paraEntidade(ExercicioRequisicaoDTO exercicioRequisicaoDTO) {
        return new Exercicio(exercicioRequisicaoDTO.nome(), exercicioRequisicaoDTO.descricao(), exercicioRequisicaoDTO.videoURL(), exercicioRequisicaoDTO.series(), exercicioRequisicaoDTO.repeticoes(), exercicioRequisicaoDTO.musculoAlvo());
    }


    public ExercicioRespostaDTO paraRespostaDTO(Exercicio exercicio) {
        return new ExercicioRespostaDTO(exercicio.getId(), exercicio.getNome(), exercicio.getDescricao(), exercicio.getVideoURL(), exercicio.getSeries(), exercicio.getRepeticoes(), exercicio.getMusculoAlvo());

    }


}
