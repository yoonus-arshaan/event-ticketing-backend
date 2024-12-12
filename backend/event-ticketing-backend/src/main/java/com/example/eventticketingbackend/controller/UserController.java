package com.example.eventticketingbackend.controller;

import com.example.eventticketingbackend.model.User;
import com.example.eventticketingbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/api/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public ResponseEntity<User> addNewUser(@RequestBody User user) {
        User newUser = userService.addUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Integer id) {
        User user = userService.findUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
