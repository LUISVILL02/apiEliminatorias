package com.eliminatorias.apieliminatorias.controllers;

import com.eliminatorias.apieliminatorias.models.dtos.ResulDto;
import com.eliminatorias.apieliminatorias.models.entities.Result;
import com.eliminatorias.apieliminatorias.services.ResultService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@AllArgsConstructor
@RestController
@validated
@RequestMapping("/api/v1/results")
public class ResultController {
    private final ResultService resultService;

    @PostMapping
    public ResponseEntity<ResulDto> create(@RequestBody @valid Result result){
        ResulDto resultCreate = resultService.create(result);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(resultCreate.getId())
                .toUri();
        return ResponseEntity.created(location).body(resultCreate);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResulDto> updateResult(@PathVariable @min(1)Long id,
                                               @RequestBody @valid Result result){
        Optional<ResulDto> result1 = resultService.update(id, result);
        return result1.map(resultUpdate -> ResponseEntity.ok().body(resultUpdate))
                        .orElseGet(()->{
                            ResulDto result2 = resultService.create(result);
                            URI location = ServletUriComponentsBuilder
                                    .fromCurrentRequest()
                                    .path("/{id}")
                                    .buildAndExpand(result2.getId())
                                    .toUri();
                            return ResponseEntity.created(location).body(result2);
                        });
    }
}
