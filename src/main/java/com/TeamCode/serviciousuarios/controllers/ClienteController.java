package com.TeamCode.serviciousuarios.controllers;

import com.TeamCode.serviciousuarios.models.Cliente;
import com.TeamCode.serviciousuarios.services.interfaces.IClienteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private IClienteService iClienteService;

    @PostMapping
    public ResponseEntity<Cliente> crearCliente(@RequestBody Cliente cliente){
        if (cliente == null) {
            log.info("Cliente es nulo, menor e igual a 0");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            log.info("Cliente Creado");
            return new ResponseEntity<>(iClienteService.guardar(cliente), HttpStatus.CREATED);
        } catch (Exception e) {
            log.info("Error interno en el servidor");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtenerCliente(@PathVariable String id){
        if (id == null || id.equals("")) {
            log.info("El id cliente es nulo o vacio");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            Optional<Cliente> optional = iClienteService.buscarPorId(id);
            if (optional.isPresent()) {
                Cliente cliente = optional.get();
                log.info("Cliente obtenido");
                return new ResponseEntity<>(cliente, HttpStatus.OK);
            } else {
                log.info("Error!!! Cliente no encontrado en la base de datos");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.info("Error interno en el servidor");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> listarClientes(){
        List<Cliente> listarClientes = iClienteService.listar();
        try {
            log.info("Lista de clientes obtenidas");
            return new ResponseEntity<>(listarClientes, HttpStatus.OK);
        } catch (Exception e) {
            log.info("Error interno en el servidor");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> editarCliente(@RequestBody Cliente cliente, @PathVariable String id){
        if (cliente == null || id.equals("0")) {
            log.info("El id cliente es nulo, menor e igual a 0");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            return new ResponseEntity<>(iClienteService.editar(cliente, id), HttpStatus.OK);
        } catch (Exception e) {
            log.info("Error interno en el servidor");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarCliente(@PathVariable String id){
        if (id == null || id.equals("")) {
            log.info("El id cliente es nulo, menor e igual a 0");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            Optional<Cliente> clienteEntityOptional = iClienteService.buscarPorId(id);
            if (clienteEntityOptional.isPresent()) {
                Cliente cliente = clienteEntityOptional.get();
                iClienteService.eliminarPorId(id);
                log.info("Cliente eliminado");
                return new ResponseEntity<>("Cliente Eliminado", HttpStatus.OK);
            } else {
                log.info("Error!!! Cliente a eliminar no existe en la base de datos");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.info("Error interno en el servidor");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/ver/dni/{dni}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Cliente buscarPorDni(@PathVariable String dni){
        return iClienteService.buscarPorDni(dni);
    }

    @DeleteMapping("/eliminar/id/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarPorId(@PathVariable String id){
        iClienteService.eliminarPorId(id);
    }

    @DeleteMapping("/eliminar/dni/{dni}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarPorDni(@PathVariable String dni){
        iClienteService.eliminarPorDni(dni);
    }

    @DeleteMapping("/eliminar/email/{email}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarPorEmail(@PathVariable String email){
        iClienteService.eliminarPorEmail(email);
    }



}
