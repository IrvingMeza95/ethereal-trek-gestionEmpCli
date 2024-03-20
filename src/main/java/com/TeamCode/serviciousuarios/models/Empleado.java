package com.TeamCode.serviciousuarios.models;

import jakarta.persistence.*;
import lombok.*;


@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "empleados")
public class Empleado extends Persona {
    @Column(nullable = false)
    private String cargo;
    @Column(nullable = false)
    private Double sueldo;
}
