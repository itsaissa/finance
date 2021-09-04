package com.test;

import com.main.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    public User newUser;

    @Test
    public void getUserandName(){
        newUser = new User("John Doe", 1234, 153);
        assertEquals("John Doe", newUser.getName());
    }






}