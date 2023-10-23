package com.example.studychat.services;

import com.example.studychat.exceptions.StudyChatException;
import com.example.studychat.models.ChatMessage;
import com.example.studychat.models.StudyRoom;
import com.example.studychat.repositores.ChatMessageRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ChatMessageServiceTest {

    @Mock
    private ChatMessageRepository chatMessageRepository;

    @Mock
    private StudyRoomService studyRoomService;

    @InjectMocks
    private ChatMessageService chatMessageService;

    @Test
    public void testSendMessageWhenValidMessageThenReturnMessage() {

        StudyRoom studyRoom = new StudyRoom("Test Room", "Test Subject");
        studyRoom.setRoomId(1L);
        ChatMessage message = new ChatMessage("Test Message", null, null, studyRoom);

        when(studyRoomService.existsById(studyRoom.getRoomId())).thenReturn(true);
        when(chatMessageRepository.save(message)).thenReturn(message);


        ChatMessage result = chatMessageService.sendMessage(message);


        assertThat(result).isEqualTo(message);
    }

    @Test
    public void testSendMessageWhenNullMessageThenThrowException() {

        ChatMessage message = null;


        assertThatThrownBy(() -> chatMessageService.sendMessage(message))
                .isInstanceOf(StudyChatException.class)
                .hasMessage("Message cannot be null.");
    }

    @Test
    public void testSendMessageWhenNonExistentStudyRoomThenThrowException() {

        StudyRoom studyRoom = new StudyRoom("Test Room", "Test Subject");
        studyRoom.setRoomId(1L);
        ChatMessage message = new ChatMessage("Test Message", null, null, studyRoom);

        when(studyRoomService.existsById(studyRoom.getRoomId())).thenReturn(false);

        assertThatThrownBy(() -> chatMessageService.sendMessage(message))
                .isInstanceOf(StudyChatException.class)
                .hasMessage("Study room does not exist.");
    }
}