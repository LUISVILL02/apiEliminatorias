package com.eliminatorias.apieliminatorias.models.mapper;

import com.eliminatorias.apieliminatorias.models.entities.Team;
import org.springframework.stereotype.Component;

@Component
public class Clonable {
    public Team teamCopy(Team team){
        Team team1 = new Team();
        team1.setName(team.getName());
        team1.setFlag(team.getFlag());
        team1.setCoach(team.getCoach());
        team1.setVisitingTeam(team.getVisitingTeam());
        team1.setLocalTeam(team.getLocalTeam());

        return team1;
    }
}
