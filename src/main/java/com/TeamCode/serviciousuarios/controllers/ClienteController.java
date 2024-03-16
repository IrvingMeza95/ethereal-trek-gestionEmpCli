package com.TeamCode.serviciousuarios.controllers;

import brave.Tracer;
import com.TeamCode.serviciousuarios.dtos.ClienteDTO;
import com.TeamCode.serviciousuarios.exceptions.MyException;
import com.TeamCode.serviciousuarios.mappers.ClienteMapper;
import com.TeamCode.serviciousuarios.models.Cliente;
import com.TeamCode.serviciousuarios.responses.ResponseMessage;
import com.TeamCode.serviciousuarios.services.interfaces.IClienteService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@Slf4j
public class ClienteController {

    @Autowired
    private IClienteService iClienteService;
    @Autowired
    private ClienteMapper clienteMapper;
    @Autowired
    private Tracer tracer;

    @CircuitBreaker(name = "generic", fallbackMethod = "metodoAlternativo")
    @GetMapping("/{param}")
    public ResponseEntity<ClienteDTO> buscar(@PathVariable String param) throws MyException {
        return ResponseEntity.ok(clienteMapper.getClienteDTO(iClienteService.buscarPorIdEmailDniCelular(param)));
    }

    @CircuitBreaker(name = "generic", fallbackMethod = "metodoAlternativo")
    @PutMapping("/{param}")
    public ResponseEntity<ClienteDTO> editar(@RequestBody Cliente cliente, @PathVariable String param) throws MyException {
        return ResponseEntity.ok(clienteMapper.getClienteDTO(iClienteService.editar(cliente,param)));
    }

    @CircuitBreaker(name = "generic", fallbackMethod = "metodoAlternativo")
    @DeleteMapping("/{param}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable String param) throws MyException {
        iClienteService.eliminar(param);
    }

    @CircuitBreaker(name = "generic", fallbackMethod = "metodoAlternativo")
    @GetMapping
    public ResponseEntity<List<ClienteDTO>> listarClientes(){
        return ResponseEntity.ok(clienteMapper.getListClienteDTO(iClienteService.listar()));
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
