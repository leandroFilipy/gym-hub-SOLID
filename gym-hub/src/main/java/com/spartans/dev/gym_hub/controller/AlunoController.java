package com.spartans.dev.gym_hub.controller;

import com.spartans.dev.gym_hub.dto.aluno.AlunoRequest;
import com.spartans.dev.gym_hub.dto.aluno.AlunoResponse;
import com.spartans.dev.gym_hub.service.AlunoService;
import com.spartans.dev.gym_hub.service.IAlunoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/alunos")
public class AlunoController {
    private final IAlunoService iAlunoService;

    @PostMapping("/cadastrar")
    public AlunoResponse create(@RequestBody AlunoRequest aluno) {
        return iAlunoService.create(aluno);

    }

    @GetMapping("/listarTodos")
    public List<AlunoResponse> listAll() {
        return iAlunoService.listAll();

    }


    @GetMapping("/listarId/{id}")
    public AlunoResponse listId(@PathVariable("id") Long id) {
        return iAlunoService.findById(id);

    }

    @PutMapping("/atualizar/{id}")
    public AlunoResponse update(@PathVariable("id")long id, @RequestBody AlunoRequest alunoRequisicaoDTO) {
        return iAlunoService.update(id,alunoRequisicaoDTO);

    }


    @DeleteMapping("/deletar/{id}")
    public void delete(@PathVariable("id")Long id){
        iAlunoService.delete(id);
    }

}
