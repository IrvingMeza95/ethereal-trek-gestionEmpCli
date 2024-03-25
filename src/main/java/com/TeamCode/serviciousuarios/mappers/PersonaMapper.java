package com.TeamCode.serviciousuarios.mappers;

import com.TeamCode.serviciousuarios.dtos.PersonaDTO;
import com.TeamCode.serviciousuarios.exceptions.MyException;
import com.TeamCode.serviciousuarios.models.Persona;
import com.TeamCode.serviciousuarios.models.Usuario;
import com.TeamCode.serviciousuarios.services.interfaces.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersonaMapper {

    @Autowired
    private IUsuarioService iUsuarioService;

    public void fillPersonaDTO(PersonaDTO personaDTO, Persona persona) throws MyException {
        Usuario usuario = iUsuarioService.buscarPorIdEmailDniCelular(persona.getEmail());
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
        personaDTO.setRoles(usuario.getRoles());
    }
}
