package com.example.banknator.auth;

import com.example.banknator.auth.dto.AuthRequest;
import com.example.banknator.auth.dto.AuthResponse;
import com.example.banknator.users.dto.PostNewUserInformation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/apiv1/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
        return new ResponseEntity<>(authService.login(authRequest), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody PostNewUserInformation postNewUserInformation) {
        return new ResponseEntity<>(authService.register(postNewUserInformation), HttpStatus.CREATED);
    }
}
