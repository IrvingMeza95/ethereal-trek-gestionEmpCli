package com.TeamCode.serviciousuarios.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Paquete {
    private String codigo;
    private List<Servicio> servicios;
    private Double costo;
}
