package com.TeamCode.serviciousuarios.controllers;

import com.TeamCode.serviciousuarios.exceptions.MyException;
import com.TeamCode.serviciousuarios.models.Empleado;
import com.TeamCode.serviciousuarios.services.interfaces.IEmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empleados")
public class EmpleadoController {

    @Autowired
    private IEmpleadoService iEmpleadoService;

    @PostMapping("/crear")
    @ResponseStatus(HttpStatus.CREATED)
    public Empleado crear(@RequestBody Empleado empleado) throws MyException {
        return iEmpleadoService.guardar(empleado);
    }

    @GetMapping("/ver/id/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Empleado buscarPorId(@PathVariable String id){
        return iEmpleadoService.buscarPorId(id);
    }

    @PutMapping("/editar/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Empleado editar(@RequestBody Empleado empleado, @PathVariable String id){
        return iEmpleadoService.editar(empleado,id);
    }

    @DeleteMapping("/eliminar/id/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarPorId(@PathVariable String id){
        iEmpleadoService.eliminarPorId(id);
    }

    @GetMapping("/listar")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<Empleado> listar(){
        return iEmpleadoService.listar();
    }

}
