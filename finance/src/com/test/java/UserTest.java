package com.test.java;

import com.main.java.Storage;
import com.main.java.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    User newUser;
    Storage curStorage;

    @Test
    public void getUserandName(){
        newUser = new User("John Doe", 1234, 153);
        assertEquals("John Doe", newUser.getName());
    }

    @BeforeEach
    void setUp() {
        newUser = new User("John Doe",123,89);
        curStorage = new Storage();
        curStorage.addUser(newUser);
    }

    @Test
    @DisplayName("Can the user get his/her net balance for the month?")
    void testCalculateNet() {
        assertEquals("-$1.00", newUser.calculateNet(1.0, 2.0));
        assertEquals("$13.89", newUser.calculateNet(114.90, 101.01));
        assertEquals("-$24.53", newUser.calculateNet(10.47, 35.00));
    }

    @Test
    @DisplayName("Can we see the user's financial summary")
    void testFinancialSummary() {
        newUser.setIncome(15.00);
        newUser.setExpenses(154.23);
        assertEquals("15.00", String.format("%.2f",newUser.getIncome()));
        assertEquals("154.23", String.format("%.2f",newUser.getExpenses()));
    }




}