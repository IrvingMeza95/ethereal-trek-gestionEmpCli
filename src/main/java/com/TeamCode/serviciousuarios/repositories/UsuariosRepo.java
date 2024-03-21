package com.TeamCode.serviciousuarios.repositories;

import com.TeamCode.serviciousuarios.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UsuariosRepo extends JpaRepository<Usuario, String> {
    @Query("SELECT u FROM Usuario u WHERE u.id =?1 or u.email =?1 or u.dni =?1 or CONCAT(u.codigoDeLlamada, u.celular) =?1")
    Usuario buscarPorIdEmailDniCelular(String param);
}
