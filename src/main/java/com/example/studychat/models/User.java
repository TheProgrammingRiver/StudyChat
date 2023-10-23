package com.example.studychat.models;

import java.util.HashSet;
import java.util.Set;


public class User {

    private Long userId;
    private String username;
    private String password;


    private Set<StudyRoom> studyRooms = new HashSet<>();


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEmpty() {
        return studyRooms.isEmpty();
    }
}
