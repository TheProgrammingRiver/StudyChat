package com.example.studychat.repositores;

import com.example.studychat.models.ChatMessage;
import com.example.studychat.models.StudyRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    ChatMessage save(ChatMessage message);

    List<ChatMessage> findByStudyRoom(StudyRoom studyRoom);
}
