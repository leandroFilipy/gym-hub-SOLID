package com.spartans.dev.gym_hub.service;

import com.spartans.dev.gym_hub.dto.aula.AulaRequisicaoDTO;
import com.spartans.dev.gym_hub.dto.aula.AulaRespostaDTO;
import com.spartans.dev.gym_hub.dto.professor.ProfessorRequisicaoDTO;
import com.spartans.dev.gym_hub.dto.professor.ProfessorRespostaDTO;
import com.spartans.dev.gym_hub.mapper.AulaMapper;
import com.spartans.dev.gym_hub.mapper.ProfessorMapper;
import com.spartans.dev.gym_hub.model.Aula;
import com.spartans.dev.gym_hub.model.Professor;
import com.spartans.dev.gym_hub.repository.AulaRepository;
import com.spartans.dev.gym_hub.repository.ProfessorRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfessorService {

    private final ProfessorRepository professorRepository;
    private final ProfessorMapper professorMapper;

    public ProfessorResponse criarProfessor(ProfessorRequest professorRequest){

        Professor professor = professorMapper.paraEntidade(professorRequest);

        if(professorRepository.existsById(professor.getId())){
            throw new RuntimeException("Professor já existe");
        }else {
            Professor professorSalvo = professorRepository.save(professor);

            ProfessorResponse professorResponse = professorMapper.paraDTO(professorSalvo);

            return professorResponse;
        }
    }

    public List<ProfessorResponse> listarProfessores (){
        if(professorRepository.findAll().isEmpty()){
            throw new RuntimeException("Não existe nenhum professorgi cadastrado");
        }
        List<Professor> professores = professorRepository.findAll();
        List<ProfessorResponse> dto = new ArrayList<>();

        for(Professor professor : professores){
            dto.add(professorMapper.paraDTO(professor));
        }

        return dto;
    }

    public ProfessorResponse listarProfessorPorId(long id){

        Professor professor = professorRepository.findById(id).orElseThrow(() -> new RuntimeException("Este professor não existe"));
        ProfessorResponse professorResponse = professorMapper.paraDTO(professor);

        return professorResponse;
    }

    public ProfessorResponse atualizarProfessor(long id, ProfessorRequest professorRequest){

        Professor professor = professorRepository.findById(id).orElseThrow(() -> new RuntimeException("Não existe professor com este id"));
        professor.setNome(professorRequest.nome());
        professor.setCref(professorRequest.cref());
        professor.setEspecialidade(professorRequest.especialidade());
        professor.setSobre(professorRequest.sobre());
        professor.setCpf(professorRequest.cpf());

        Professor professorSalvo = professorRepository.save(professor);
        ProfessorResponse professorResponse = professorMapper.paraDTO(professorSalvo);
        return professorResponse;
    }

    public void deletarProfessor(long id){
        if(professorRepository.existsById(id)){
            professorRepository.deleteById(id);
        }else {
            throw new RuntimeException("Erro ao deletar, não existe professor com este ID");
        }
        }

}
