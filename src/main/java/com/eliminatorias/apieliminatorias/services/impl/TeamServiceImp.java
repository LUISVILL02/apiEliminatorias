package com.eliminatorias.apieliminatorias.services.impl;

import com.eliminatorias.apieliminatorias.models.entities.Team;
import com.eliminatorias.apieliminatorias.models.mapper.Clonable;
import com.eliminatorias.apieliminatorias.repositories.TeamRepository;
import com.eliminatorias.apieliminatorias.services.TeamService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class TeamServiceImp implements TeamService {

    private final TeamRepository teamRepository;
    private final Clonable clonable;
    @Override
    public Team create(Team team) {
        Team teamCopy = clonable.teamCopy(team);
        return teamRepository.save(teamCopy);
    }

    @Override
    public List<Team> getTeams() {
        return teamRepository.findAll();
    }

    @Override
    public Optional<Team> getTeam(String name) {
        return teamRepository.findByName(name);
    }

    @Override
    public Optional<Team> UpdateTeamById(Long id, Team team) {
        return teamRepository.findById(id).map(oldTeam -> {
            Team upDaateTeam = oldTeam.UpdateTeamWith(team);
            return teamRepository.save(upDaateTeam);
        });
    }

    @Override
    public void delete(Long id) {
        teamRepository.deleteById(id);
    }
}
