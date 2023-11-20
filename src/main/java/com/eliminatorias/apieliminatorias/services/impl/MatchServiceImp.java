package com.eliminatorias.apieliminatorias.services.impl;

import com.eliminatorias.apieliminatorias.exceptions.matchExcept.MatchNoFoundExceptByDate;
import com.eliminatorias.apieliminatorias.models.dtos.MatchDto;
import com.eliminatorias.apieliminatorias.models.dtos.ResulDto;
import com.eliminatorias.apieliminatorias.models.entities.Match;
import com.eliminatorias.apieliminatorias.models.entities.Result;
import com.eliminatorias.apieliminatorias.models.mapper.Clonable;
import com.eliminatorias.apieliminatorias.models.mapper.MatchMapper;
import com.eliminatorias.apieliminatorias.models.mapper.ResultMapper;
import com.eliminatorias.apieliminatorias.repositories.MatchRepository;
import com.eliminatorias.apieliminatorias.services.MatchService;
import com.eliminatorias.apieliminatorias.services.ResultService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class MatchServiceImp implements MatchService {
    private final MatchRepository matchRepository;
    private final ResultService resultService;
    private final MatchMapper matchMapper;
    private final ResultMapper resultMapper;

    @Override
    public MatchDto save(MatchDto matchDto) {
        Match match = matchMapper.matchDtoToMatch(matchDto);
        return matchMapper.matchToMatchDto(matchRepository.save(match));
    }

    @Override
    public List<MatchDto> findByDate(LocalDate date) {
        List<Match> matchList = matchRepository.findByDate(date);
        if (matchList.isEmpty()){
            throw new MatchNoFoundExceptByDate("No se encontraron partidos para la fecha: "+ date);
        }
      
        List<MatchDto> matchDtoList = null;
        matchDtoList = matchList.stream().map(matchMapper::matchToMatchDto).collect(Collectors.toList());
        return matchDtoList;
    }

    @Override
    public List<MatchDto> findAllName(String name) {
        List<Match> matchList = matchRepository.findByName(name);
        if (matchList.isEmpty()){
            throw new MatchNoFoundExceptByDate("No se encontraron partidos para el equipo: "+ name);
        }
        List<MatchDto> matchDtoList = null;
        matchDtoList = matchList.stream().map(matchMapper::matchToMatchDto).collect(Collectors.toList());
        return matchDtoList;
    }

    @Override
    public Optional<MatchDto> finByid(Long id) {
        Optional<Match> match = matchRepository.findById(id);
        if (match.isEmpty()){
            throw new RuntimeException("sin resultados para el partido con id: "+ id);
        }
        return match.map(matchMapper::matchToMatchDto);
    }

    @Override
    public List<MatchDto> findAll() {
        return matchRepository.findAll().stream().map(matchMapper::matchToMatchDto).collect(Collectors.toList());
    }

    @Override
    public Optional<MatchDto> update(Long id, MatchDto match) {
        Optional<Match> matchfind = matchRepository.findById(id);
        if (matchfind.isEmpty()){
            throw new RuntimeException("No se encontro el partido con id: "+ id);
        }
        MatchDto match1 = matchMapper.matchToMatchDto(matchfind.get());
        match1.setDate(match.getDate());
        match1.setStadium(match.getStadium());
        match1.setMainFerefe(match.getMainFerefe());
        match1.setIdLocalTeam(match.getIdLocalTeam());
        match1.setIdVisitingTeam(match.getIdVisitingTeam());

        ResulDto resulDto = match.getScore();

        Optional<ResulDto> result = resultService.update(match1.getScore().getId(), resultMapper.resultDtoToResult(resulDto));

        match1.setScore(result.get());


        return Optional.of(matchMapper.matchToMatchDto(matchRepository.save(matchMapper.matchDtoToMatch(match1))));
    }
}
