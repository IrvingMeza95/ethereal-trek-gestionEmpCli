package com.TeamCode.serviciousuarios.services.impl;

import com.TeamCode.serviciousuarios.models.Persona;
import com.TeamCode.serviciousuarios.models.Usuario;
import com.TeamCode.serviciousuarios.services.interfaces.IPersonaService;
import org.springframework.stereotype.Service;

@Service
public class PersonaService implements IPersonaService {
    @Override
    public void crear(Usuario usuario, Persona persona) {
        persona.setNombre(usuario.getNombre());
        persona.setApellido(usuario.getApellido());
        persona.setEmail(usuario.getEmail());
        persona.setDni(usuario.getDni());
        persona.setCelular(usuario.getCelular());
        persona.setEnabled(true);
        persona.setPais(usuario.getPais());
        persona.setCodigoDeLlamada(usuario.getCodigoDeLlamada());
    }

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
        personaDb.setCodigoDeLlamada(nuevaPersona.getCodigoDeLlamada());
        personaDb.setEnabled(nuevaPersona.getEnabled());
    }
}
