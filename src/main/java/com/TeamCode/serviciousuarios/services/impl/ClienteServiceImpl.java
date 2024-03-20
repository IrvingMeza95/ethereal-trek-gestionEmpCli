package com.TeamCode.serviciousuarios.services.impl;

import com.TeamCode.serviciousuarios.exceptions.MyException;
import com.TeamCode.serviciousuarios.models.Cliente;
import com.TeamCode.serviciousuarios.models.Usuario;
import com.TeamCode.serviciousuarios.repositories.ClienteRepo;
import com.TeamCode.serviciousuarios.services.interfaces.IClienteService;
import com.TeamCode.serviciousuarios.services.interfaces.IPersonaService;
import com.TeamCode.serviciousuarios.services.interfaces.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements IClienteService {

    @Autowired
    private ClienteRepo clienteRepo;
    @Autowired
    private IUsuarioService iUsuarioService;
    @Autowired
    private IPersonaService iPersonaService;

    @Override
    public Cliente guardar(Usuario cliente) {
        Cliente nuevoCliente = new Cliente();
        iPersonaService.crear(cliente,nuevoCliente);
        return clienteRepo.save(nuevoCliente);
    }

    @Override
    public Cliente editar(Cliente cliente, String param) throws MyException {
        Cliente clienteDb = buscarPorIdEmailDniCelular(param);
        iPersonaService.editar(clienteDb, cliente);
        iUsuarioService.usuarioMapper(clienteDb, cliente.getEmail());
        return clienteRepo.save(clienteDb);
    }

    @Override
    public void eliminar(String param) throws MyException {
        Cliente cliente = buscarPorIdEmailDniCelular(param);
        iUsuarioService.eliminar(cliente.getEmail());
        clienteRepo.delete(cliente);
    }

    @Override
    public List<Cliente> listar() {
        return clienteRepo.findAll();
    }

    @Override
    public Cliente buscarPorIdEmailDniCelular(String param) throws MyException {
        Optional<Cliente> cliente = Optional.of(clienteRepo.buscarPorIdEmailDniCelular(param));
        if (!cliente.isPresent())
            throw new MyException("No se encontró ningún cliente asociado a " + param + ".");
        return cliente.get();
    }

}
