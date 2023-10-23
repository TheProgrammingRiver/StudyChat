package com.example.studychat.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;


public class StudyRoom {


    private Long roomId;
    private String name;
    private String subject;

    @JsonIgnore
    private Set<User> users = new HashSet<>();

    @JsonIgnore
    private List<ChatMessage> messages;

    public StudyRoom() { }

    public StudyRoom(String name, String subject) {
        this.name = name;
        this.subject = subject;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public List<ChatMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<ChatMessage> messages) {
        this.messages = messages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudyRoom studyRoom = (StudyRoom) o;
        return Objects.equals(roomId, studyRoom.roomId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomId);
    }

}
