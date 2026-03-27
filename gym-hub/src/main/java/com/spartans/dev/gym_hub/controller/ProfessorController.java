package com.spartans.dev.gym_hub.controller;

import com.spartans.dev.gym_hub.dto.professor.ProfessorRequest;
import com.spartans.dev.gym_hub.dto.professor.ProfessorResponse;
import com.spartans.dev.gym_hub.service.professorService.IProfessorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/professor")
public class ProfessorController {

    private final IProfessorService iProfessorService;

    @PostMapping("/register")
    public ProfessorResponse create(@RequestBody ProfessorRequest professorRequest){
            return iProfessorService.create(professorRequest);
    }

    @GetMapping("/list")
    public List<ProfessorResponse> listAll(){
            return iProfessorService.listAll();
    }

    @GetMapping("/list/{id}")
    public ProfessorResponse findById(@PathVariable long id){
            return iProfessorService.findById(id);
    }

    @PutMapping("/update/{id}")
    public ProfessorResponse update(@PathVariable long id, @RequestBody ProfessorRequest professorRequest){
            return iProfessorService.update(id, professorRequest);
    }

    @DeleteMapping("/delete/{id}")
    public void deletarProfessor(@PathVariable long id){
            iProfessorService.delete(id);
    }
}
