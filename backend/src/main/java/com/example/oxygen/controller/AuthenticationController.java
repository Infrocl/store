package com.example.oxygen.controller;

import com.example.oxygen.auth.AuthenticationRequest;
import com.example.oxygen.auth.AuthenticationResponse;
import com.example.oxygen.auth.AuthenticationService;
import com.example.oxygen.auth.RegisterRequest;
import com.example.oxygen.pojo.UserPojo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody RegisterRequest request) {
        service.register(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        log.info("Handling login request");
        return ResponseEntity.ok(service.authenticate(request));
    }
}
