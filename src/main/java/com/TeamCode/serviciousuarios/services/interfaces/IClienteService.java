package com.TeamCode.serviciousuarios.services.interfaces;

import com.TeamCode.serviciousuarios.models.Cliente;
import com.TeamCode.serviciousuarios.models.Usuario;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IClienteService {
    Cliente guardar(Usuario cliente);
    Cliente editar(Cliente cliente, String id);
    void eliminar(String param);
    List<Cliente> listar();
    Cliente buscarPorIdEmailDniCelular(String param);
}
