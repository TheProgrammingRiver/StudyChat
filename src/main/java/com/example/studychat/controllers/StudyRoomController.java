package com.example.studychat.controllers;

import com.example.studychat.exceptions.StudyChatException;
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
@RestController
@RequestMapping("/studyrooms")
public class StudyRoomController {

    @Autowired
    private StudyRoomService studyRoomService;
    @Autowired
    private UserService userService;


    /**
     * Creates a study room.
     *
     * @param  studyRoom  the study room to be created
     * @return            the ResponseEntity containing the created study room and the HTTP status code
     */
    @PostMapping("/create")
    public ResponseEntity<StudyRoom> createRoom(@RequestBody StudyRoom studyRoom) {
        if (studyRoom == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        StudyRoom createdRoom = studyRoomService.createRoom(studyRoom);
        return new ResponseEntity<>(createdRoom, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<StudyRoom>> listAllRooms() {
        List<StudyRoom> rooms = studyRoomService.listAllRooms();
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }
    @GetMapping("/{roomId}")
    public ResponseEntity<StudyRoom> getRoomDetails(@PathVariable Long roomId) {
        StudyRoom room = studyRoomService.findById(roomId).orElseThrow(() -> new StudyChatException("Study room not found."));
        return ResponseEntity.ok(room);
    }



    /**
     * Join a room.
     *
     * @param  roomId    the ID of the room to join
     * @param  user      the user object containing the username
     * @return           a map with a success message if the user successfully joined the room,
     *                   or an error message if the user was not found or if there was an exception
     */
    @PostMapping("/{roomId}/join")
    public ResponseEntity<Map<String, String>> joinRoom(@PathVariable Long roomId, @RequestBody User user) {
        try {
            User existingUser = userService.findByUsername(user.getUsername()).orElseThrow(() -> new StudyChatException("User not found."));
            StudyRoom room = studyRoomService.joinRoom(roomId, existingUser);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Joined successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (StudyChatException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/{roomId}/leave")
    public ResponseEntity<String> leaveRoom(@PathVariable Long roomId, @RequestBody User user) {
        try {
            StudyRoom room = studyRoomService.leaveRoom(roomId, user);
            return ResponseEntity.ok("Left successfully");
        } catch (StudyChatException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}