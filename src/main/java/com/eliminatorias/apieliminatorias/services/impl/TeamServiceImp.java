package com.eliminatorias.apieliminatorias.services.impl;

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
    public Optional<TeamDto> getTeam(String name) {
        Optional<Team> team = teamRepository.findByName(name);
        return team.map(teamMapper::teamToTeamDto);
    }

    @Override
    public Optional<TeamDto> UpdateTeamById(Long id, Team team) {
        Optional<Team> teamInDB = teamRepository.findById(id);
        teamInDB.ifPresent(team1 -> System.out.println("service "+ team1.getIdTeam()));
        if (teamInDB.isPresent()){
            System.out.println("prueba desde el service"+ teamInDB.get());
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
