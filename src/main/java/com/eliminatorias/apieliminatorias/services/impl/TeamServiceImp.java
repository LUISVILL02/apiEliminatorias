package com.eliminatorias.apieliminatorias.services.impl;

import com.eliminatorias.apieliminatorias.exceptions.teamExcept.TeamNoFoundExcep;
import com.eliminatorias.apieliminatorias.models.dtos.TeamDto;
import com.eliminatorias.apieliminatorias.models.entities.Team;
import com.eliminatorias.apieliminatorias.models.mapper.Clonable;
import com.eliminatorias.apieliminatorias.models.mapper.TeamMapper;
import com.eliminatorias.apieliminatorias.repositories.TeamRepository;
import com.eliminatorias.apieliminatorias.services.TeamService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class TeamServiceImp implements TeamService {

    private final TeamRepository teamRepository;
    private final Clonable clonable;
    private final TeamMapper teamMapper;
    @Override
    public TeamDto create(Team team) {
        Team teamCopy = clonable.teamCopy(team);
        teamRepository.save(teamCopy);
        return teamMapper.teamToTeamDto(teamCopy);
    }

    @Override
    public List<TeamDto> getTeams() {
        List<Team> team =  teamRepository.findAll();
        List<TeamDto> teamDto = null;
        teamDto = team.stream().map(teamMapper::teamToTeamDto).collect(Collectors.toList());
        return teamDto;
    }

    @Override
    public Optional<TeamDto> getTeamById(Long id) {
        Optional<Team> team = teamRepository.findById(id);
        if (team.isPresent()){
            return team.map(teamMapper::teamToTeamDto);
        }
        throw new TeamNoFoundExcep("No se encontro el equipo con id: "+ id);
    }

    @Override
    public Optional<TeamDto> getTeam(String name) {
        Optional<Team> team = teamRepository.findByName(name);
        if (team.isEmpty()){
            throw new TeamNoFoundExcep("No se encontro el equipo con nombre: "+ name);
        }
        return team.map(teamMapper::teamToTeamDto);
    }

    @Override
    public Optional<TeamDto> UpdateTeamById(Long id, Team team) {
        Optional<Team> teamInDB = teamRepository.findById(id);
        if (teamInDB.isPresent()){
            Team teamCopy = teamInDB.get().UpdateTeamWith(team);
            teamRepository.save(teamCopy);
            return Optional.of(teamMapper.teamToTeamDto(teamCopy));
        }
        TeamDto teamDto = teamMapper.teamToTeamDto(teamRepository.save(team));
        return Optional.ofNullable(teamDto);
    }

    @Override
    public void delete(Long id) {
        teamRepository.deleteById(id);
    }
}
