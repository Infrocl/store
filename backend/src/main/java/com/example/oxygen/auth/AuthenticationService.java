package com.example.oxygen.auth;

import com.example.oxygen.config.JwtService;
import com.example.oxygen.entity.Role;
import com.example.oxygen.entity.User;
import com.example.oxygen.pojo.UserPojo;
import com.example.oxygen.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public void register(RegisterRequest request) {
        userService.create(request);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        String email = request.getEmail();
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, request.getPassword())
        );
        User user = userService.findUserByEmail(email);
        return new AuthenticationResponse(
                jwtService.generateToken(user), userService.findUserIdByEmail(email), userService.findUserRoleByEmail(email));

    }
}
