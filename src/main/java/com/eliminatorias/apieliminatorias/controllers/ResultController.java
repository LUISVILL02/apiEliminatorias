package com.eliminatorias.apieliminatorias.controllers;

import com.eliminatorias.apieliminatorias.models.dtos.ResulDto;
import com.eliminatorias.apieliminatorias.models.entities.Result;
import com.eliminatorias.apieliminatorias.services.ResultService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@AllArgsConstructor
@RestController
@Validated
@RequestMapping("/apiEliminatorias/v1/results")
public class ResultController {
    private final ResultService resultService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResulDto> create(@RequestBody @Valid Result result){
        ResulDto resultCreate = resultService.create(result);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(resultCreate.getId())
                .toUri();
        return ResponseEntity.created(location).body(resultCreate);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResulDto> updateResult(@PathVariable @Min(1) Long id,
                                               @RequestBody @Valid Result result){
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
