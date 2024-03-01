package com.TeamCode.serviciousuarios.models;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Usuario {
    private String id;
    private String nombre;
    private String apellido;
    private String email;
    private String password;
    private String celular;
    private String dni;

}
