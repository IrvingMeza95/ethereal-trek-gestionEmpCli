package com.TeamCode.serviciousuarios.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;


@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@Entity
@Table(name = "clientes")
public class Cliente extends Persona {
    public Cliente() {
    }
}
