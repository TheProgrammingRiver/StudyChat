package com.example.studychat.repositores;

import com.example.studychat.models.ChatMessage;

public interface ChatMessageRepository {
    ChatMessage save(ChatMessage message);
}
