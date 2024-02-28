package com.TeamCode.serviciousuarios.clientes.interfaces;

import com.TeamCode.serviciousuarios.models.Usuario;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@FeignClient("servicio-autentificacion-usuarios")
public interface IAuthUsuarioRest {
    @PutMapping("/auth/editar/email/{email}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    Usuario editar(@RequestBody Usuario usuario, @PathVariable String email);
    @DeleteMapping("/auth/eliminar/id/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarPorId(@PathVariable String id);
    @DeleteMapping("/auth/eliminar/email/{email}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarPorEmail(@PathVariable String email);
}
