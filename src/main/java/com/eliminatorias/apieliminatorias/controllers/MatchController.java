package com.eliminatorias.apieliminatorias.controllers;

import com.eliminatorias.apieliminatorias.models.dtos.MatchDto;
import com.eliminatorias.apieliminatorias.models.entities.Match;
import com.eliminatorias.apieliminatorias.services.MatchService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@AllArgsConstructor
@RestController
@RequestMapping("apiEliminatorias/v1/Matches")
public class MatchController {
    private final MatchService matchService;

    @PostMapping
    public ResponseEntity<MatchDto> create(@RequestBody Match match){
        MatchDto match1 = matchService.save(match);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(match1.getId())
                .toUri();
        return ResponseEntity.created(location).body(match1);
    }
}
