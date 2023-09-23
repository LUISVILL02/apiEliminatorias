package com.eliminatorias.apieliminatorias.models.dtos;

import com.eliminatorias.apieliminatorias.models.entities.Match;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TeamDto {
    private Long idTeam;
    private String name;
    private String flag;
    private String coach;
}
