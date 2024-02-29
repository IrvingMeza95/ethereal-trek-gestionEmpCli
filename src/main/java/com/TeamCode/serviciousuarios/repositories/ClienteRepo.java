package com.TeamCode.serviciousuarios.repositories;

import com.TeamCode.serviciousuarios.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepo extends JpaRepository<Cliente, String> {
    Cliente findByEmail(String email);
}
