package com.example.studychat.controllers;

import com.example.studychat.models.User;
import com.example.studychat.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/users")
public class UserController {


    public class UserController {

        @Autowired
        private UserService userService;



        @PostMapping("/register")
        public ResponseEntity<String> register(@RequestBody User user) {
            User registeredUser = userService.register(user);
            if (registeredUser != null) {
                return new ResponseEntity<>("Registration successful.", HttpStatus.CREATED);
            }
            return new ResponseEntity<>("Registration failed.", HttpStatus.BAD_REQUEST);
        }
    }
}