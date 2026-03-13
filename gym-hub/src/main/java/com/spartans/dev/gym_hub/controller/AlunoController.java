package com.spartans.dev.gym_hub.controller;

import com.spartans.dev.gym_hub.dto.aluno.AlunoRequisicaoDTO;
import com.spartans.dev.gym_hub.dto.aluno.AlunoRespostaDTO;
import com.spartans.dev.gym_hub.dto.aula.AulaRequisicaoDTO;
import com.spartans.dev.gym_hub.dto.aula.AulaRespostaDTO;
import com.spartans.dev.gym_hub.service.AlunoService;
import com.spartans.dev.gym_hub.service.AulaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/alunos")
public class AlunoController {
    private final AlunoService alunoService;

    @PostMapping("/cadastrar")
    public AlunoRespostaDTO create(@RequestBody AlunoRequisicaoDTO aluno) {
        return alunoService.create(aluno);

    }

    @GetMapping("/listarTodos")
    public List<AlunoRespostaDTO> listAll() {
        return alunoService.listAll();

    }


    @GetMapping("/listarId/{id}")
    public AlunoRespostaDTO listId(@PathVariable("id") Long id) {
        return alunoService.findById(id);

    }

    @PutMapping("/atualizar/{id}")
    public AlunoRespostaDTO update(@PathVariable("id")long id, @RequestBody AlunoRequisicaoDTO alunoRequisicaoDTO) {
        return alunoService.update(id,alunoRequisicaoDTO);

    }


    @DeleteMapping("/deletar/{id}")
    public void delete(@PathVariable("id")Long id){
        alunoService.delete(id);
    }

}
