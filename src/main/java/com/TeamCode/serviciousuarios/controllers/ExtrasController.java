package com.TeamCode.serviciousuarios.controllers;

import com.TeamCode.serviciousuarios.clientes.impl.PaisesRestImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/extras")
public class ExtrasController {

    @Autowired
    private PaisesRestImpl paises;

    @GetMapping("/paises-por-region/{region}")
    public List<String> paisesPorRegion(@PathVariable String region){
        return paises.paisesPorRegion(region);
    }

    @GetMapping("/paises")
    public List<String> buscarTodos(){
        return paises.buscarTodos();
    }

}
