package com.TeamCode.serviciousuarios.models;

import com.TeamCode.serviciousuarios.enums.MediosDePago;
import com.TeamCode.serviciousuarios.enums.TipoDeVenta;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Venta {
    private Long numero;
    private TipoDeVenta tipoDeVenta;
    private MediosDePago medioDePago;
    private Date fecha;
    private Cliente cliente;
    private Empleado empleado;
    private Servicio servicio;
    private Paquete paquete;
}
