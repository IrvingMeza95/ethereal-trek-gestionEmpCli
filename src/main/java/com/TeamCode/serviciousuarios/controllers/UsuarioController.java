package com.TeamCode.serviciousuarios.controllers;

import brave.Tracer;
import com.TeamCode.serviciousuarios.exceptions.MyException;
import com.TeamCode.serviciousuarios.models.Cliente;
import com.TeamCode.serviciousuarios.models.Empleado;
import com.TeamCode.serviciousuarios.responses.ResponseMessage;
import com.TeamCode.serviciousuarios.models.Usuario;
import com.TeamCode.serviciousuarios.services.interfaces.IClienteService;
import com.TeamCode.serviciousuarios.services.interfaces.IEmpleadoService;
import com.TeamCode.serviciousuarios.services.interfaces.IUsuarioService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
@Slf4j
public class UsuarioController {

    @Autowired
    private IUsuarioService iUsuarioService;
    @Autowired
    private IEmpleadoService iEmpleadoService;
    @Autowired
    private IClienteService iClienteService;
    private final Logger log = LoggerFactory.getLogger(UsuarioController.class);
    @Autowired
    private Tracer tracer;

    @CircuitBreaker(name = "generic", fallbackMethod = "metodoAlternativo")
    @PostMapping("/empleado")
    public ResponseEntity<Empleado> registrarEmpleado(@RequestBody Usuario empleado) throws MyException {
        return ResponseEntity.ok(iEmpleadoService.guardar(iUsuarioService.registrarEmpleado(empleado)));
    }

    @CircuitBreaker(name = "generic", fallbackMethod = "metodoAlternativo")
    @PostMapping("/cliente")
    public ResponseEntity<Cliente> registrarCliente(@RequestBody Usuario cliente) throws MyException {
        return ResponseEntity.ok(iClienteService.guardar(iUsuarioService.registrarCliente(cliente)));
    }

    @CircuitBreaker(name = "generic", fallbackMethod = "metodoAlternativo")
    @GetMapping
    public ResponseEntity<List<Usuario>> listar(){
        return ResponseEntity.ok(iUsuarioService.listar());
    }

    @CircuitBreaker(name = "generic", fallbackMethod = "metodoAlternativo")
    @DeleteMapping("/{param}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void aliminar(@PathVariable String param) throws MyException {
        iUsuarioService.eliminar(param);
    }

    @CircuitBreaker(name = "generic", fallbackMethod = "metodoAlternativo")
    @PutMapping("/{param}")
    public ResponseEntity<Usuario> editar(@RequestBody Usuario usuario, @PathVariable String param) throws MyException {
        return ResponseEntity.ok(iUsuarioService.editar(usuario,param));
    }

    @CircuitBreaker(name = "generic", fallbackMethod = "metodoAlternativo")
    @PutMapping("/password/{param}")
    public ResponseEntity<Usuario> cambiarPassword(@RequestParam String password, @PathVariable String param) throws MyException {
        return ResponseEntity.ok(iUsuarioService.cambairPassword(password,param));
    }

    @CircuitBreaker(name = "generic", fallbackMethod = "metodoAlternativo")
    @GetMapping("/{param}")
    public ResponseEntity<Usuario> buscarPorIdEmailDniCelular(@PathVariable String param) throws MyException {
        return ResponseEntity.ok(iUsuarioService.buscarPorIdEmailDniCelular(param));
    }

    public ResponseEntity<ResponseMessage> metodoAlternativo(Throwable e){
        String error = "";
        if (e.getMessage() != null){
            error  = "Error: " + e.getMessage();
        } else if (e.fillInStackTrace() != null) {
            error = "Error: " + e.fillInStackTrace();
        }else {
            error = "Error: Algo salió mal.";
        }

        log.error(error);
        tracer.currentSpan().tag("mensaje.error", error);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage(error));
    }

}
