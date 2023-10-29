package com.example.studychat.controllers;

import com.example.studychat.models.StudyRoom;
import com.example.studychat.models.User;
import com.example.studychat.services.StudyRoomService;
import com.example.studychat.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private StudyRoomService studyRoomService;

    /**
     * Registers a new user.
     *
     * @param  user  the user object to register
     * @return       a ResponseEntity containing a map with the registered user data if successful,
     *               or a ResponseEntity with HTTP status BAD_REQUEST if registration fails.
     */
    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody User user) {
        User registeredUser = userService.register(user);
        if (registeredUser != null) {
            Map<String, String> response = new HashMap<>();
            response.put("data", String.valueOf(registeredUser));
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles the POST request to '/login' endpoint.
     *
     * @param  user   the user object containing the username and password
     * @return        the response entity with the authenticated user data
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        boolean isAuthenticated = userService.authenticate(user.getUsername(), user.getPassword());
        if (isAuthenticated) {
            User authenticatedUser = userService.findByUsername(user.getUsername()).orElse(null);
            if (authenticatedUser != null) {
                Map<String, Object> response = new HashMap<>();
                response.put("data", authenticatedUser);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }


    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserDetails(@PathVariable Long userId) {
        User user = userService.findById(userId);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/username/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        Optional<User> user = userService.findByUsername(username);
        return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    /**
     * Retrieves the list of study rooms for a specific user.
     *
     * @param  userId	the ID of the user
     * @return         	a ResponseEntity object containing a list of StudyRoom objects if rooms are found,
     *                  or a ResponseEntity object with HttpStatus.NOT_FOUND if no rooms are found
     */
    @GetMapping("/{userId}/rooms")
    public ResponseEntity<List<StudyRoom>> listRoomsForUser(@PathVariable Long userId) {
        List<StudyRoom> rooms = studyRoomService.findRoomsByUserId(userId);
        if (!rooms.isEmpty()) {
            return new ResponseEntity<>(rooms, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}