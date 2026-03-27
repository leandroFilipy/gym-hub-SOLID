package com.spartans.dev.gym_hub.service.exercicioService;

import com.spartans.dev.gym_hub.dto.exercicio.ExercicioRequest;
import com.spartans.dev.gym_hub.dto.exercicio.ExercicioResponse;
import com.spartans.dev.gym_hub.exceptions.ExercicioNotFoundException;
import com.spartans.dev.gym_hub.mapper.exercicio.IExercicioMapper;
import com.spartans.dev.gym_hub.model.Exercicio;
import com.spartans.dev.gym_hub.repository.ExercicioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExercicioService implements IExercicioService{

    private final IExercicioMapper iExercicioMapper;
    private final ExercicioRepository exercicioRepository;


    @Override
    public ExercicioResponse create(ExercicioRequest exercicioRequisicaoDTO) {

        Exercicio exercicio = iExercicioMapper.paraEntidade(exercicioRequisicaoDTO);
        Exercicio exercicioSalvo = exercicioRepository.save(exercicio);

        return iExercicioMapper.paraDTO(exercicioSalvo);
    }


    @Override
    public List<ExercicioResponse> listAll() {

        List<Exercicio> exercicios = exercicioRepository.findAll();

        return exercicios.stream()
                .map(exercicio -> {
                    return iExercicioMapper.paraDTO(exercicio);

                })

                .toList();

    }

    @Override
    public ExercicioResponse findById(long id) {
        Exercicio exercicio = exercicioRepository.findById(id)
                .orElseThrow(() -> new ExercicioNotFoundException("Exercicio não existe!"));

        return iExercicioMapper.paraDTO(exercicio);
    }


    @Override
    public ExercicioResponse update(long id, ExercicioRequest exercicioRequisicaoDTO) {
        Exercicio exercicioExistente = exercicioRepository.findById(id)
                .orElseThrow(() -> new ExercicioNotFoundException("Exercicio nao existe"));

        exercicioExistente.setNome(exercicioRequisicaoDTO.nome());
        exercicioExistente.setDescricao(exercicioRequisicaoDTO.descricao());
        exercicioExistente.setVideoURL(exercicioRequisicaoDTO.videoURL());
        exercicioExistente.setSeries(exercicioRequisicaoDTO.series());
        exercicioExistente.setRepeticoes(exercicioRequisicaoDTO.repeticoes());
        exercicioExistente.setMusculoAlvo(exercicioRequisicaoDTO.musculoAlvo());


        Exercicio exercicioAtualizado = exercicioRepository.save(exercicioExistente);
        return iExercicioMapper.paraDTO(exercicioAtualizado);

    }


    @Override
    public void delete(long id) {
        if (!exercicioRepository.existsById(id)) {
            throw new ExercicioNotFoundException("Exercicio nao existe");
        }

        exercicioRepository.deleteById(id);
    }

}
