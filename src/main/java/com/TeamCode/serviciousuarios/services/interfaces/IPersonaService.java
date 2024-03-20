package com.TeamCode.serviciousuarios.services.interfaces;

import com.TeamCode.serviciousuarios.models.Persona;
import org.springframework.stereotype.Component;

@Component
public interface IPersonaService {
    void editar(Persona personaDb, Persona nuevaPersona);
}
