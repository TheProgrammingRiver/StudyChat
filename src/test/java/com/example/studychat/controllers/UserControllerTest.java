package com.example.studychat.controllers;

import com.example.studychat.models.User;
import com.example.studychat.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void registerTest() {
        User mockUser = new User();
        when(userService.register(any(User.class))).thenReturn(mockUser);

        ResponseEntity<Map<String, String>> response = userController.register(mockUser);

        assertEquals(201, response.getStatusCodeValue());
    }

    @Test
    public void loginTestSuccess() {
        User mockUser = new User();
        mockUser.setUsername("validUsername");
        mockUser.setPassword("validPassword");
        when(userService.authenticate(anyString(), anyString())).thenReturn(true);

        ResponseEntity<Map<String, String>> response = (ResponseEntity<Map<String, String>>) userController.login(mockUser);

        assertEquals(200, response.getStatusCodeValue());
        verify(userService).authenticate(mockUser.getUsername(), mockUser.getPassword());
    }

    @Test
    public void loginTestFailure() {
        User mockUser = new User();
        when(userService.authenticate(anyString(), anyString())).thenReturn(false);

        ResponseEntity<Map<String, String>> response = (ResponseEntity<Map<String, String>>) userController.login(mockUser);

        assertEquals(401, response.getStatusCodeValue());
    }

    @Test
    public void testRegisterWhenRegistrationIsSuccessfulThenReturn201() {
        User mockUser = new User();
        when(userService.register(any(User.class))).thenReturn(mockUser);

        ResponseEntity<Map<String, String>> response = userController.register(mockUser);

        assertEquals(201, response.getStatusCodeValue());
    }

    @Test
    public void testLoginWhenLoginIsSuccessfulThenReturn200() {
        User mockUser = new User();
        mockUser.setUsername("validUsername");
        mockUser.setPassword("validPassword");
        when(userService.authenticate(anyString(), anyString())).thenReturn(true);

        ResponseEntity<Map<String, String>> response = (ResponseEntity<Map<String, String>>) userController.login(mockUser);

        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void testLoginWhenLoginFailsThenReturn401() {
        User mockUser = new User();
        when(userService.authenticate(anyString(), anyString())).thenReturn(false);

        ResponseEntity<Map<String, String>> response = (ResponseEntity<Map<String, String>>) userController.login(mockUser);

        assertEquals(401, response.getStatusCodeValue());
    }
}
