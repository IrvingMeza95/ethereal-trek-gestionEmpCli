package com.TeamCode.serviciousuarios.models;

import com.TeamCode.serviciousuarios.enums.TipoServicio;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Servicio {
    private String codigo;
    private TipoServicio tipoServicio;
    private String nombre;
    private String descripcion;
    private String destino;
    private Date fecha;
    private Double costo;
}
