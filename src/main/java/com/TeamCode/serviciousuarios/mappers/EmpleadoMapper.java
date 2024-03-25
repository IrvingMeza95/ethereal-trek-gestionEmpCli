package com.TeamCode.serviciousuarios.mappers;

import com.TeamCode.serviciousuarios.dtos.EmpleadoDTO;
import com.TeamCode.serviciousuarios.exceptions.MyException;
import com.TeamCode.serviciousuarios.models.Empleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmpleadoMapper {

    @Autowired
    private PersonaMapper personaMapper;

    public EmpleadoDTO getEmpleadoDTO(Empleado empleado) throws MyException {
        EmpleadoDTO empleadoDTO = new EmpleadoDTO();
        empleadoDTO.setCargo(empleado.getCargo());
        empleadoDTO.setSueldo(empleado.getSueldo());
        personaMapper.fillPersonaDTO(empleadoDTO, empleado);
        return empleadoDTO;
    }

    public List<EmpleadoDTO> getListEmpleadoDTO(List<Empleado> empleados){
        return empleados.stream().map(e -> {
            try {
                return getEmpleadoDTO(e);
            } catch (MyException ex) {
                throw new RuntimeException(ex);
            }
        }).collect(Collectors.toList());
    }

}
