package com.eliminatorias.apieliminatorias.models.mapper;

import com.eliminatorias.apieliminatorias.models.entities.Result;
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
    public Result resultCopy(Result result){
        Result result1 = new Result();
        result1.setVisitingGoal(result.getVisitingGoal());
        result1.setLocalGoal(result.getLocalGoal());
        result1.setNumberCardRed(result.getNumberCardRed());
        result1.setNumberCardYell(result.getNumberCardYell());
        result1.setMatch(result.getMatch());

        return result1;
    }
}
