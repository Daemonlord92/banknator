package com.example.banknator.auth;

import com.example.banknator.auth.dto.AuthRequest;
import com.example.banknator.auth.dto.AuthResponse;
import com.example.banknator.config.JwtService;
import com.example.banknator.entity.UserCredential;
import com.example.banknator.users.UserService;
import com.example.banknator.users.dto.PostNewUserInformation;
import com.example.banknator.users.dto.User;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService{
    protected final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthServiceImpl(UserService userService, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }


    @Override
    public AuthResponse login(AuthRequest authRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authRequest.email(),
                authRequest.password()
        ));
        UserCredential user = userService.getUserCredentialByEmail(authRequest.email()).orElseThrow();
        String jwt = jwtService.generateToken(user);
        return new AuthResponse(jwt);
    }

    @Override
    public AuthResponse register(PostNewUserInformation postNewUserInformation) {
        User user = userService.createUserInformation(postNewUserInformation);
        String jwt = jwtService.generateToken(user.getEmail());
        return new AuthResponse(jwt);
    }
}
