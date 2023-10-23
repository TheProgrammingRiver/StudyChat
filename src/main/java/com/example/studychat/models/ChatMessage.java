package com.example.studychat.models;

import java.sql.Timestamp;

public class ChatMessage {

    public ChatMessage(String text, Timestamp timestamp, User sender, StudyRoom studyRoom) {
        this.text = text;
        this.timestamp = timestamp;
        this.sender = sender;
        this.studyRoom = studyRoom;
    }

    public String getText() {
        return text;
    }
    

}
