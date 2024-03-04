package com.TeamCode.serviciousuarios.clientes.impl;

import com.TeamCode.serviciousuarios.clientes.interfaces.ServicioUsuariosRest;
import com.TeamCode.serviciousuarios.models.Persona;
import com.TeamCode.serviciousuarios.models.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuariosRestImpl {

    @Autowired
    private ServicioUsuariosRest servicioUsuariosRest;

    public Usuario editar(Persona persona, String email){
        Usuario usuario = new Usuario();
        usuario.setNombre(persona.getNombre());
        usuario.setApellido(persona.getApellido());
        usuario.setEmail(persona.getEmail());
        usuario.setDni(persona.getDni());
        usuario.setCelular(persona.getCelular());
        usuario.setEnabled(true);
        usuario.setIntentos(0);
        return servicioUsuariosRest.editar(usuario,email);
    }

    public Usuario cambiarPassword(String password, String param){
        return servicioUsuariosRest.cambiarPassword(password,param);
    }

    public void eliminar(String param){
        servicioUsuariosRest.eliminar(param);
    }

}
