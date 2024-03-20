package com.TeamCode.serviciousuarios.controllers;

import com.TeamCode.serviciousuarios.clientes.impl.PaisesRestImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/paises")
public class PaisesController {

    @Autowired
    private PaisesRestImpl paises;

    @GetMapping("/region/{region}")
    public List<String> paisesPorRegion(@PathVariable String region){
        return paises.paisesPorRegion(region);
    }

    @GetMapping
    public List<String> buscarTodos(){
        return paises.buscarTodos();
    }

    @GetMapping("/codigos-de-llamda/{pais}")
    public List<String> codigosDeLlamada(@PathVariable String pais){
        return paises.extraerCodigosDeLlamadaPorPaises(pais);
    }

}
