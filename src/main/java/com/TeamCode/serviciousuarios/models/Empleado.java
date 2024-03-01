package com.TeamCode.serviciousuarios.models;

import com.TeamCode.serviciousuarios.enums.Cargo;
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
    @Enumerated(EnumType.STRING)
    private Cargo cargo;
    @Column(nullable = false)
    private Double sueldo;

}
