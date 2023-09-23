package com.eliminatorias.apieliminatorias.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
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
    @Size(min = 1)
    private Long id;

    @PositiveOrZero
    private Integer visitingGoal;

    @PositiveOrZero 
    private Integer localGoal;

    @PositiveOrZero 
    private Integer numberCardRed;

    @PositiveOrZero 
    private Integer numberCardYell;
    @OneToOne(mappedBy = "score")
    private Match match;

    public Result updateResult(Result result){
        return new Result(this.id, result.visitingGoal, result.localGoal,
                result.numberCardRed, result.numberCardYell, result.match);
    }
}
