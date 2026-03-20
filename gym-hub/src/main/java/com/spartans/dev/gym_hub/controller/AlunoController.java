package com.spartans.dev.gym_hub.controller;

import com.spartans.dev.gym_hub.dto.aluno.AlunoRequest;
import com.spartans.dev.gym_hub.dto.aluno.AlunoResponse;
import com.spartans.dev.gym_hub.interfaces.IAlunoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/client")
public class AlunoController {
    private final IAlunoService iAlunoService;


    @PostMapping("/register")
    public AlunoResponse create(@RequestBody AlunoRequest aluno) {
        return iAlunoService.create(aluno);

    }

    @GetMapping("/list")
    public List<AlunoResponse> listAll() {
        return iAlunoService.listAll();

    }


    @GetMapping("/list/{id}")
    public AlunoResponse listId(@PathVariable("id") Long id) {
        return iAlunoService.findById(id);

    }

    @PutMapping("/update/{id}")
    public AlunoResponse update(@PathVariable("id")long id, @RequestBody AlunoRequest alunoRequisicaoDTO) {
        return iAlunoService.update(id,alunoRequisicaoDTO);

    }


    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id")Long id){
        iAlunoService.delete(id);
    }

}
