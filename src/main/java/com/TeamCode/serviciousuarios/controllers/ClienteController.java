package com.TeamCode.serviciousuarios.controllers;

import com.TeamCode.serviciousuarios.models.Cliente;
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

    @PostMapping("/crear")
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente crear(@RequestBody Cliente cliente){
        return iClienteService.guardar(cliente);
    }

    @GetMapping("/ver/id/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Cliente buscarPorId(@PathVariable String id){
        return iClienteService.buscarPorId(id);
    }

    @PutMapping("/editar/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Cliente editar(@RequestBody Cliente cliente, @PathVariable String id){
        return iClienteService.editar(cliente,id);
    }

    @DeleteMapping("/eliminar/id/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarPorId(@PathVariable String id){
        iClienteService.eliminarPorId(id);
    }

    @GetMapping("/listar")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<Cliente> listar(){
        return iClienteService.listar();
    }

}
