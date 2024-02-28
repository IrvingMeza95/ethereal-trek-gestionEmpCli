package com.TeamCode.serviciousuarios.clientes.interfaces;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@FeignClient(name = "servicio-paises", url = "https://restcountries.com/v3.1")
public interface IPaisesRest {
    @GetMapping("/region/{region}")
    Object[] paisesPorRegion(@PathVariable String region);
    @GetMapping("/all")
    Object[] buscarTodos();
    @GetMapping("/name/{name}")
    Object[] buscarPorNombre(@PathVariable String name);
}
