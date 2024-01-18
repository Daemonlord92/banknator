package com.example.banknator.auth;

import com.example.banknator.auth.dto.AuthRequest;
import com.example.banknator.auth.dto.AuthResponse;
import com.example.banknator.users.dto.PostNewUserInformation;

public interface AuthService {
    AuthResponse login(AuthRequest authRequest);
    AuthResponse register(PostNewUserInformation postNewUserInformation);
}
