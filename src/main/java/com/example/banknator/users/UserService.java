package com.example.banknator.users;

import com.example.banknator.users.dto.PostNewUserInformation;
import com.example.banknator.users.dto.User;

import java.util.Optional;

public interface UserService {
    Optional<User> createUserInformation(PostNewUserInformation request);
    void updateUserInformation(UpdateUserInformation request);
}
