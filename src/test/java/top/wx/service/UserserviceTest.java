package top.wx.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import top.wx.Mapper.UserMapper;
import top.wx.pojo.Driver;
import top.wx.pojo.Passenger;
import top.wx.service.Userservice;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
/**
 * @Author: rain
 * @Date: 2023-11-10-20:52
 * @Description:
 */
class UserserviceTest {

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private Userservice userService;

    @Test
    public void testFindUserIdExist() {
        // Arrange
        String userId = "user123";
        when(userMapper.findUserIdIsExist(userId)).thenReturn(new Passenger()); // Assuming a user is found

        // Act
        boolean result = userService.findUserIdIsExist(userId);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testFindUserIdNotExist() {
        // Arrange
        String userId = "user456";
        when(userMapper.findUserIdIsExist(userId)).thenReturn(null); // Assuming no user is found

        // Act
        boolean result = userService.findUserIdIsExist(userId);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testFindDriverIdExist() {
        // Arrange
        String driverId = "driver123";
        when(userMapper.findDriverIdIsExist(driverId)).thenReturn(new Driver()); // Assuming a driver is found

        // Act
        boolean result = userService.findDriverIdIsExist(driverId);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testFindDriverIdNotExist() {
        // Arrange
        String driverId = "driver456";
        when(userMapper.findDriverIdIsExist(driverId)).thenReturn(null); // Assuming no driver is found

        // Act
        boolean result = userService.findDriverIdIsExist(driverId);

        // Assert
        assertFalse(result);
    }
}