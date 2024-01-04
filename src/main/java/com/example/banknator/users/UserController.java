package com.example.banknator.users;

import com.example.banknator.users.dto.PostNewUserInformation;
import com.example.banknator.users.dto.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/apiv1/users")
public class UserController {
    private final UserService userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/newUser")
    public ResponseEntity<User> postNewUser(@RequestBody PostNewUserInformation request){
        try{
            return ResponseEntity.of(userService.createUserInformation(request));
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Error has occurred while creating user" ,e
            );
        }
    }
}
