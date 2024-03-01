package com.TeamCode.serviciousuarios.services.impl;

import com.TeamCode.serviciousuarios.clientes.impl.UsuariosRestImpl;
import com.TeamCode.serviciousuarios.models.Cliente;
import com.TeamCode.serviciousuarios.models.Usuario;
import com.TeamCode.serviciousuarios.repositories.ClienteRepo;
import com.TeamCode.serviciousuarios.services.interfaces.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements IClienteService {

    @Autowired
    private ClienteRepo clienteRepo;
    @Autowired
    private UsuariosRestImpl usuariosRest;

    @Override
    public Cliente guardar(Usuario cliente) {
        Cliente nuevoCliente = new Cliente();
        nuevoCliente.setNombre(cliente.getNombre());
        nuevoCliente.setApellido(cliente.getApellido());
        nuevoCliente.setEmail(cliente.getEmail());
        nuevoCliente.setDni(cliente.getDni());
        nuevoCliente.setCelular(cliente.getCelular());
        return clienteRepo.save(nuevoCliente);
    }

    @Override
    public Cliente editar(Cliente cliente, String param) {
        Cliente clienteDb = buscarPorIdEmailDniCelular(param);
        clienteDb.setNombre(cliente.getNombre());
        clienteDb.setApellido(cliente.getApellido());
        clienteDb.setCelular(cliente.getCelular());
        clienteDb.setEmail(cliente.getEmail());
        clienteDb.setDni(cliente.getDni());
        clienteDb.setPais(cliente.getPais());
        clienteDb.setFechaNac(cliente.getFechaNac());
        usuariosRest.editar(clienteDb, cliente.getEmail());
        return clienteRepo.save(clienteDb);
    }

    @Override
    public void eliminar(String param) {
        Cliente cliente = buscarPorIdEmailDniCelular(param);
        usuariosRest.eliminar(cliente.getEmail());
        clienteRepo.delete(cliente);
    }

    @Override
    public List<Cliente> listar() {
        return clienteRepo.findAll();
    }

    @Override
    public Cliente buscarPorIdEmailDniCelular(String param) {
        return Optional.of(clienteRepo.buscarPorIdEmailDniCelular(param)).orElseThrow();
    }

}
