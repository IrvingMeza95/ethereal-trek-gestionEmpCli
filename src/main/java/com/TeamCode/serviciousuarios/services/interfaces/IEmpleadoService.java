package com.TeamCode.serviciousuarios.services.interfaces;

import com.TeamCode.serviciousuarios.exceptions.MyException;
import com.TeamCode.serviciousuarios.models.Empleado;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IEmpleadoService {
    Empleado guardar(Empleado empleado) throws MyException;
    Empleado buscarPorId(String id);
    Empleado editar(Empleado empleado, String id);
    void eliminarPorId(String id);
    void eliminarPorEmail(String email);
    List<Empleado> listar();
    Empleado buscarPorEmail(String email);
}
