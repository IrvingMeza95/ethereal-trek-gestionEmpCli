package com.TeamCode.serviciousuarios.services.impl;

import com.TeamCode.serviciousuarios.clientes.impl.PaisesRestImpl;
import com.TeamCode.serviciousuarios.clientes.impl.UsuariosRestImpl;
import com.TeamCode.serviciousuarios.models.Cliente;
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
    private PaisesRestImpl paisesRest;
    @Autowired
    private UsuariosRestImpl usuariosRest;

    @Override
    public Cliente guardar(Cliente cliente) {
        return clienteRepo.save(cliente);
    }

    @Override
    public Optional<Cliente> buscarPorId(String id) {
        return clienteRepo.findById(id);
    }

    @Override
    public Cliente editar(Cliente cliente, String id) {
        Cliente clienteDb = buscarPorId(id).get();
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
    public void eliminarPorDni(String dni) {
        clienteRepo.deleteByDni(dni);
    }

    @Override
    public void eliminarPorEmail(String email) {
        usuariosRest.eliminarPorEmail(email);
        Cliente cliente = clienteRepo.findByEmail(email);
        clienteRepo.delete(cliente);
    }

    @Override
    public List<Cliente> listar() {
        return clienteRepo.findAll();
    }

    @Override
    public Cliente buscarPorDni(String dni) {
        return clienteRepo.findByDni(dni);
    }

}
