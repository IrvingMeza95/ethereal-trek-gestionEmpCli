package com.TeamCode.serviciousuarios.controllers;

import com.TeamCode.serviciousuarios.exceptions.MyException;
import com.TeamCode.serviciousuarios.models.Cliente;
import com.TeamCode.serviciousuarios.models.Usuario;
import com.TeamCode.serviciousuarios.services.interfaces.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private IClienteService iClienteService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente crear(@RequestBody Usuario cliente){
        return iClienteService.guardar(cliente);
    }

    @GetMapping("/{param}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Cliente buscar(@PathVariable String param) throws MyException {
        return iClienteService.buscarPorIdEmailDniCelular(param);
    }

    @PutMapping("/{param}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Cliente editar(@RequestBody Cliente cliente, @PathVariable String param) throws MyException {
        return iClienteService.editar(cliente,param);
    }

    @DeleteMapping("/{param}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable String param) throws MyException {
        iClienteService.eliminar(param);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<Cliente> listar(){
        return iClienteService.listar();
    }

}
