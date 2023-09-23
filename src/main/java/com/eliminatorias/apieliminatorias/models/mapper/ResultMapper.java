package com.eliminatorias.apieliminatorias.models.mapper;

import com.eliminatorias.apieliminatorias.models.dtos.ResulDto;
import com.eliminatorias.apieliminatorias.models.entities.Result;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface ResultMapper {
    ResulDto resultToResultDto(Result result);
    Result resultDtoToResult(ResulDto resulDto);
}
