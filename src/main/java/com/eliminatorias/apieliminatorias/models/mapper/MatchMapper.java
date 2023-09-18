package com.eliminatorias.apieliminatorias.models.mapper;

import com.eliminatorias.apieliminatorias.models.dtos.MatchDto;
import com.eliminatorias.apieliminatorias.models.entities.Match;
import com.eliminatorias.apieliminatorias.models.entities.Result;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(
        componentModel = "spring",
        uses = {ResultMapper.class, TeamMapper.class}
)
public interface MatchMapper {

    @Mappings({
            @Mapping(source = "localTeam.idTeam", target = "idLocalTeam"),
            @Mapping(source = "visitingTeam.idTeam", target = "idVisitingTeam"),
            @Mapping(source = "score.id", target = "score")
    })
    MatchDto matchToMatchDto(Match match);

    @InheritInverseConfiguration
    Match matchDtoToMatch(MatchDto matchDto);
}
