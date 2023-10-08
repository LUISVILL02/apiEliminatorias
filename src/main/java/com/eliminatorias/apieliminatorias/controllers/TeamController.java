package com.eliminatorias.apieliminatorias.controllers;

import com.eliminatorias.apieliminatorias.models.dtos.TeamDto;
import com.eliminatorias.apieliminatorias.models.entities.Team;
import com.eliminatorias.apieliminatorias.services.TeamService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
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
@RequestMapping("apiEliminatorias/v1/Teams")
public class TeamController {
    private final TeamService teamService;

    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> finAll(@RequestParam(required = false) String name){
        if (name != null){
            Optional<TeamDto> team = teamService.getTeam(name);
            if (team.isPresent()){
                return new ResponseEntity<>(team.get(), HttpStatus.OK);
            }else
                return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(teamService.getTeams(), HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TeamDto> create(@RequestBody @Valid Team team){
        TeamDto teamCreate = teamService.create(team);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(teamCreate.getIdTeam())
                .toUri();
        return ResponseEntity.created(location).body(teamCreate);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TeamDto> update(@PathVariable @Min(1) Long id,
                                       @RequestBody @Valid Team team){
        Optional<TeamDto> team1 = teamService.UpdateTeamById(id, team);
        if (team1.isPresent()){
            return ResponseEntity.ok().body(team1.get());
        }
        TeamDto team2 = teamService.create(team);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(team2.getIdTeam())
                .toUri();
        return ResponseEntity.created(location).body(team2);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Team> delete(@PathVariable @Min(1) Long id){
        teamService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
