package com.eliminatorias.apieliminatorias.security.jwt;

import com.eliminatorias.apieliminatorias.models.entities.UserDetailsImp;

import io.jsonwebtoken.*;

import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
public class JwtUtils {

    @Value("${eliminatorias.app.jwtSecret}")
    private String seceretKey;

    @Value("${eliminatorias.app.jwtExpirationMs}")
    private String timeExpiration;

    public String generateAccestoken(Authentication authentication){
        UserDetailsImp userPrincipal = (UserDetailsImp) authentication.getPrincipal();
        System.out.println("userPrincipal: "+userPrincipal.getUsername());
        try {
            //no genera el token areglar despues
            return Jwts.builder()
                    .setSubject(userPrincipal.getUsername())
                    .setIssuedAt(new Date())
                    .setExpiration(new Date((new Date()).getTime() + Long.parseLong(timeExpiration)))
                    .signWith(SignatureAlgorithm.HS512, seceretKey)
                    .compact();
        }catch (Exception e){
            System.out.println("error: "+e.getMessage());
            return null;
        }

    }
    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(seceretKey)
                .build()
                .parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(seceretKey).build().parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            System.out.println("Invalid JWT signature: {}"+ e.getMessage());
        } catch (MalformedJwtException e) {
            System.out.println("Invalid JWT token: {}"+ e.getMessage());
        } catch (ExpiredJwtException e) {
            System.out.println("JWT token is expired: {}"+ e.getMessage());
        } catch (UnsupportedJwtException e) {
            System.out.println("JWT token is unsupported: {}"+ e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("JWT claims string is empty: {}"+ e.getMessage());
        }

        return false;
    }

}
