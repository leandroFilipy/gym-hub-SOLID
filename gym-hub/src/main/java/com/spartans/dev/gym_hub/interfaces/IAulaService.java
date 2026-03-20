package com.spartans.dev.gym_hub.interfaces;

import com.spartans.dev.gym_hub.dto.aula.AulaRequest;
import com.spartans.dev.gym_hub.dto.aula.AulaResponse;
import com.spartans.dev.gym_hub.model.Aula;

import java.util.List;

public interface IAulaService {
    AulaResponse create(AulaRequest aulaRequest);
    List<AulaResponse> listAll();
    AulaResponse findById(long id);
    AulaResponse update(long id, AulaRequest aulaRequest);
    void delete(long id);

}
