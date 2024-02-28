package com.TeamCode.serviciousuarios.clientes.impl;

import com.TeamCode.serviciousuarios.clientes.interfaces.IPaisesRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PaisesRestImpl {

    @Autowired
    private IPaisesRest iPaisesRest;

    public List<String> paisesPorRegion(String region){
        return extraerPaises(iPaisesRest.paisesPorRegion(region));
    }

    public List<String> buscarTodos(){
        return extraerPaises(iPaisesRest.buscarTodos());
    }

    public String extraerCodigoDeLlamadaPorPais(String name){
        Object[] pais = iPaisesRest.buscarPorNombre(name.toLowerCase());
        String root = (String) ((Map)((Map)pais[0]).get("idd")).get("root");
        List<String> suffixes = (List<String>) ((Map)((Map)pais[0]).get("idd")).get("suffixes");
        String callingCode = root + suffixes.get(0);
        return callingCode;
    }

    private List<String> extraerPaises(Object[] request){
        List<String> listaPaises = new ArrayList<>();
        for (int i = 0; i < request.length - 1; i++) {
            listaPaises.add(
                    (String) ((Map)((Map)request[i]).get("name")).get("common")
            );
        }
        Collections.sort(listaPaises);
        return listaPaises;
    }

}
