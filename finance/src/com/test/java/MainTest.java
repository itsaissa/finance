package com.test.java;

import com.main.java.Storage;
import com.main.java.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    User newUser;
    Storage curStorage;

    @BeforeEach
    void setUp() {
        newUser = new User("John Doe",123,89);
        curStorage = new Storage();
        curStorage.addUser(newUser);
    }

    @Test
    @DisplayName("Can the user login?")
    void testLogin() {
        assertEquals("John Doe", curStorage.validateUser(123,89).getName());
    }

}