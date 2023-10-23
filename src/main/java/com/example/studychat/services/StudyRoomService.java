package com.example.studychat.services;

import com.example.studychat.models.StudyRoom;
import com.example.studychat.repositores.StudyRoomRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class StudyRoomService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudyRoomService.class);


@Autowired
private StudyRoomRepository studyRoomRepository;


public StudyRoom createRoom(StudyRoom studyRoom) {
        LOGGER.info("Creating study room with name: {}", studyRoom.getName());
        return studyRoomRepository.save(studyRoom);
        }

    public Optional<StudyRoom> findById(Long roomId) {
        LOGGER.info("Fetching study room by ID: {}", roomId);
        return studyRoomRepository.findById(roomId);
    }

    public boolean existsById(Long roomId) { return studyRoomRepository.existsById(roomId);}
}

