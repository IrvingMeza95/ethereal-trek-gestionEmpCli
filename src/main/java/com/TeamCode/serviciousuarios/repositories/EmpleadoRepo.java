package com.TeamCode.serviciousuarios.repositories;

import com.TeamCode.serviciousuarios.models.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoRepo extends JpaRepository<Empleado, String> {
    Empleado findByEmail(String email);
}
