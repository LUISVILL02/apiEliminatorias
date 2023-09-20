package com.eliminatorias.apieliminatorias.services;

import com.eliminatorias.apieliminatorias.models.dtos.MatchDto;
import com.eliminatorias.apieliminatorias.models.entities.Match;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MatchService {
    MatchDto save(MatchDto matchDto);
    List<MatchDto> findByDate(LocalDate date);
    List<MatchDto> findAllName(String name);
    Optional<MatchDto> finByid(Long id);
}
