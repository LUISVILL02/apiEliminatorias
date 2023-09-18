package com.eliminatorias.apieliminatorias.services.impl;

import com.eliminatorias.apieliminatorias.models.dtos.ResulDto;
import com.eliminatorias.apieliminatorias.models.entities.Result;
import com.eliminatorias.apieliminatorias.models.mapper.Clonable;
import com.eliminatorias.apieliminatorias.models.mapper.ResultMapper;
import com.eliminatorias.apieliminatorias.repositories.ResultRepository;
import com.eliminatorias.apieliminatorias.services.ResultService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class ResultServiceImp implements ResultService {

    private final ResultRepository resultRepository;
    private final Clonable clonable;
    private final ResultMapper resultMapper;
    @Override
    public ResulDto create(Result result) {
        Result resultCopy = clonable.resultCopy(result);
        return resultMapper.resultToResultDto(resultRepository.save(resultCopy));
    }

    @Override
    public Optional<ResulDto> update(Long id, Result result) {
        return resultRepository.findById(id).map(oldResult -> {
            Result updateResult = oldResult.updateResult(result);
            return resultMapper.resultToResultDto(resultRepository.save(updateResult));
        });
    }
}
