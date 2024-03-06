package com.TeamCode.serviciousuarios.repositories;

import com.TeamCode.serviciousuarios.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Long> {
}
