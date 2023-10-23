package com.example.studychat.services;

import com.example.studychat.exceptions.StudyChatException;
import com.example.studychat.models.StudyRoom;
import com.example.studychat.models.User;
import com.example.studychat.repositores.StudyRoomRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class StudyRoomService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudyRoomService.class);


@Autowired
private StudyRoomRepository studyRoomRepository;

@Autowired
private UserService userService;


public StudyRoom createRoom(StudyRoom studyRoom) {
        LOGGER.info("Creating study room with name: {}", studyRoom.getName());
        return studyRoomRepository.save(studyRoom);
        }

    public List<StudyRoom> listAllRooms() {
        LOGGER.info("Listing all study rooms.");
        return studyRoomRepository.findAll();
    }

    public Optional<StudyRoom> findById(Long roomId) {
        LOGGER.info("Fetching study room by ID: {}", roomId);
        return studyRoomRepository.findById(roomId);
    }

    public StudyRoom joinRoom(Long roomId, User user) {
        StudyRoom room = this.findById(roomId).orElseThrow(() -> new StudyChatException("Study room not found."));


        User actualUser = userService.findByUsername(user.getUsername())
                .orElseThrow(() -> new StudyChatException("User not found."));
        if (room == null) {
            throw new StudyChatException("Study room not found.");
        }

        room.getUsers().add(actualUser);
        return studyRoomRepository.save(room);
    }

    public boolean existsById(Long roomId) { return studyRoomRepository.existsById(roomId);}
}

