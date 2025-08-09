package olis.codingexercise.controller;

import olis.codingexercise.dto.UserResponse;
import olis.codingexercise.dto.UserUpdateRequest;
import olis.codingexercise.model.User;
import olis.codingexercise.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private List<User> userList = new ArrayList<>();

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(produces = "application/json")
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    public User addUser(@RequestBody User newUser) {
        return userService.createUser(newUser);
    }

    @GetMapping(path = "/{userId}", produces = "application/json")
    public User getUser(@PathVariable Long userId) {
        return userService.findById(userId);
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<UserResponse> editUser(
            @PathVariable Long userId,
            @RequestBody UserUpdateRequest updateRequest
    ) {
        UserResponse response = userService.updateUser(userId, updateRequest);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = "/{userId}", produces = "application/json")
    public void deleteUser(@PathVariable Long userId) {
        userService.delete(userId);
    }
}