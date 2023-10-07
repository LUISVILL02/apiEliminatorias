package com.eliminatorias.apieliminatorias.services.impl;

import com.eliminatorias.apieliminatorias.models.dtos.Login;
import com.eliminatorias.apieliminatorias.models.dtos.ResponseJwt;
import com.eliminatorias.apieliminatorias.models.dtos.Signup;
import com.eliminatorias.apieliminatorias.models.entities.Role;
import com.eliminatorias.apieliminatorias.models.entities.User;
import com.eliminatorias.apieliminatorias.models.entities.UserDetailsImp;
import com.eliminatorias.apieliminatorias.models.enums.ERole;
import com.eliminatorias.apieliminatorias.repositories.RoleRepository;
import com.eliminatorias.apieliminatorias.repositories.UserRepository;
import com.eliminatorias.apieliminatorias.security.jwt.JwtUtils;
import com.eliminatorias.apieliminatorias.services.Auth;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class AuthImp implements Auth {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;
    private final JwtUtils jwtUtils;
    @Override
    public ResponseJwt login(Login login) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        System.out.println("autenticado");
        String jwt = jwtUtils.generateAccestoken(authentication);
        System.out.println("jwt "+ jwt);

        UserDetailsImp userDetails = (UserDetailsImp) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return new ResponseJwt(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles);
    }

    @Override
    public String signup(Signup signup) {
        if (userRepository.existsByUserName(signup.getUsername())) {
            return "Error: Username is already taken!";
        }

        if (userRepository.existsByEmail(signup.getEmail())) {
            return "Error: Email is already in use!";
        }

        // Create new user's account
        User user = new User(signup.getUsername(),
                signup.getEmail(),
                encoder.encode(signup.getPassword()));

        Set<String> strRoles = signup.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;

                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        return "User registered successfully!";
    }
}
