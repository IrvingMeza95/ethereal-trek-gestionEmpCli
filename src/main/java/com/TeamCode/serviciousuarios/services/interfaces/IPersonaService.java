package com.TeamCode.serviciousuarios.services.interfaces;

import com.TeamCode.serviciousuarios.models.Persona;
import com.TeamCode.serviciousuarios.models.Usuario;
import org.springframework.stereotype.Component;

@Component
public interface IPersonaService {
    void crear(Usuario usuario, Persona persona);
    void editar(Persona personaDb, Persona nuevaPersona);
}
