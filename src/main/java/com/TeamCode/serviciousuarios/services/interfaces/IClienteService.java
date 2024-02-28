package com.TeamCode.serviciousuarios.services.interfaces;

import com.TeamCode.serviciousuarios.models.Cliente;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IClienteService {
    Cliente guardar(Cliente cliente);
    Cliente buscarPorId(String id);
    Cliente editar(Cliente cliente, String id);
    void eliminarPorId(String id);
    void eliminarPorDni(String dni);
    void eliminarPorEmail(String email);
    List<Cliente> listar();

    Cliente buscarPorDni(String dni);
}
