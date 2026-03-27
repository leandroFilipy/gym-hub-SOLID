package com.spartans.dev.gym_hub.controller;

import com.spartans.dev.gym_hub.dto.aula.AulaRequest;
import com.spartans.dev.gym_hub.dto.aula.AulaResponse;
import com.spartans.dev.gym_hub.service.aulaService.IAulaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/class")
public class AulaController {

    private final IAulaService iAulaService;

    @PostMapping("/register")
    public AulaResponse create(@RequestBody AulaRequest aula) {
        return iAulaService.create(aula);

    }

    @GetMapping("/list")
    public List<AulaResponse> listAll() {
        return iAulaService.listAll();

    }


    @GetMapping("/list/{id}")
    public AulaResponse listId(@PathVariable("id") Long id) {
        return iAulaService.findById(id);

    }

    @PutMapping("/update/{id}")
    public AulaResponse update(@PathVariable("id")long id, @RequestBody AulaRequest aulaRequisicaoDTO) {
        return iAulaService.update(id,aulaRequisicaoDTO);

    }


    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id")Long id){
        iAulaService.delete(id);
    }


}
