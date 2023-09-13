package com.eliminatorias.apieliminatorias.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "results")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer visitingGoal;
    private Integer localGoal;
    private Integer numberCardRed;
    private Integer numberCardYell;
}
