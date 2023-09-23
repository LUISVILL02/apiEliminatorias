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

    @NotBlank(message = "the name cannot be empty")
    private String name;

    @NotBlank(message = "There are should be a URL")
    private String flag;

    @NotBlank(message = "The coach name cannot be blank")
    private String coach;
}
