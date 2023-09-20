package com.eliminatorias.apieliminatorias.controllers;

import com.eliminatorias.apieliminatorias.models.dtos.MatchDto;
import com.eliminatorias.apieliminatorias.models.entities.Match;
import com.eliminatorias.apieliminatorias.services.MatchService;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("apiEliminatorias/v1/Matches")
public class MatchController {
    private final MatchService matchService;

    @PostMapping
    public ResponseEntity<MatchDto> create(@RequestBody MatchDto matchDto){
        MatchDto match1 = matchService.save(matchDto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(match1.getId())
                .toUri();
        return ResponseEntity.created(location).body(match1);
    }

     @GetMapping 
     public ResponseEntity<List<MatchDto>> FindByDate(@RequestParam(required = false) String date){
        LocalDate localDate = LocalDate.parse(date);
        if(localDate != null){
            List<MatchDto> match = matchService.findByDate(localDate);
                return new ResponseEntity<>(match ,HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
     }
     
      @GetMapping
      public ResponseEntity<List<MatchDto>> FindAllName(@RequestParam(required = false) String name){
        if(name != null){
            List<MatchDto> match = matchService.findAllName(name);
            return new ResponseEntity<>(match, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
      } 

}
