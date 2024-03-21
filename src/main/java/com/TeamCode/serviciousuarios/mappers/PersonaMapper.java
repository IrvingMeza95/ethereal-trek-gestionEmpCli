package com.TeamCode.serviciousuarios.mappers;

import com.TeamCode.serviciousuarios.dtos.PersonaDTO;
import com.TeamCode.serviciousuarios.models.Persona;
import org.springframework.stereotype.Component;

@Component
public class PersonaMapper {
    public void fillPersonaDTO(PersonaDTO personaDTO, Persona persona){
        personaDTO.setId(persona.getId());
        personaDTO.setNombre(persona.getNombre());
        personaDTO.setApellido(persona.getApellido());
        personaDTO.setDni(persona.getDni());
        personaDTO.setFechaNac(persona.getFechaNac());
        personaDTO.setPais(persona.getPais());
        personaDTO.setCodigoDeLlamada(persona.getCodigoDeLlamada());
        personaDTO.setCelular(persona.getCelular());
        personaDTO.setEmail(persona.getEmail());
        personaDTO.setDireccion(persona.getDireccion());
        personaDTO.setEnabled(persona.getEnabled());
        personaDTO.setDireccion(persona.getDireccion());
    }
}
