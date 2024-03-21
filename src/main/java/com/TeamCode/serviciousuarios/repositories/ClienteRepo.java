package com.TeamCode.serviciousuarios.repositories;

import com.TeamCode.serviciousuarios.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClienteRepo extends JpaRepository<Cliente, String> {
    Cliente findByEmail(String email);
    @Query("SELECT c FROM Cliente c WHERE c.id=?1 or c.email =?1 or c.dni =?1 or CONCAT(c.codigoDeLlamada, c.celular) =?1")
    Cliente buscarPorIdEmailDniCelular(String param);
}
