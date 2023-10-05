package com.eliminatorias.apieliminatorias.repositories;

import com.eliminatorias.apieliminatorias.models.entities.Role;
import com.eliminatorias.apieliminatorias.models.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
