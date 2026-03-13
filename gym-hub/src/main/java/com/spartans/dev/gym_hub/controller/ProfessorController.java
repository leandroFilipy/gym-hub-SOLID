package com.spartans.dev.gym_hub.controller;

import com.spartans.dev.gym_hub.dto.professor.ProfessorRequest;
import com.spartans.dev.gym_hub.dto.professor.ProfessorResponse;
import com.spartans.dev.gym_hub.service.ProfessorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/professores")
public class ProfessorController {

    private final ProfessorService professorService;

    @PostMapping("/cadastrar")
    public ProfessorResponse create(@RequestBody ProfessorRequest professorRequest){
        try{
            return professorService.create(professorRequest);
        }catch (RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/listar")
    public List<ProfessorResponse> listAll(){
        try{
            return professorService.listAll();
        }catch (RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/listar/{id}")
    public ProfessorResponse findById(@PathVariable long id){
        try{
            return professorService.findById(id);
        }catch (RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/atualizar/{id}")
    public ProfessorResponse update(@PathVariable long id, @RequestBody ProfessorRequest professorRequest){
        try{
            return professorService.update(id, professorRequest);
        }catch (RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarProfessor(@PathVariable long id){
        try{
            professorService.delete(id);
            return ResponseEntity.status(201).body("Usuário foi deletado com sucesso");
        }catch (RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
