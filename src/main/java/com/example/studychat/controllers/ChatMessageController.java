package com.example.studychat.controllers;

import com.example.studychat.models.ChatMessage;
import com.example.studychat.models.StudyRoom;
import com.example.studychat.services.ChatMessageService;
import com.example.studychat.services.StudyRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/studyroom/{roomId}/messages")
public class ChatMessageController {

    @Autowired
    private ChatMessageService chatMessageService;

    @Autowired
    private StudyRoomService studyRoomService;

    @PostMapping("/send")
    public ResponseEntity<ChatMessage> sendMessage(@PathVariable Long roomId, @RequestBody ChatMessage message) {
        StudyRoom room = studyRoomService.findById(roomId).orElse(null);
        if (room == null) {
            return ResponseEntity.notFound().build();
        }
        message.setStudyRoom(room); // Setting the room in the message
        ChatMessage sentMessage = chatMessageService.sendMessage(message);
        return ResponseEntity.ok(sentMessage);
    }

    @GetMapping
    public ResponseEntity<List<ChatMessage>> getMessagesForRoom(@PathVariable Long roomId) {
        StudyRoom room = studyRoomService.findById(roomId).orElse(null);
        if (room == null) {
            return ResponseEntity.notFound().build();
        }
        List<ChatMessage> messages = chatMessageService.getMessagesForRoom(room);
        return ResponseEntity.ok(messages);
    }
}
