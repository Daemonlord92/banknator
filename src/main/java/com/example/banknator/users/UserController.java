package com.example.banknator.users;

import com.example.banknator.shared.MessageResponse;
import com.example.banknator.users.dto.PostNewUserInformation;
import com.example.banknator.users.dto.UpdateUserInformation;
import com.example.banknator.users.dto.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

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
            return new ResponseEntity<>(userService.createUserInformation(request), HttpStatus.CREATED);
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.getMessage()
            );
        }
    }

    @GetMapping("/getAllUser")
    public ResponseEntity<List<User>> getAllUser() {
        try {
            return ResponseEntity.ok(userService.getAllUsers());
        } catch (ResponseStatusException e ) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.getMessage()
            );
        }
    }

    @GetMapping("/")
    public ResponseEntity<User> getUser(@RequestParam long id) {
        try {
            return ResponseEntity.ok(userService.getUserById(id));
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.getMessage()
            );
        }
    }

    @PutMapping("/updateUser")
    public ResponseEntity updateUser(@RequestBody UpdateUserInformation request) {
        try {
            userService.updateUserInformation(request);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.getMessage()
            );
        }
    }

    @PutMapping("/disableUser")
    public ResponseEntity<MessageResponse> disableUser(@RequestParam long id) {
        try {
            return ResponseEntity.ok(userService.disableUser(id));
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.getMessage()
            );
        }
    }

    @PutMapping("/enableUser")
    public ResponseEntity<MessageResponse> enableUser(@RequestParam long id) {
        try {
            return ResponseEntity.ok(userService.enableUser(id));
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.getMessage()
            );
        }
    }
}
