package com.eliminatorias.apieliminatorias.repositories;

import com.eliminatorias.apieliminatorias.models.entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Long> {
    Optional<Team> findByName(String name);
}
