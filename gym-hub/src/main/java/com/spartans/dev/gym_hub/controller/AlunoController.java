package com.spartans.dev.gym_hub.controller;

import com.spartans.dev.gym_hub.dto.AlunoRequest;
import com.spartans.dev.gym_hub.dto.AlunoResponse;
import com.spartans.dev.gym_hub.service.AlunoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RequestMapping
@RestController("/alunos")
@RequiredArgsConstructor
public class AlunoController {

    private final AlunoService alunoService;

    @PostMapping("/cadastrar")
    public AlunoResponse criarAluno (AlunoRequest alunoRequest){
        try {
            return alunoService.criarAluno(alunoRequest);
        }catch (RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/listar")
    public List<AlunoResponse> listarAlunos (){
        try {
            return alunoService.listarAlunos();
        }catch (RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/listar/{id}")
    public AlunoResponse listarAlunoPorId(@PathVariable long id){
        try {
            return alunoService.listarPorId(id);
        }catch (RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/atualizar/{id}")
    public AlunoResponse atualizarAluno(@PathVariable long id, @RequestBody AlunoRequest alunoRequest){
        try{
            return alunoService.atualizarAluno(id, alunoRequest);
        }catch (RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/deletar/{id}")
    public void deletarAluno(@PathVariable long id){
        try{
            alunoService.deletarAluno(id);
        }catch (RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

}
