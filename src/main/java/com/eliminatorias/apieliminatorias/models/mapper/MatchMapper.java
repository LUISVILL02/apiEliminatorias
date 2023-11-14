package com.eliminatorias.apieliminatorias.models.mapper;

import com.eliminatorias.apieliminatorias.models.dtos.MatchDto;
import com.eliminatorias.apieliminatorias.models.entities.Match;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring"
)
public interface MatchMapper {

    @Mappings({
            @Mapping(source = "localTeam.idTeam", target = "idLocalTeam"),
            @Mapping(source = "visitingTeam.idTeam", target = "idVisitingTeam")
    })
    MatchDto matchToMatchDto(Match match);
    @InheritInverseConfiguration
    Match matchDtoToMatch(MatchDto matchDto);
}
