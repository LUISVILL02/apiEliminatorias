package com.eliminatorias.apieliminatorias.models.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MatchDto{
    private Long id;
    @JsonFormat(pattern = "dd/MM/yyyy",timezone = "GMT-5")
    private LocalDate date;
    private String stadium;
    private String mainFerefe;
    private Long idLocalTeam;
    private Long idVisitingTeam;
    private ResulDto score;
}
