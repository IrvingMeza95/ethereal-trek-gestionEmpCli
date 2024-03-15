package com.TeamCode.serviciousuarios.controllers;

import com.TeamCode.serviciousuarios.dtos.EmpleadoDTO;
import com.TeamCode.serviciousuarios.enums.Cargo;
import com.TeamCode.serviciousuarios.exceptions.MyException;
import com.TeamCode.serviciousuarios.mappers.EmpleadoMapper;
import com.TeamCode.serviciousuarios.models.Empleado;
import com.TeamCode.serviciousuarios.services.interfaces.IEmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<EmpleadoDTO> buscar(@PathVariable String param) throws MyException {
        return ResponseEntity.ok(empleadoMapper.getEmpleadoDTO(iEmpleadoService.buscarPorIdEmailDniCelular(param)));
    }

    @PutMapping("/{param}")
    public ResponseEntity<EmpleadoDTO> editar(@RequestBody Empleado empleado, @PathVariable String param) throws MyException {
        return ResponseEntity.ok(empleadoMapper.getEmpleadoDTO(iEmpleadoService.editar(empleado,param)));
    }

    @PutMapping("/password/{param}")
    public ResponseEntity<EmpleadoDTO> cambiarPassword(@RequestParam String password, @PathVariable String param) throws MyException {
        return ResponseEntity.ok(empleadoMapper.getEmpleadoDTO(iEmpleadoService.cambiarPassword(password,param)));
    }

    @DeleteMapping("/{param}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable String param) throws MyException {
        iEmpleadoService.eliminar(param);
    }

    @GetMapping
    public ResponseEntity<List<EmpleadoDTO>> listar(){
        return ResponseEntity.ok(empleadoMapper.getListEmpleadoDTO(iEmpleadoService.listar()));
    }

    @GetMapping("/cargos")
    public ResponseEntity<List<Cargo>> cargos(){
        return ResponseEntity.ok(List.of(Cargo.values()));
    }

}
