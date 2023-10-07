package com.eliminatorias.apieliminatorias.services;

import com.eliminatorias.apieliminatorias.models.dtos.Login;
import com.eliminatorias.apieliminatorias.models.dtos.ResponseJwt;
import com.eliminatorias.apieliminatorias.models.dtos.Signup;

public interface Auth {
    ResponseJwt login(Login login);
    String signup(Signup signup);
}
