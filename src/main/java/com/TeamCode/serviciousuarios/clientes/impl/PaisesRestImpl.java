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

    public List<String> extraerCodigosDeLlamadaPorPaises(String paisSeleccionado){
        Object[] paises = iPaisesRest.buscarTodos();
        List<String> codigos = new ArrayList<>();
        for (Object pais : paises){
            String common = String.valueOf(((Map)((Map)pais).get("name")).get("common"));
            if (common.equalsIgnoreCase(paisSeleccionado)){
                String root = (String) ((Map)((Map)pais).get("idd")).get("root");
                List<String> suffixes = (List<String>) ((Map)((Map)pais).get("idd")).get("suffixes");
                if (suffixes != null){
                    for (String codigo : suffixes){
                        codigos.add(root + codigo);
                    }
                }
            }
        }
        Collections.sort(codigos);
        return codigos;
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
