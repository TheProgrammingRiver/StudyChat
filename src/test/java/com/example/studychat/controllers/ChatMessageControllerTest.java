package com.example.studychat.controllers;

import com.example.studychat.models.ChatMessage;
import com.example.studychat.models.StudyRoom;
import com.example.studychat.services.ChatMessageService;
import com.example.studychat.services.StudyRoomService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ChatMessageControllerTest {

    @InjectMocks
    private ChatMessageController chatMessageController;

    @Mock
    private ChatMessageService chatMessageService;

    @Mock
    private StudyRoomService studyRoomService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void  testGetMessagesForRoomWhenValidRoomThenReturnOk() {
        StudyRoom mockRoom = new StudyRoom();
        ChatMessage mockMessage1 = new ChatMessage();
        ChatMessage mockMessage2 = new ChatMessage();
        List<ChatMessage> mockMessages = Arrays.asList(mockMessage1, mockMessage2);
        when(studyRoomService.findById(anyLong())).thenReturn(java.util.Optional.of(mockRoom));
        when(chatMessageService.getMessagesForRoom(mockRoom)).thenReturn(mockMessages);

        ResponseEntity<List<ChatMessage>> response = chatMessageController.getMessagesForRoom(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());
    }

    @Test
    public void testSendMessageWhenValidRoomAndMessageThenReturnOk() {
        StudyRoom mockRoom = new StudyRoom();
        ChatMessage mockMessage = new ChatMessage();
        when(studyRoomService.findById(anyLong())).thenReturn(java.util.Optional.of(mockRoom));
        when(chatMessageService.sendMessage(any(ChatMessage.class))).thenReturn(mockMessage);

        ResponseEntity<ChatMessage> response = chatMessageController.sendMessage(1L, mockMessage);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockMessage, response.getBody());
    }

    @Test
    public void testSendMessageWhenInvalidRoomThenReturnNotFound() {
        ChatMessage mockMessage = new ChatMessage();
        when(studyRoomService.findById(anyLong())).thenReturn(java.util.Optional.empty());

        ResponseEntity<ChatMessage> response = chatMessageController.sendMessage(1L, mockMessage);

        assertEquals(404, response.getStatusCodeValue());
    }
}
