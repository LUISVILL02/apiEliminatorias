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
    @NotBlank(message = "The date cannot be empty")
    private LocalDate date;

    @NotBlank(message = "The stadium name cannot be empty ")
    private String stadium;
    
    @NotBlank(message = "The mainFerefe cannot be a empty")
    private String mainFerefe;
    private Long idLocalTeam;
    private Long idVisitingTeam;
    private ResulDto score;
}
