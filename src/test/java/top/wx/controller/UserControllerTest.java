package top.wx.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.junit.jupiter.api.Assertions.*;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import top.wx.pojo.Passenger;
import top.wx.service.Userservice;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private UserController userController;

    @Mock
    private Userservice userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    void testRegister() throws Exception {
        Passenger mockPassenger = new Passenger();
        mockPassenger.setUserId("testUser");
        mockPassenger.setPassword("testPassword");

        when(userService.findUserIdIsExist("testUser")).thenReturn(false);

        mockMvc.perform(MockMvcRequestBuilders.post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"userId\":\"testUser\",\"password\":\"testPassword\"}"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(200));

        verify(userService, times(1)).saveUser(any(Passenger.class));
    }

    // Similar test methods can be added for other controller methods

    // Example of testing login method
    @Test
    void testLogin() throws Exception {
        Passenger mockPassenger = new Passenger();
        mockPassenger.setUserId("testUser");
        mockPassenger.setPassword("testPassword");

        when(userService.queryUserForLogin("testUser", "testPassword")).thenReturn(mockPassenger);

        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"userId\":\"testUser\",\"password\":\"testPassword\"}"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.userId").value("testUser"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.password").doesNotExist());

        verify(userService, times(1)).queryUserForLogin("testUser", "testPassword");
    }
}
