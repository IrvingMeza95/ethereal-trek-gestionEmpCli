package com.TeamCode.serviciousuarios.mappers;

import com.TeamCode.serviciousuarios.dtos.EmpleadoDTO;
import com.TeamCode.serviciousuarios.models.Empleado;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmpleadoMapper {

    public EmpleadoDTO getEmpleadoDTO(Empleado empleado){
        EmpleadoDTO empleadoDTO = new EmpleadoDTO();
        empleadoDTO.setCargo(empleado.getCargo());
        empleadoDTO.setSueldo(empleado.getSueldo());
        empleadoDTO.setId(empleado.getId());
        empleadoDTO.setNombre(empleado.getNombre());
        empleadoDTO.setApellido(empleado.getApellido());
        empleadoDTO.setDni(empleado.getDni());
        empleadoDTO.setFechaNac(empleado.getFechaNac());
        empleadoDTO.setPais(empleado.getPais());
        empleadoDTO.setCelular(empleado.getCelular());
        empleadoDTO.setEmail(empleado.getEmail());
        return empleadoDTO;
    }

    public List<EmpleadoDTO> getListEmpleadoDTO(List<Empleado> empleados){
        return empleados.stream().map(this::getEmpleadoDTO).collect(Collectors.toList());
    }

}
