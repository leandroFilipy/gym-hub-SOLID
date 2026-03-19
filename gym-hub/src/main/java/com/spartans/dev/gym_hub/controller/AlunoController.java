package com.spartans.dev.gym_hub.controller;

import com.spartans.dev.gym_hub.dto.aluno.AlunoRequest;
import com.spartans.dev.gym_hub.dto.aluno.AlunoResponse;
import com.spartans.dev.gym_hub.service.AlunoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/alunos")
public class AlunoController {
    private final AlunoService alunoService;

    @PostMapping("/register")
    public AlunoResponse create(@RequestBody AlunoRequest aluno) {
        return alunoService.create(aluno);

    }

    @GetMapping("/list")
    public List<AlunoResponse> listAll() {
        return alunoService.listAll();

    }


    @GetMapping("/list/{id}")
    public AlunoResponse listId(@PathVariable("id") Long id) {
        return alunoService.findById(id);

    }

    @PutMapping("/update/{id}")
    public AlunoResponse update(@PathVariable("id")long id, @RequestBody AlunoRequest alunoRequisicaoDTO) {
        return alunoService.update(id,alunoRequisicaoDTO);

    }


    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id")Long id){
        alunoService.delete(id);
    }

}
