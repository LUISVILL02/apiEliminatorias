package com.eliminatorias.apieliminatorias.services;

import com.eliminatorias.apieliminatorias.models.dtos.TeamDto;
import com.eliminatorias.apieliminatorias.models.entities.Team;

import java.util.List;
import java.util.Optional;

public interface TeamService {
    TeamDto create(Team team);
    List<TeamDto> getTeams();
    Optional<TeamDto> getTeam(String name);
    Optional<TeamDto> getTeamById(Long id);
    Optional<TeamDto> UpdateTeamById(Long id, Team team);
    void delete(Long id);
}
