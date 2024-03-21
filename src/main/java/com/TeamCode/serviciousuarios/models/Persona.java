package com.TeamCode.serviciousuarios.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class Persona {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private String apellido;
    @Column(nullable = false, unique = true)
    private String dni;
    @Column(nullable = true)
    private String fechaNac;
    @Column(nullable = true)
    private String pais;
    @Column(nullable = false, unique = true)
    private String celular;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = true)
    private String password;
    @Column(nullable = true, length = 250)
    private String direccion;
    @Column(nullable = false)
    private Boolean enabled;
    @Column(nullable = false)
    private String codigoDeLlamada;
}
