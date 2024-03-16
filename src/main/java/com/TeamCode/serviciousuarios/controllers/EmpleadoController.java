package com.TeamCode.serviciousuarios.controllers;

import brave.Tracer;
import com.TeamCode.serviciousuarios.dtos.EmpleadoDTO;
import com.TeamCode.serviciousuarios.enums.Cargo;
import com.TeamCode.serviciousuarios.exceptions.MyException;
import com.TeamCode.serviciousuarios.mappers.EmpleadoMapper;
import com.TeamCode.serviciousuarios.models.Empleado;
import com.TeamCode.serviciousuarios.responses.ResponseMessage;
import com.TeamCode.serviciousuarios.services.interfaces.IEmpleadoService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empleados")
@Slf4j
public class EmpleadoController {

    @Autowired
    private IEmpleadoService iEmpleadoService;
    @Autowired
    private EmpleadoMapper empleadoMapper;
    @Autowired
    private Tracer tracer;

    @CircuitBreaker(name = "generic", fallbackMethod = "metodoAlternativo")
    @GetMapping("/{param}")
    public ResponseEntity<EmpleadoDTO> buscar(@PathVariable String param) throws MyException {
        return ResponseEntity.ok(empleadoMapper.getEmpleadoDTO(iEmpleadoService.buscarPorIdEmailDniCelular(param)));
    }

    @CircuitBreaker(name = "generic", fallbackMethod = "metodoAlternativo")
    @PutMapping("/{param}")
    public ResponseEntity<EmpleadoDTO> editar(@RequestBody Empleado empleado, @PathVariable String param) throws MyException {
        return ResponseEntity.ok(empleadoMapper.getEmpleadoDTO(iEmpleadoService.editar(empleado,param)));
    }

    @CircuitBreaker(name = "generic", fallbackMethod = "metodoAlternativo")
    @PutMapping("/password/{param}")
    public ResponseEntity<EmpleadoDTO> cambiarPassword(@RequestParam String password, @PathVariable String param) throws MyException {
        return ResponseEntity.ok(empleadoMapper.getEmpleadoDTO(iEmpleadoService.cambiarPassword(password,param)));
    }

    @CircuitBreaker(name = "generic", fallbackMethod = "metodoAlternativo")
    @DeleteMapping("/{param}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable String param) throws MyException {
        iEmpleadoService.eliminar(param);
    }

    @CircuitBreaker(name = "generic", fallbackMethod = "metodoAlternativo")
    @GetMapping
    public ResponseEntity<List<EmpleadoDTO>> listar(){
        return ResponseEntity.ok(empleadoMapper.getListEmpleadoDTO(iEmpleadoService.listar()));
    }

    @CircuitBreaker(name = "generic", fallbackMethod = "metodoAlternativo")
    @GetMapping("/cargos")
    public ResponseEntity<List<Cargo>> cargos(){
        return ResponseEntity.ok(List.of(Cargo.values()));
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
