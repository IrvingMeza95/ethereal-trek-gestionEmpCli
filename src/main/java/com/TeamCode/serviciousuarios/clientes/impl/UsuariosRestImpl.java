package com.TeamCode.serviciousuarios.clientes.impl;

import com.TeamCode.serviciousuarios.clientes.interfaces.IAuthUsuarioRest;
import com.TeamCode.serviciousuarios.models.Persona;
import com.TeamCode.serviciousuarios.models.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuariosRestImpl {

    @Autowired
    private IAuthUsuarioRest iAuthUsuarioRest;
    public Usuario editar(Persona persona, String email){
        Usuario usuario = Usuario.builder()
                .email(persona.getEmail())
                .celular(persona.getCelular())
                .password(persona.getPassword())
                .build();
        return iAuthUsuarioRest.editar(usuario,email);
    }

    public void eliminarPorEmail(String email){
        iAuthUsuarioRest.eliminarPorEmail(email);
    }

}
