package com.TeamCode.serviciousuarios.services.interfaces;

import com.TeamCode.serviciousuarios.exceptions.MyException;
import com.TeamCode.serviciousuarios.models.Empleado;
import com.TeamCode.serviciousuarios.models.Usuario;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IEmpleadoService {
    Empleado guardar(Usuario empleado) throws MyException;
    Empleado editar(Empleado empleado, String param) throws MyException;
    void eliminar(String param) throws MyException;
    List<Empleado> listar();
    Empleado buscarPorIdEmailDniCelular(String param) throws MyException;
}
