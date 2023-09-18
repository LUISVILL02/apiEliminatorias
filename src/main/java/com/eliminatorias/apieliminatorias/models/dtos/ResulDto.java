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
    private Integer visitingGoal;
    private Integer localGoal;
    private Integer numberCardRed;
    private Integer numberCardYell;
    @JsonIgnore
    private MatchDto match;
}
