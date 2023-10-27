//package com.example.studychat.controllers;
//
//import com.example.studychat.models.ChatMessage;
//import com.example.studychat.models.StudyRoom;
//import com.example.studychat.services.ChatMessageService;
//import com.example.studychat.services.StudyRoomService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/studyroom/{roomId}/messages")
//public class ChatMessageController {
//
//    @Autowired
//    private ChatMessageService chatMessageService;
//
//    @Autowired
//    private StudyRoomService studyRoomService;
//
//    @PostMapping("/send")
//    public ResponseEntity<ChatMessage> sendMessage(@PathVariable Long roomId, @RequestBody ChatMessage message) {
//        StudyRoom room = studyRoomService.findById(roomId).orElse(null);
//        if (room == null) {
//            return ResponseEntity.notFound().build();
//        }
//        message.setStudyRoom(room);
//        ChatMessage sentMessage = chatMessageService.sendMessage(message);
//        return ResponseEntity.ok(sentMessage);
//    }
//
//    @GetMapping
//    public ResponseEntity<List<ChatMessage>> getMessagesForRoom(@PathVariable Long roomId) {
//        StudyRoom room = studyRoomService.findById(roomId).orElse(null);
//        if (room == null) {
//            return ResponseEntity.notFound().build();
//        }
//        List<ChatMessage> messages = chatMessageService.getMessagesForRoom(room);
//        return ResponseEntity.ok(messages);
//    }
//}


package com.example.studychat.controllers;

import com.example.studychat.models.ChatMessage;
import com.example.studychat.models.StudyRoom;
import com.example.studychat.services.ChatMessageService;
import com.example.studychat.services.StudyRoomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/studyroom/{roomId}/messages")
public class ChatMessageController {

    private static final Logger logger = LoggerFactory.getLogger(ChatMessageController.class);

    @Autowired
    private ChatMessageService chatMessageService;

    @Autowired
    private StudyRoomService studyRoomService;

    @PostMapping("/send")
    public ResponseEntity<ChatMessage> sendMessage(@PathVariable Long roomId, @RequestBody ChatMessage message) {
        logger.info("Attempting to send a message to room with ID: {}", roomId);

        StudyRoom room = studyRoomService.findById(roomId).orElse(null);
        if (room == null) {
            logger.error("Study room with ID {} not found", roomId);
            return ResponseEntity.notFound().build();
        }

        message.setStudyRoom(room);
        ChatMessage sentMessage = chatMessageService.sendMessage(message);
        logger.info("Message sent successfully");
        return ResponseEntity.ok(sentMessage);
    }

    @GetMapping
    public ResponseEntity<List<ChatMessage>> getMessagesForRoom(@PathVariable Long roomId) {
        logger.info("Fetching messages for room with ID: {}", roomId);

        StudyRoom room = studyRoomService.findById(roomId).orElse(null);
        if (room == null) {
            logger.error("Study room with ID {} not found", roomId);
            return ResponseEntity.notFound().build();
        }
        List<ChatMessage> messages = chatMessageService.getMessagesForRoom(room);
        logger.info("Fetched {} messages", messages.size());
        return ResponseEntity.ok(messages);
    }
}
