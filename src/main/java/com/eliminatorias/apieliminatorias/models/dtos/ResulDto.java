package com.eliminatorias.apieliminatorias.models.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResulDto {

 
    private Long id;

    @PositiveOrZero
    private Integer visitingGoal;

    @PositiveOrZero
    private Integer localGoal;

    @PositiveOrZero
    private Integer numberCardRed;

    @PositiveOrZero
    private Integer numberCardYell;

}
