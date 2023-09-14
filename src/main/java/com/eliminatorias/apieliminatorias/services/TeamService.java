package com.eliminatorias.apieliminatorias.services;

import com.eliminatorias.apieliminatorias.models.entities.Team;

import java.util.List;
import java.util.Optional;

public interface TeamService {
    Team create(Team team);
    List<Team> getTeams();
    Optional<Team> getTeam(String name);
    Optional<Team> UpdateTeamById(Long id, Team team);
    void delete(Long id);
}
