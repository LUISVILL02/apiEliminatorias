package com.eliminatorias.apieliminatorias.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "teams")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTeam;

    @NotBlank(message = "The name cannot be empty")
    private String name;

    @NotBlank(message = "There are be a URL")
    private String flag;

    @NotBlank(message = "The coach name cannot be empty")
    private String coach;
    @OneToMany(mappedBy = "localTeam")
    private List<Match> localTeam;
    @OneToMany(mappedBy = "visitingTeam")
    private List<Match> visitingTeam;

    public Team UpdateTeamWith(Team team){
        return new Team(this.idTeam, team.name, team.flag, team.coach, team.localTeam, team.visitingTeam);
    }
}
