package com.eliminatorias.apieliminatorias.models.entities;

import jakarta.persistence.*;
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
    private String name;
    private String flag;
    private String coach;
    @OneToMany(mappedBy = "localTeam")
    private List<Match> localTeam;
    @OneToMany(mappedBy = "visitingTeam")
    private List<Match> visitingTeam;

    public Team UpdateTeamWith(Team team){
        return new Team(this.idTeam, this.name, this.flag, this.coach, this.localTeam, this.visitingTeam);
    }
}
