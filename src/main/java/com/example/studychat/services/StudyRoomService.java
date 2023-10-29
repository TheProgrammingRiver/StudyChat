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


    /**
     * Creates a study room with the given study room object.
     *
     * @param studyRoom the study room object to create
     * @return the created study room object
     */
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


    public List<StudyRoom> findRoomsByUserId(Long userId) {
        return studyRoomRepository.findRoomsByUserId(userId);
    }

    public boolean existsById(Long id) {
        return studyRoomRepository.existsById(id);
    }


    /**
     * Joins a user to a study room.
     *
     * @param  roomId    the ID of the study room
     * @param  user      the user to join the study room
     * @return           the updated study room with the user joined
     * @throws StudyChatException if the study room or user is not found
     */
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

    public StudyRoom leaveRoom(Long roomId, User user) {
        StudyRoom room = this.findById(roomId).orElseThrow(() -> new StudyChatException("Study room not found."));
        if (room == null) {
            throw new StudyChatException("Study room not found.");
        }

        User actualUser = userService.findByUsername(user.getUsername())
                .orElseThrow(() -> new StudyChatException("User not found."));
        if (actualUser == null) {
            throw new StudyChatException("User not found.");
        }

        room.getUsers().remove(actualUser);
        return studyRoomRepository.save(room);
    }
}
