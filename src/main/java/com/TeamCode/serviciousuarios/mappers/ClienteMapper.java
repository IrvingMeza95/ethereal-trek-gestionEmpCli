package com.TeamCode.serviciousuarios.mappers;

import com.TeamCode.serviciousuarios.dtos.ClienteDTO;
import com.TeamCode.serviciousuarios.models.Cliente;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClienteMapper {

    public ClienteDTO getClienteDTO(Cliente cliente){
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setId(cliente.getId());
        clienteDTO.setNombre(cliente.getNombre());
        clienteDTO.setApellido(cliente.getApellido());
        clienteDTO.setDni(cliente.getDni());
        clienteDTO.setFechaNac(cliente.getFechaNac());
        clienteDTO.setPais(cliente.getPais());
        clienteDTO.setCelular(cliente.getCelular());
        clienteDTO.setEmail(cliente.getEmail());
        return clienteDTO;
    }

    public List<ClienteDTO> getListClienteDTO(List<Cliente> clientes){
        return clientes.stream().map(this::getClienteDTO).collect(Collectors.toList());
    }

}
