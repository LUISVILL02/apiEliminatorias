package com.eliminatorias.apieliminatorias.services;

import com.eliminatorias.apieliminatorias.models.dtos.MatchDto;
import com.eliminatorias.apieliminatorias.models.entities.Match;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MatchService {
    MatchDto save(Match match);
    List<MatchDto> findAllDate(LocalDate date);
    List<MatchDto> finAllName(String name);
    Optional<MatchDto> finByid(Long id);
}