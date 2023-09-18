package com.eliminatorias.apieliminatorias.models.mapper;

import com.eliminatorias.apieliminatorias.models.dtos.ResulDto;
import com.eliminatorias.apieliminatorias.models.entities.Result;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(
        componentModel = "spring",
        uses = {MatchMapper.class}
)
public interface ResultMapper {


    @Mappings({
            @Mapping(source = "visitingGoal", target = "visitingGoal"),
            @Mapping(source = "localGoal", target = "localGoal"),
            @Mapping(source = "numberCardRed", target = "numberCardRed"),
            @Mapping(source = "numberCardYell", target = "numberCardYell"),
            @Mapping(source = "match", target = "match"),
            @Mapping(source = "match.id", target = "match.score")

    })
    ResulDto resultToResultDto(Result result);
    @Mapping(source = "match.score", target = "match.score.id")
    Result resultDtoToResult(ResulDto resultDto);
}
