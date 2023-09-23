package com.eliminatorias.apieliminatorias.models.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "matchs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    @Size(min = 1)
    private Long id;

    @JsonFormat(pattern = "dd/MM/yyyy", timezone = "GMT-5")
    @NotBlank(message = "the date can't be a empty")
    private LocalDate date;

    @NotBlank(message = "The stadium should have a name")
    private String stadium;

    @NotBlank(message = "The mainFerefe cannot be a empty")
    private String mainFerefe;
    @ManyToOne
    @JoinColumn(name = "idLocalTeam")
    private Team localTeam;
    @ManyToOne
    @JoinColumn(name = "idVisitingTeam")
    private Team visitingTeam;
    @OneToOne(optional = false)
    @JoinColumn(name = "id_score", referencedColumnName = "id")
    private Result score;

    public Match updateMatch(Match match){
        return new Match(this.id, match.date, match.stadium, match.mainFerefe,
                match.localTeam, match.visitingTeam, match.score);
    }
}
