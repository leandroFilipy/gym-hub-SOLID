package com.spartans.dev.gym_hub.controller;

import com.spartans.dev.gym_hub.dto.professor.ProfessorRequest;
import com.spartans.dev.gym_hub.dto.professor.ProfessorResponse;
import com.spartans.dev.gym_hub.interfaces.IProfessorService;
import com.spartans.dev.gym_hub.service.ProfessorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/professor")
public class ProfessorController {

    private final IProfessorService iProfessorService;

    @PostMapping("/register")
    public ProfessorResponse create(@RequestBody ProfessorRequest professorRequest){
        try{
            return iProfessorService.create(professorRequest);
        }catch (RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/list")
    public List<ProfessorResponse> listAll(){
        try{
            return iProfessorService.listAll();
        }catch (RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/list/{id}")
    public ProfessorResponse findById(@PathVariable long id){
        try{
            return iProfessorService.findById(id);
        }catch (RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ProfessorResponse update(@PathVariable long id, @RequestBody ProfessorRequest professorRequest){
        try{
            return iProfessorService.update(id, professorRequest);
        }catch (RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletarProfessor(@PathVariable long id){
        try{
            iProfessorService.delete(id);
            return ResponseEntity.status(201).body("Usuário foi deletado com sucesso");
        }catch (RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
