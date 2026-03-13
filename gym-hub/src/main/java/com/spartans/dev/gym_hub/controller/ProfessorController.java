package com.spartans.dev.gym_hub.controller;

import com.spartans.dev.gym_hub.dto.aula.AulaRequisicaoDTO;
import com.spartans.dev.gym_hub.dto.aula.AulaRespostaDTO;
import com.spartans.dev.gym_hub.dto.professor.ProfessorRequisicaoDTO;
import com.spartans.dev.gym_hub.dto.professor.ProfessorRespostaDTO;
import com.spartans.dev.gym_hub.service.AulaService;
import com.spartans.dev.gym_hub.service.ProfessorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/professores")
public class ProfessorController {

    private final ProfessorService professorService;

    @PostMapping("/cadastrar")
    public ProfessorResponse criarProfessor(@RequestBody ProfessorRequest professorRequest){
        try{
            return professorService.criarProfessor(professorRequest);
        }catch (RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/listar")
    public List<ProfessorResponse> listarProfessores(){
        try{
            return professorService.listarProfessores();
        }catch (RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/listar/{id}")
    public ProfessorResponse listarProfessorPorId(@PathVariable long id){
        try{
            return professorService.listarProfessorPorId(id);
        }catch (RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/atualizar/{id}")
    public ProfessorResponse atualizarProfessor(@PathVariable long id, @RequestBody ProfessorRequest professorRequest){
        try{
            return professorService.atualizarProfessor(id, professorRequest);
        }catch (RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarProfessor(@PathVariable long id){
        try{
            professorService.deletarProfessor(id);
            return ResponseEntity.status(201).body("Usuário foi deletado com sucesso");
        }catch (RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
