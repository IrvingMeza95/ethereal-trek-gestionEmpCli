package com.TeamCode.serviciousuarios.controllers;

import com.TeamCode.serviciousuarios.exceptions.MyException;
import com.TeamCode.serviciousuarios.models.Empleado;
import com.TeamCode.serviciousuarios.models.Usuario;
import com.TeamCode.serviciousuarios.services.interfaces.IEmpleadoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empleados")
public class EmpleadoController {

    @Autowired
    private IEmpleadoService iEmpleadoService;
    private  final Logger log = LoggerFactory.getLogger(EmpleadoController.class);

//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public Empleado crear(@RequestBody Usuario empleado) throws MyException {
//        return iEmpleadoService.guardar(empleado);
//    }

    @GetMapping("/{param}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Empleado buscar(@PathVariable String param) throws MyException {
        return iEmpleadoService.buscarPorIdEmailDniCelular(param);
    }

    @PutMapping("/{param}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Empleado editar(@RequestBody Empleado empleado, @PathVariable String param) throws MyException {
        return iEmpleadoService.editar(empleado,param);
    }

    @PutMapping("/password/{param}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Empleado cambiarPassword(@RequestParam String password, @PathVariable String param) throws MyException {
        return iEmpleadoService.cambiarPassword(password,param);
    }

    @DeleteMapping("/{param}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable String param) throws MyException {
        iEmpleadoService.eliminar(param);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<Empleado> listar(){
        return iEmpleadoService.listar();
    }

}
