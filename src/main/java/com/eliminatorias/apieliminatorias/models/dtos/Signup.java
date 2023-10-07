package com.eliminatorias.apieliminatorias.models.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class Signup {
    private String username;

    private String email;

    private Set<String> role;

    private String password;
}
