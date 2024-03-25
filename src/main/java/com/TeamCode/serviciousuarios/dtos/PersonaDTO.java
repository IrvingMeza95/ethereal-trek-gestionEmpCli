package com.TeamCode.serviciousuarios.dtos;

import com.TeamCode.serviciousuarios.models.Role;
import lombok.*;

import java.util.List;

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
    private List<Role> roles;

}
