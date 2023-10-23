package com.example.studychat.controllers;

import com.example.studychat.exceptions.StudyChatException;
import com.example.studychat.models.StudyRoom;
import com.example.studychat.models.User;
import com.example.studychat.services.StudyRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/studyrooms")
public class StudyRoomController {

    @Autowired
    private StudyRoomService studyRoomService;

    @PostMapping("/create")
    public ResponseEntity<StudyRoom> createRoom(@RequestBody StudyRoom studyRoom) {
        if (studyRoom == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        StudyRoom createdRoom = studyRoomService.createRoom(studyRoom);
        return new ResponseEntity<>(createdRoom, HttpStatus.CREATED);
    }



    @GetMapping("/{roomId}")
    public ResponseEntity<StudyRoom> getRoomDetails(@PathVariable Long roomId) {
        StudyRoom room = studyRoomService.findById(roomId).orElseThrow(() -> new StudyChatException("Study room not found."));
        return ResponseEntity.ok(room);
    }

}

