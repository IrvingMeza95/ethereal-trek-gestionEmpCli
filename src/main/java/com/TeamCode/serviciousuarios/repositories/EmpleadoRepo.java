package com.TeamCode.serviciousuarios.repositories;

import com.TeamCode.serviciousuarios.models.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmpleadoRepo extends JpaRepository<Empleado, String> {
    @Query("SELECT e FROM Empleado e WHERE e.id=?1 or e.email =?1 or e.dni =?1 or CONCAT(e.codigoDeLlamada, e.celular) =?1")
    Empleado buscarPorIdEmailDniCelular(String param);
}
