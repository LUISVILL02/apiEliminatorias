package com.eliminatorias.apieliminatorias.models.mapper;

import com.eliminatorias.apieliminatorias.models.dtos.TeamDto;
import com.eliminatorias.apieliminatorias.models.entities.Team;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(
        componentModel = "spring",
        uses = {MatchMapper.class}
)
public interface TeamMapper {

    @Mappings({
            @Mapping(source = "localTeam", target = "localTeam"),
            @Mapping(source = "visitingTeam", target = "visitingTeam")
    })
    TeamDto teamToTeamDto(Team team);
    Team teamDtoToTeam(TeamDto teamDto);

}
