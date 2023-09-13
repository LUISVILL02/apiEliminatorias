package com.eliminatorias.apieliminatorias.models.dtos;

import com.eliminatorias.apieliminatorias.models.entities.Result;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MatchDto implements EntityDto{
    private Long id;
    private LocalDate date;
    private String stadium;
    private String mainFerefe;
    private Long idLocalTeam;
    private Long idVisitingTeam;
    private Result score;
}
