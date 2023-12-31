package com.eliminatorias.apieliminatorias.controllers;

import com.eliminatorias.apieliminatorias.models.dtos.Login;
import com.eliminatorias.apieliminatorias.models.dtos.ResponseJwt;
import com.eliminatorias.apieliminatorias.models.dtos.Signup;
import com.eliminatorias.apieliminatorias.services.Auth;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.message.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/apieliminatorias/v1/auth")
public class AuthController {
    private final Auth auth;

    @PostMapping ("/login")
    public ResponseEntity<ResponseJwt> login(@RequestBody Login login){
        try {
            ResponseJwt responseJwt = auth.login(login);
            return new ResponseEntity<>(responseJwt, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping ("/singup")
    public ResponseEntity<String> signup(@RequestBody Signup signup){
        return new ResponseEntity<>(auth.signup(signup), HttpStatus.OK);
    }
}
