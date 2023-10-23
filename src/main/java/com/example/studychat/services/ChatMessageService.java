package com.example.studychat.services;

import com.example.studychat.exceptions.StudyChatException;
import com.example.studychat.models.ChatMessage;
import com.example.studychat.models.StudyRoom;
import com.example.studychat.repositores.ChatMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatMessageService {

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @Autowired
    private StudyRoomService studyRoomService;

    public ChatMessage sendMessage(ChatMessage message) throws StudyChatException {
        if (message == null) {
            throw new StudyChatException("Message cannot be null.");
        }

        StudyRoom room = message.getStudyRoom();
        if (room == null || !studyRoomService.existsById(room.getRoomId())) {
            throw new StudyChatException("Study room does not exist.");
        }

        return chatMessageRepository.save(message);
    }

    public List<ChatMessage> getMessagesForRoom(StudyRoom studyRoom) {
        if (studyRoom == null) {
            throw new StudyChatException("Study room cannot be null.");
        }
        return chatMessageRepository.findByStudyRoom(studyRoom);
    }
}
