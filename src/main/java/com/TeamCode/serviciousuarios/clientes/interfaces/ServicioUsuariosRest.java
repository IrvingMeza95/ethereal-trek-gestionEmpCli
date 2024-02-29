package com.TeamCode.serviciousuarios.clientes.interfaces;

import com.TeamCode.serviciousuarios.models.Usuario;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@FeignClient("servicioUsuarios")
public interface ServicioUsuariosRest {
    @PutMapping("/auth/editar/email/{email}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    Usuario editar(@RequestBody Usuario usuario, @PathVariable String email);
    @DeleteMapping("/auth/{param}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void eliminar(@PathVariable String param);
}
