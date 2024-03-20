package com.TeamCode.serviciousuarios.services.interfaces;

import com.TeamCode.serviciousuarios.exceptions.MyException;
import com.TeamCode.serviciousuarios.models.Persona;
import com.TeamCode.serviciousuarios.models.Usuario;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface IUsuarioService {
    Usuario guardar(Usuario usuario) throws MyException;
    Usuario registrarEmpleado(Usuario empleado) throws MyException;
    Usuario registrarCliente(Usuario cliente) throws MyException;
    void eliminar(String param) throws MyException;
    List<Usuario> listar();
    Usuario editar(Usuario usuario, String email) throws MyException;
    Usuario buscarPorIdEmailDniCelular(String param) throws MyException;
    Usuario cambairPassword(String password, String param) throws MyException;
    Usuario usuarioMapper(Persona persona, String email) throws MyException;
}
