package com.eliminatorias.apieliminatorias.services;

import com.eliminatorias.apieliminatorias.models.dtos.ResulDto;
import com.eliminatorias.apieliminatorias.models.entities.Result;

import java.util.Optional;

public interface ResultService {
    ResulDto create(Result result);
    Optional<ResulDto> update(Long id, Result result);
}
