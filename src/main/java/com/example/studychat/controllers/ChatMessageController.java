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


    /**
     * Sends a chat message to a specific room.
     *
     * @param  roomId   the ID of the room to send the message to
     * @param  message  the chat message to be sent
     * @return          the response entity containing the sent message
     */
    @PostMapping("/send")
    public ResponseEntity<ChatMessage> sendMessage(@PathVariable Long roomId, @RequestBody ChatMessage message) {
        StudyRoom room = studyRoomService.findById(roomId).orElse(null);
        if (room == null) {
            return ResponseEntity.notFound().build();
        }
        message.setStudyRoom(room);
        ChatMessage sentMessage = chatMessageService.sendMessage(message);
        return ResponseEntity.ok(sentMessage);
    }


    /**
     * Retrieves the list of chat messages for a given room.
     *
     * @param  roomId  the ID of the room
     * @return         the ResponseEntity containing the list of chat messages
     */
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
