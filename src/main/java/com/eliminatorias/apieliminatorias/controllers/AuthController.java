package com.eliminatorias.apieliminatorias.controllers;

import com.eliminatorias.apieliminatorias.models.dtos.Login;
import com.eliminatorias.apieliminatorias.models.dtos.ResponseJwt;
import com.eliminatorias.apieliminatorias.models.dtos.Signup;
import com.eliminatorias.apieliminatorias.services.Auth;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/apieliminatorias/v1/auth")
public class AuthController {
    private final Auth auth;

    @PostMapping ("/login")
    public ResponseEntity<ResponseJwt> login(@RequestBody Login login){
        System.out.println("login: "+login.getEmail());
        return new ResponseEntity<>(auth.login(login), HttpStatus.OK);
    }
    @PostMapping ("/signup")
    public ResponseEntity<String> signup(@RequestBody Signup signup){
        return new ResponseEntity<>(auth.signup(signup), HttpStatus.OK);
    }
}
