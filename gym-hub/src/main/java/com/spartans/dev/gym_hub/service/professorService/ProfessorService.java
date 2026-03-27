package com.spartans.dev.gym_hub.service.professorService;

import com.spartans.dev.gym_hub.dto.professor.ProfessorRequest;
import com.spartans.dev.gym_hub.dto.professor.ProfessorResponse;
import com.spartans.dev.gym_hub.exceptions.ProfessorNotFoundException;
import com.spartans.dev.gym_hub.mapper.professor.IProfessorMapper;
import com.spartans.dev.gym_hub.model.Professor;
import com.spartans.dev.gym_hub.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfessorService implements IProfessorService{

    private final IProfessorMapper iProfessorMapper;
    private final ProfessorRepository professorRepository;
    public ProfessorResponse create(ProfessorRequest professorRequest){

        Professor professor = iProfessorMapper.paraEntidade(professorRequest);
        Professor professorSalvo = professorRepository.save(professor);
        ProfessorResponse professorResponse = iProfessorMapper.paraDTO(professorSalvo);

        return professorResponse;
    }

    public List<ProfessorResponse> listAll (){
        List<Professor> professores = professorRepository.findAll();
        List<ProfessorResponse> dto = new ArrayList<>();

        for(Professor professor : professores){
            dto.add(iProfessorMapper.paraDTO(professor));
        }

        return dto;
    }

    public ProfessorResponse findById(long id){

        Professor professor = professorRepository.findById(id).orElseThrow(() -> new ProfessorNotFoundException("Este professor não existe"));
        ProfessorResponse professorResponse = iProfessorMapper.paraDTO(professor);

        return professorResponse;
    }

    public ProfessorResponse update(long id, ProfessorRequest professorRequest){

        Professor professor = professorRepository.findById(id).orElseThrow(() -> new ProfessorNotFoundException("Não existe professor com este id"));
        professor.setNome(professorRequest.nome());
        professor.setCref(professorRequest.cref());
        professor.setEspecialidade(professorRequest.especialidade());
        professor.setSobre(professorRequest.sobre());
        professor.setCpf(professorRequest.cpf());

        Professor professorSalvo = professorRepository.save(professor);
        ProfessorResponse professorResponse = iProfessorMapper.paraDTO(professorSalvo);
        return professorResponse;
    }

    public void delete(long id){
        if(professorRepository.existsById(id)){
            professorRepository.deleteById(id);
        }else {
            throw new ProfessorNotFoundException("Erro ao deletar, não existe professor com este ID");
        }
        }

}
