package com.eliminatorias.apieliminatorias.services.impl;

import com.eliminatorias.apieliminatorias.models.dtos.MatchDto;
import com.eliminatorias.apieliminatorias.models.entities.Match;
import com.eliminatorias.apieliminatorias.models.mapper.Mapper;
import com.eliminatorias.apieliminatorias.repositories.MatchRepository;
import com.eliminatorias.apieliminatorias.services.MatchService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class MatchServiceImp implements MatchService {
    private final MatchRepository matchRepository;
    private final Mapper mapper;

    @Override
    public MatchDto save(Match match) {
        Match match1 = matchRepository.save(match);
        return mapper.convertEntityTODto(match1);
    }

    @Override
    public List<MatchDto> findAllDate(LocalDate date) {
        List<Match> matches = matchRepository.findAllByDate(date);
        return mapper.convertListMatchToDto(matches);
    }

    @Override
    public List<MatchDto> finAllName(String name) {
        List<Match> matches = matchRepository.findAllByName(name);
        return mapper.convertListMatchToDto(matches);
    }

    @Override
    public Optional<MatchDto> finByid(Long id) {
        Optional<Match> match = matchRepository.findById(id);
        return match.map(mapper::convertEntityTODto);
    }
}
