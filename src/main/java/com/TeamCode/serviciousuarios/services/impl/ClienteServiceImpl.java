package com.TeamCode.serviciousuarios.services.impl;

import com.TeamCode.serviciousuarios.clientes.impl.PaisesRestImpl;
import com.TeamCode.serviciousuarios.clientes.impl.UsuariosRestImpl;
import com.TeamCode.serviciousuarios.models.Cliente;
import com.TeamCode.serviciousuarios.repositories.ClienteRepo;
import com.TeamCode.serviciousuarios.services.interfaces.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements IClienteService {

    @Autowired
    private ClienteRepo clienteRepo;
    @Autowired
    private PaisesRestImpl paisesRest;
    @Autowired
    private UsuariosRestImpl usuariosRest;

    @Override
    public Cliente guardar(Cliente cliente) {
        return clienteRepo.save(cliente);
    }

    @Override
    public Cliente buscarPorId(String id) {
        return clienteRepo.findById(id).orElseThrow();
    }

    @Override
    public Cliente editar(Cliente cliente, String id) {
        Cliente clienteDb = buscarPorId(id);
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
    public void eliminarPorId(String id) {
        clienteRepo.deleteById(id);
    }

    @Override
    public List<Cliente> listar() {
        return clienteRepo.findAll();
    }

}
