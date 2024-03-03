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

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public Double getSueldo() {
        return sueldo;
    }

    public void setSueldo(Double sueldo) {
        this.sueldo = sueldo;
    }
}
