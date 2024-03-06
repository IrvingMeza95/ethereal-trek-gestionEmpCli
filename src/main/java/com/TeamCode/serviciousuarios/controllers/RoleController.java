package com.TeamCode.serviciousuarios.controllers;

import com.TeamCode.serviciousuarios.models.Role;
import com.TeamCode.serviciousuarios.services.interfaces.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private IRoleService iRoleService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Role crear(@RequestBody Role role){
        return iRoleService.guardar(role);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id){
        iRoleService.eliminar(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<Role> listar(){
        return iRoleService.listar();
    }

}
