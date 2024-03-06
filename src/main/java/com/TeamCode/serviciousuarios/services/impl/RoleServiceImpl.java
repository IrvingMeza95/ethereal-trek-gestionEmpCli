package com.TeamCode.serviciousuarios.services.impl;

import com.TeamCode.serviciousuarios.models.Role;
import com.TeamCode.serviciousuarios.repositories.RoleRepo;
import com.TeamCode.serviciousuarios.services.interfaces.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private RoleRepo roleRepo;

    @Override
    public Role guardar(Role role) {
        return roleRepo.save(role);
    }

    @Override
    public void eliminar(Long id) {
        Role role = roleRepo.findById(id).get();
        roleRepo.delete(role);
    }

    @Override
    public List<Role> listar() {
        return roleRepo.findAll();
    }

}
