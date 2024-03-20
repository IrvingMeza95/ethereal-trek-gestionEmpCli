package com.TeamCode.serviciousuarios.services.interfaces;

import com.TeamCode.serviciousuarios.exceptions.MyException;
import com.TeamCode.serviciousuarios.models.Cliente;
import com.TeamCode.serviciousuarios.models.Usuario;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface IClienteService {
    Cliente guardar(Usuario cliente);
    Cliente editar(Cliente cliente, String param) throws MyException;
    void eliminar(String param) throws MyException;
    List<Cliente> listar();
    Cliente buscarPorIdEmailDniCelular(String param) throws MyException;
}
