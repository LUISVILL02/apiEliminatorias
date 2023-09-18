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
    @OneToOne(mappedBy = "score")
    private Match match;

    public Result updateResult(Result result){
        return new Result(this.id, result.visitingGoal, result.localGoal,
                result.numberCardRed, result.numberCardYell, result.match);
    }
}
