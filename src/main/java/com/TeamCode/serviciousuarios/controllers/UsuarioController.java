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
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Empleado> registrarEmpleado(@RequestBody Usuario empleado) throws MyException {
        return ResponseEntity.ok(iEmpleadoService.guardar(iUsuarioService.registrarEmpleado(empleado)));
    }

    @PostMapping("/cliente")
    public ResponseEntity<Cliente> registrarCliente(@RequestBody Usuario cliente) throws MyException {
        return ResponseEntity.ok(iClienteService.guardar(iUsuarioService.registrarCliente(cliente)));
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listar(){
        return ResponseEntity.ok(iUsuarioService.listar());
    }

    @DeleteMapping("/{param}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void aliminar(@PathVariable String param) throws MyException {
        iUsuarioService.eliminar(param);
    }

    @PutMapping("/{param}")
    public ResponseEntity<Usuario> editar(@RequestBody Usuario usuario, @PathVariable String param) throws MyException {
        return ResponseEntity.ok(iUsuarioService.editar(usuario,param));
    }

    @PutMapping("/password/{param}")
    public ResponseEntity<Usuario> cambiarPassword(@RequestParam String password, @PathVariable String param) throws MyException {
        return ResponseEntity.ok(iUsuarioService.cambairPassword(password,param));
    }

    @GetMapping("/{param}")
    public ResponseEntity<Usuario> buscarPorIdEmailDniCelular(@PathVariable String param) throws MyException {
        return ResponseEntity.ok(iUsuarioService.buscarPorIdEmailDniCelular(param));
    }

}
