package com.TeamCode.serviciousuarios.services.impl;

import com.TeamCode.serviciousuarios.models.Persona;
import com.TeamCode.serviciousuarios.services.interfaces.IPersonaService;
import org.springframework.stereotype.Service;

@Service
public class PersonaService implements IPersonaService {
    @Override
    public void editar(Persona personaDb, Persona nuevaPersona) {
        personaDb.setNombre(nuevaPersona.getNombre());
        personaDb.setApellido(nuevaPersona.getApellido());
        personaDb.setCelular(nuevaPersona.getCelular());
        personaDb.setEmail(nuevaPersona.getEmail());
        personaDb.setDni(nuevaPersona.getDni());
        personaDb.setPais(nuevaPersona.getPais());
        personaDb.setFechaNac(nuevaPersona.getFechaNac());
        personaDb.setDireccion(nuevaPersona.getDireccion());
    }
}
