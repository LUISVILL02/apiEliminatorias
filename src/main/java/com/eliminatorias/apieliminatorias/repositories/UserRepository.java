package com.eliminatorias.apieliminatorias.repositories;

import com.eliminatorias.apieliminatorias.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String username);
    Optional<User> findByEmail(String email);
    Boolean existsByUserName(String username);
    Boolean existsByEmail(String email);
}
