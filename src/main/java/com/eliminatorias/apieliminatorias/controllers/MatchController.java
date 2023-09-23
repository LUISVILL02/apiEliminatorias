package com.eliminatorias.apieliminatorias.controllers;

import com.eliminatorias.apieliminatorias.models.dtos.MatchDto;
import com.eliminatorias.apieliminatorias.models.entities.Match;
import com.eliminatorias.apieliminatorias.models.mapper.MatchMapper;
import com.eliminatorias.apieliminatorias.services.MatchService;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@validated
@RequestMapping("apiEliminatorias/v1/Matches")
public class MatchController {
    private final MatchService matchService;
    private final MatchMapper matchMapper;
    @PostMapping
    public ResponseEntity<MatchDto> create(@RequestBody @valid MatchDto matchDto){
        MatchDto match1 = matchService.save(matchDto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(match1.getId())
                .toUri();
        return ResponseEntity.created(location).body(match1);
    }

     @GetMapping 
     public ResponseEntity<List<MatchDto>> getByDate(@RequestParam(required = false) @NotBlank String date){
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(date, formatter);
        List<MatchDto> match = matchService.findByDate(localDate);
        return new ResponseEntity<>(match ,HttpStatus.OK);
     }
     
      @GetMapping("/byName")
      public ResponseEntity<List<MatchDto>> getByName(@RequestParam(required = false) @NotBlank String name){
        if(name != null){
            List<MatchDto> match = matchService.findAllName(name);
            return new ResponseEntity<>(match, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
      }

      @PatchMapping("/{id}")
      public ResponseEntity<MatchDto> update(@PathVariable @min(1)Long id, @RequestBody @valid MatchDto match){
        Optional<MatchDto> matchFind = matchService.update(id, match);
        if (matchFind.isPresent()){
            return ResponseEntity.ok().body(matchFind.get());
        }
          URI location = ServletUriComponentsBuilder
                  .fromCurrentRequest()
                  .path("/{id}")
                  .buildAndExpand(matchFind.get().getId())
                  .toUri();
        return ResponseEntity.created(location).body(matchFind.get());
      }

}
