package com.example.studychat.services;

import com.example.studychat.models.StudyRoom;
import com.example.studychat.models.User;
import com.example.studychat.repositores.StudyRoomRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;


public class StudyRoomServiceTest {

    @InjectMocks
    private StudyRoomService studyRoomService;

    @Mock
    private StudyRoomRepository studyRoomRepository;

    @Mock
    private UserService userService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateRoomWhenValidStudyRoomThenReturnCreatedStatusAndStudyRoom() {
        StudyRoom room = new StudyRoom("Math Room", "Math");
        when(studyRoomRepository.save(any(StudyRoom.class))).thenReturn(room);

        StudyRoom savedRoom = studyRoomService.createRoom(room);

        assertEquals(room.getName(), savedRoom.getName());
    }

    @Test
    public void testCreateRoomWhenRoomIsCreatedThenReturnRoom() {
        StudyRoom room = new StudyRoom("Math Room", "Math");
        when(studyRoomRepository.save(any(StudyRoom.class))).thenReturn(room);

        StudyRoom savedRoom = studyRoomService.createRoom(room);

        assertEquals(room.getName(), savedRoom.getName());
    }

    @Test
    public void testListAllRoomsWhenRoomsExistThenReturnOkStatusAndList() {
        StudyRoom room = new StudyRoom("Math Room", "Math");
        List<StudyRoom> rooms = Collections.singletonList(room);

        when(studyRoomRepository.findAll()).thenReturn(rooms);

        List<StudyRoom> fetchedRooms = studyRoomService.listAllRooms();

        assertEquals(1, fetchedRooms.size());
        assertEquals(room.getName(), fetchedRooms.get(0).getName());
    }

    @Test
    public void  testJoinRoomWhenValidRoomAndUserThenReturnJoinedRoom() {
        StudyRoom room = new StudyRoom("Math Room", "Math");
        User user = new User("John", "password");

        when(studyRoomService.findById(anyLong())).thenReturn(Optional.of(room));
        when(userService.findByUsername(anyString())).thenReturn(Optional.of(user));
        when(studyRoomRepository.save(any(StudyRoom.class))).thenReturn(room);

        StudyRoom joinedRoom = studyRoomService.joinRoom(1L, user);

        assertEquals(room.getName(), joinedRoom.getName());
        assertTrue(joinedRoom.getUsers().contains(user));
    }

    @Test
    public void testLeaveRoomWhenValidRoomAndUserThenReturnLeftRoom() {
        StudyRoom room = new StudyRoom("Math Room", "Math");
        User user = new User("John", "password");
        room.getUsers().add(user);

        when(studyRoomService.findById(anyLong())).thenReturn(Optional.of(room));
        when(userService.findByUsername(anyString())).thenReturn(Optional.of(user));
        when(studyRoomRepository.save(any(StudyRoom.class))).thenReturn(room);

        StudyRoom leftRoom = studyRoomService.leaveRoom(1L, user);

        assertEquals(room.getName(), leftRoom.getName());
        assertFalse(leftRoom.getUsers().contains(user));
    }
}
