package com.spartans.dev.gym_hub.service;

import com.spartans.dev.gym_hub.dto.exercicio.ExercicioRequisicaoDTO;
import com.spartans.dev.gym_hub.dto.exercicio.ExercicioRespostaDTO;
import com.spartans.dev.gym_hub.mapper.ExercicioMapper;
import com.spartans.dev.gym_hub.model.Exercicio;
import com.spartans.dev.gym_hub.repository.ExercicioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExercicioService {

    private final ExercicioRepository exercicioRepository;
    private final ExercicioMapper exercicioMapper;


    public ExercicioRespostaDTO create(ExercicioRequisicaoDTO exercicioRequisicaoDTO) {

        Exercicio exercicio = exercicioMapper.paraEntidade(exercicioRequisicaoDTO);

        if (exercicio.getId() != null && exercicioRepository.existsById(exercicio.getId())) {
            throw new RuntimeException("Exercicio ja existente");
        }

        Exercicio exercicioSalvo = exercicioRepository.save(exercicio);

        return exercicioMapper.paraRespostaDTO(exercicioSalvo);
    }


    public List<ExercicioRespostaDTO> listAll() {

        List<Exercicio> exercicios = exercicioRepository.findAll();

        return exercicios.stream()
                .map(exercicio -> {
                    return exercicioMapper.paraRespostaDTO(exercicio);

                })

                .toList();

    }

    public ExercicioRespostaDTO findById(long id) {
        Exercicio exercicio = exercicioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exercicio não existe!"));

        return exercicioMapper.paraRespostaDTO(exercicio);
    }


    public ExercicioRespostaDTO update(Long id, ExercicioRequisicaoDTO exercicioRequisicaoDTO) {
        Exercicio exercicioExistente = exercicioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exercicio nao existe"));

        exercicioExistente.setNome(exercicioRequisicaoDTO.nome());
        exercicioExistente.setDescricao(exercicioRequisicaoDTO.descricao());
        exercicioExistente.setVideoURL(exercicioRequisicaoDTO.videoURL());
        exercicioExistente.setSeries(exercicioRequisicaoDTO.series());
        exercicioExistente.setRepeticoes(exercicioRequisicaoDTO.repeticoes());
        exercicioExistente.setMusculoAlvo(exercicioRequisicaoDTO.musculoAlvo());


        Exercicio exercicioAtualizado = exercicioRepository.save(exercicioExistente);
        return exercicioMapper.paraRespostaDTO(exercicioAtualizado);

    }


    public void delete(Long id) {
        if (!exercicioRepository.existsById(id)) {
            throw new RuntimeException("Exercicio nao existe");
        }

        exercicioRepository.deleteById(id);
    }

}
