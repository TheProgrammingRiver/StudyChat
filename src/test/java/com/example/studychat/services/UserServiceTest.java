package com.example.studychat.services;

import com.example.studychat.models.User;
import com.example.studychat.repositores.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;


public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void findByIdTest() {
        User user = new User("John", "password");
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

        User fetchedUser = userService.findById(1L);

        assertEquals(user.getUsername(), fetchedUser.getUsername());
    }

    @Test
    public void authenticateTest() {
        User user = new User("John", "password");
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));

        boolean isAuthenticated = userService.authenticate("John", "password");

        assertTrue(isAuthenticated);
    }

    @Test
    public void registerTest() {
        User user = new User("John", "password");
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.empty());
        when(userRepository.save(any(User.class))).thenReturn(user);

        User registeredUser = userService.register(user);

        assertEquals(user.getUsername(), registeredUser.getUsername());
    }

    @Test
    public void findByUsernameTest() {
        User user = new User("John", "password");
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));

        Optional<User> fetchedUser = userService.findByUsername("John");

        assertTrue(fetchedUser.isPresent());
        assertEquals(user.getUsername(), fetchedUser.get().getUsername());
    }

    @Test
    public void testFindByIdWhenUserExistsThenReturnUser() {
        User user = new User("John", "password");
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

        User fetchedUser = userService.findById(1L);

        assertEquals(user, fetchedUser);
    }

    @Test
    public void testFindByIdWhenUserDoesNotExistThenReturnNull() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        User fetchedUser = userService.findById(1L);

        assertNull(fetchedUser);
    }
}
