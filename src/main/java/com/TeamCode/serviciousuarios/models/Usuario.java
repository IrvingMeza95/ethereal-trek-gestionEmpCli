package com.TeamCode.serviciousuarios.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private String apellido;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = true)
    private String password;
    @Column(nullable = false)
    private String codigoDeLlamada;
    @Column(unique = true, nullable = false)
    private String celular;
    @Column(nullable = false, unique = true)
    private String dni;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = { "usuario_id", "rol_id" }) }
    )
    private List<Role> roles;
    @Column(nullable = false)
    private String pais;
    @Column(nullable = false)
    private Boolean enabled;
    @Column(nullable = false)
    private Integer intentos;
}
