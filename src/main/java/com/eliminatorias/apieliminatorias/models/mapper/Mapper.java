package com.eliminatorias.apieliminatorias.models.mapper;

import com.eliminatorias.apieliminatorias.models.dtos.MatchDto;
import com.eliminatorias.apieliminatorias.models.entities.Match;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Mapper {
    public MatchDto convertEntityTODto(Match match){
        MatchDto matchDto = new MatchDto();
        matchDto.setId(match.getId());
        matchDto.setDate(match.getDate());
        matchDto.setStadium(match.getStadium());
        matchDto.setMainFerefe(match.getMainFerefe());
        matchDto.setIdLocalTeam(match.getLocalTeam().getIdTeam());
        matchDto.setIdVisitingTeam(match.getVisitingTeam().getIdTeam());
        matchDto.setScore(match.getScore());

        return matchDto;
    }
    public List<MatchDto> convertListMatchToDto(List<Match> matches){
        List<MatchDto> matchesDto = new ArrayList<>();
        matches.forEach((match) -> {
            MatchDto matchDto = convertEntityTODto(match);
        });
        return matchesDto;
    }
}
