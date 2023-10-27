package com.example.studychat.models;


import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "chat_messages")
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;
    private String text;
    private Timestamp timestamp;
    private Long id;
    private String content;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User sender;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private StudyRoom studyRoom;

    public ChatMessage() { }

    public ChatMessage(String text, Timestamp timestamp, User sender, StudyRoom studyRoom) {
        this.text = text;
        this.timestamp = timestamp;
        this.sender = sender;
        this.studyRoom = studyRoom;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public String getText() {
        return text;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public StudyRoom getStudyRoom() {
        return studyRoom;
    }

    public void setStudyRoom(StudyRoom studyRoom) {
        this.studyRoom = studyRoom;
    }

    @Override
    public String toString() {
        return "ChatMessage{" +
                "messageId=" + messageId +
                ", text='" + text + '\'' +
                ", timestamp=" + timestamp +
                ", sender=" + sender +
                ", studyRoom=" + studyRoom +
                '}';
    }
}
