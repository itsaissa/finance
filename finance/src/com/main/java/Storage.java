package com.main.java;

import java.util.ArrayList;
import java.util.List;

public class Storage {
    /**
     * userList - the list of users in the app
     */
    private final List<User> userList;

    /**
     * Initialize a storage Object
     */
    public Storage(){
        this.userList = new ArrayList<>();
    }

    /**
     * Validate the user's id and pin
     */
    public User validateUser(int id, int pin) {
        for(User u: this.userList){
            if(u.getId() == id && u.getPin() == pin){
                return u;
            }
        }
        System.out.println("Incorrect id and/or pin number. Try Again");
        return null;
    }

    /**
     * Add a user to the storage system
     */
    public void addUser(User newUser) {
        userList.add(newUser);
    }
}
