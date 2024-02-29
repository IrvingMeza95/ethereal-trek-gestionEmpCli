package com.TeamCode.serviciousuarios.controllers;

import com.TeamCode.serviciousuarios.exceptions.MyException;
import com.TeamCode.serviciousuarios.models.Empleado;
import com.TeamCode.serviciousuarios.services.interfaces.IEmpleadoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/api/empleados")
public class EmpleadoController {

    @Autowired
    private IEmpleadoService iEmpleadoService;

    @PostMapping
    public ResponseEntity<Empleado> crearEmpleado(@RequestBody Empleado empleado) throws MyException {
        if (empleado == null) {
            log.info("Empleado es nulo, menor e igual a 0");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            log.info("Empleado Creado");
            return new ResponseEntity<>(iEmpleadoService.guardar(empleado), HttpStatus.CREATED);
        } catch (Exception e) {
            log.info("Error interno en el servidor");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empleado> obtenerEmpleado(@PathVariable(value = "id") String idEmpleado){
        if (idEmpleado == null || idEmpleado.equals("")) {
            log.info("El id cliente es nulo o vacio");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            Optional<Empleado> optional = iEmpleadoService.buscarPorId(idEmpleado);
            if (optional.isPresent()) {
                Empleado empleado = optional.get();
                log.info("Empleado obtenido");
                return new ResponseEntity<>(empleado, HttpStatus.OK);
            } else {
                log.info("Error!!! Empleado no encontrado en la base de datos");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.info("Error interno en el servidor");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<Empleado>> listarEmpleados(){
        List<Empleado> listarEmpleados = iEmpleadoService.listar();
        try {
            log.info("Lista de empleados obtenidas");
            return new ResponseEntity<>(listarEmpleados, HttpStatus.OK);
        } catch (Exception e) {
            log.info("Error interno en el servidor");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Empleado> actualizarEmpleado(@RequestBody Empleado empleado, @PathVariable String id){
        if (empleado == null || id.equals("0")) {
            log.info("El id empleado es nulo, menor e igual a 0");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            return new ResponseEntity<>(iEmpleadoService.editar(empleado, id), HttpStatus.OK);
        } catch (Exception e) {
            log.info("Error interno en el servidor");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarEmpleado(@PathVariable String id){
        if (id == null || id.equals("")) {
            log.info("El id empleado es nulo, menor e igual a 0");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            Optional<Empleado> empleadoEntityOptional = iEmpleadoService.buscarPorId(id);
            if (empleadoEntityOptional.isPresent()) {
                Empleado empleado = empleadoEntityOptional.get();
                iEmpleadoService.eliminarPorId(id);
                log.info("Empleado eliminado");
                return new ResponseEntity<>("Empleado Eliminado", HttpStatus.OK);
            } else {
                log.info("Error!!! Empleado a eliminar no existe en la base de datos");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.info("Error interno en el servidor");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/eliminar/email/{email}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarPorEmail(@PathVariable String email){
        iEmpleadoService.eliminarPorEmail(email);
    }

}
