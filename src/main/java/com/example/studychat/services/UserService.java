package com.example.studychat.services;

import com.example.studychat.models.User;
import com.example.studychat.repositores.UserRepository;
import com.example.studychat.exceptions.StudyChatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

@Service
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    public User findById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }


    public User register(User user) {
        Optional<User> existingUser = userRepository.findByUsername(user.getUsername());
        if(existingUser.isPresent()) {
            LOGGER.error("Attempt to register user with duplicate username: {}", user.getUsername());
            throw new StudyChatException("User with username " + user.getUsername() + " already exists.");
        }
        LOGGER.info("Registering user with username: {}", user.getUsername());
        return userRepository.save(user);
    }


    public Optional<User> findByUsername(String username) {
        LOGGER.info("Fetching user by username: {}", username);
        return Optional.ofNullable(userRepository.findByUsername(username)
                .orElseThrow(() -> {
                    LOGGER.error("User with username {} not found.", username);
                    return new StudyChatException("User with username " + username + " not found.");
                }));
    }

}
