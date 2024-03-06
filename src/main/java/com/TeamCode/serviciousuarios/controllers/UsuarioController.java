package com.TeamCode.serviciousuarios.controllers;

import com.TeamCode.serviciousuarios.exceptions.MyException;
import com.TeamCode.serviciousuarios.models.Cliente;
import com.TeamCode.serviciousuarios.models.Empleado;
import com.TeamCode.serviciousuarios.models.Usuario;
import com.TeamCode.serviciousuarios.services.interfaces.IClienteService;
import com.TeamCode.serviciousuarios.services.interfaces.IEmpleadoService;
import com.TeamCode.serviciousuarios.services.interfaces.IUsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class UsuarioController {

    @Autowired
    private IUsuarioService iUsuarioService;
    @Autowired
    private IEmpleadoService iEmpleadoService;
    @Autowired
    private IClienteService iClienteService;
    private final Logger log = LoggerFactory.getLogger(UsuarioController.class);

    @PostMapping("/empleado")
    @ResponseStatus(HttpStatus.CREATED)
    public Empleado registrarEmpleado(@RequestBody Usuario empleado) throws MyException {
        return iEmpleadoService.guardar(iUsuarioService.registrarEmpleado(empleado));
    }

    @PostMapping("/cliente")
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente registrarCliente(@RequestBody Usuario cliente) throws MyException {
        return iClienteService.guardar(iUsuarioService.registrarCliente(cliente));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<Usuario> listar(){
        return iUsuarioService.listar();
    }

    @DeleteMapping("/{param}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void aliminar(@PathVariable String param) throws MyException {
        iUsuarioService.eliminar(param);
    }

    @PutMapping("/{param}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Usuario editar(@RequestBody Usuario usuario, @PathVariable String param) throws MyException {
        return iUsuarioService.editar(usuario,param);
    }

    @PutMapping("/password/{param}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Usuario cambiarPassword(@RequestParam String password, @PathVariable String param) throws MyException {
        return iUsuarioService.cambairPassword(password,param);
    }

    @GetMapping("/{param}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Usuario buscarPorIdEmailDniCelular(@PathVariable String param) throws MyException {
        return iUsuarioService.buscarPorIdEmailDniCelular(param);
    }

}
