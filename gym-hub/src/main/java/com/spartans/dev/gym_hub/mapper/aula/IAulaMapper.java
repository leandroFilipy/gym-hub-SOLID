package com.spartans.dev.gym_hub.mapper.aula;

import com.spartans.dev.gym_hub.dto.aula.AulaRequest;
import com.spartans.dev.gym_hub.dto.aula.AulaResponse;
import com.spartans.dev.gym_hub.model.Aula;

public interface IAulaMapper {
    Aula paraEntidade(AulaRequest aulaRequest);
    AulaResponse paraDTO (Aula aula);
}
