package com.TeamCode.serviciousuarios.dtos;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class PersonaDTO {
    private String id;
    private String nombre;
    private String apellido;
    private String dni;
    private String fechaNac;
    private String pais;
    private String celular;
    private String email;
    private String direccion;
    private Boolean enabled;
    private String codigoDeLlamada;
}
