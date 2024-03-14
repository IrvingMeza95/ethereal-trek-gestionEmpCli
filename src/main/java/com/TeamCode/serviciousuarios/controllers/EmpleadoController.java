package com.TeamCode.serviciousuarios.controllers;

import com.TeamCode.serviciousuarios.dtos.EmpleadoDTO;
import com.TeamCode.serviciousuarios.exceptions.MyException;
import com.TeamCode.serviciousuarios.mappers.EmpleadoMapper;
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
    @Autowired
    private EmpleadoMapper empleadoMapper;

    @GetMapping("/{param}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public EmpleadoDTO buscar(@PathVariable String param) throws MyException {
        return empleadoMapper.getEmpleadoDTO(iEmpleadoService.buscarPorIdEmailDniCelular(param));
    }

    @PutMapping("/{param}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public EmpleadoDTO editar(@RequestBody Empleado empleado, @PathVariable String param) throws MyException {
        return empleadoMapper.getEmpleadoDTO(iEmpleadoService.editar(empleado,param));
    }

    @PutMapping("/password/{param}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public EmpleadoDTO cambiarPassword(@RequestParam String password, @PathVariable String param) throws MyException {
        return empleadoMapper.getEmpleadoDTO(iEmpleadoService.cambiarPassword(password,param));
    }

    @DeleteMapping("/{param}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable String param) throws MyException {
        iEmpleadoService.eliminar(param);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<EmpleadoDTO> listar(){
        return empleadoMapper.getListEmpleadoDTO(iEmpleadoService.listar());
    }

}
