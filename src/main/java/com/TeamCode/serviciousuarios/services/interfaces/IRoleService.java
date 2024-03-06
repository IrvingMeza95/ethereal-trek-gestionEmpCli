package com.TeamCode.serviciousuarios.services.interfaces;

import com.TeamCode.serviciousuarios.models.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRoleService {
    Role guardar(Role role);
    void eliminar(Long id);
    List<Role> listar();
}
