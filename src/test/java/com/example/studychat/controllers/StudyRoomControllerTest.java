package com.example.studychat.controllers;

import com.example.studychat.exceptions.StudyChatException;
import com.example.studychat.models.StudyRoom;
import com.example.studychat.models.User;
import com.example.studychat.services.StudyRoomService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class StudyRoomControllerTest {

    @InjectMocks
    private StudyRoomController studyRoomController;

    @Mock
    private StudyRoomService studyRoomService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createRoomWhenValidStudyRoomThenReturnCreatedStatusAndStudyRoom() {
        StudyRoom mockRoom = new StudyRoom();
        when(studyRoomService.createRoom(any(StudyRoom.class))).thenReturn(mockRoom);

        ResponseEntity<StudyRoom> response = studyRoomController.createRoom(mockRoom);

        assertEquals(201, response.getStatusCodeValue());
        assertEquals(mockRoom, response.getBody());
    }

    @Test
    public void listAllRoomsWhenRoomsExistThenReturnOkStatusAndList() {
        StudyRoom mockRoom1 = new StudyRoom();
        StudyRoom mockRoom2 = new StudyRoom();
        List<StudyRoom> mockRooms = Arrays.asList(mockRoom1, mockRoom2);
        when(studyRoomService.listAllRooms()).thenReturn(mockRooms);

        ResponseEntity<List<StudyRoom>> response = studyRoomController.listAllRooms();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());
    }

    @Test
    public void testCreateRoomWhenValidStudyRoomThenReturnCreatedStatusAndStudyRoom() {
        StudyRoom mockRoom = new StudyRoom();
        when(studyRoomService.createRoom(any(StudyRoom.class))).thenReturn(mockRoom);

        ResponseEntity<StudyRoom> response = studyRoomController.createRoom(mockRoom);

        assertEquals(201, response.getStatusCodeValue());
        assertEquals(mockRoom, response.getBody());
    }

    @Test
    public void testCreateRoomWhenNullStudyRoomThenReturnBadRequest() {
        ResponseEntity<StudyRoom> response = studyRoomController.createRoom(null);

        assertEquals(400, response.getStatusCodeValue());
    }

    @Test
    public void testGetRoomDetailsWhenRoomExistsThenReturnOkStatusAndRoom() {
        StudyRoom mockRoom = new StudyRoom();
        when(studyRoomService.findById(any(Long.class))).thenReturn(Optional.of(mockRoom));

        ResponseEntity<StudyRoom> response = studyRoomController.getRoomDetails(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockRoom, response.getBody());
    }

    @Test
    public void testGetRoomDetailsWhenRoomDoesNotExistThenThrowStudyChatException() {
        when(studyRoomService.findById(any(Long.class))).thenReturn(Optional.empty());

        try {
            studyRoomController.getRoomDetails(1L);
        } catch (StudyChatException e) {
            assertEquals("Study room not found.", e.getMessage());
        }
    }

    @Test
    public void testJoinRoomWhenRoomExistsAndUserExistsThenReturnOkStatusAndMessage() {
        StudyRoom mockRoom = new StudyRoom();
        User mockUser = new User();
        when(studyRoomService.joinRoom(any(Long.class), any(User.class))).thenReturn(mockRoom);

        ResponseEntity<String> response = studyRoomController.joinRoom(1L, mockUser);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Joined successfully", response.getBody());
    }

    @Test
    public void testLeaveRoomWhenRoomExistsAndUserExistsThenReturnOkStatusAndMessage() {
        StudyRoom mockRoom = new StudyRoom();
        User mockUser = new User();
        when(studyRoomService.leaveRoom(any(Long.class), any(User.class))).thenReturn(mockRoom);

        ResponseEntity<String> response = studyRoomController.leaveRoom(1L, mockUser);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Left successfully", response.getBody());
    }
}
