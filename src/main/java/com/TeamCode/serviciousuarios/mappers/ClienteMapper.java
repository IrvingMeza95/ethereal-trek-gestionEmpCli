package com.TeamCode.serviciousuarios.mappers;

import com.TeamCode.serviciousuarios.dtos.ClienteDTO;
import com.TeamCode.serviciousuarios.exceptions.MyException;
import com.TeamCode.serviciousuarios.models.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClienteMapper {

    @Autowired
    private PersonaMapper personaMapper;

    public ClienteDTO getClienteDTO(Cliente cliente) throws MyException {
        ClienteDTO clienteDTO = new ClienteDTO();
        personaMapper.fillPersonaDTO(clienteDTO, cliente);
        return clienteDTO;
    }

    public List<ClienteDTO> getListClienteDTO(List<Cliente> clientes){
        return clientes.stream().map(c -> {
            try {
                return getClienteDTO(c);
            } catch (MyException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());
    }

}
