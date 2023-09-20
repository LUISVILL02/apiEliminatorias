package com.eliminatorias.apieliminatorias.repositories;

import com.eliminatorias.apieliminatorias.models.entities.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface MatchRepository extends JpaRepository<Match, Long> {
    List<Match> findByDate(LocalDate date);

    @Query("Select m from Match m where m.localTeam.name = ?1 or m.visitingTeam.name = ?1")
    List<Match> findByName(String name);
}
