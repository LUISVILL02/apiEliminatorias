package com.eliminatorias.apieliminatorias.models.entities;

import jakarta.persistence.*;
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
    private Long id;
    private LocalDate date;
    private String stadium;
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
}
