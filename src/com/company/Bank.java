package com.company;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class Bank {
    private String name;
    private ArrayList<User> users;
    private ArrayList<Account> accounts;

    public Bank(String name){
        this.name = name;
        this.users = new ArrayList<User>();
        this.accounts = new ArrayList<Account>();

    }
    /**
     * Return the name of the bank
     * @return name of this back
     */
    public String getBankName(){
        return this.name;
    }
    /**
     * Create a new unique ID for a user
     * @return unique
     */
    public String getNewUserUUID() {
        String uniqueID;
        Random rng = new Random();
        int len = 9;
        boolean nonUnique;

        do{
            // Make a new ID
            uniqueID = "";
            for(int i = 0; i < len; i++){
                uniqueID += ((Integer)rng.nextInt(10)).toString();
            }
            //check to make sure ID is unique compared to other users
            nonUnique = false;
            for(User u : this.users){
                if (uniqueID.compareTo(u.getUniqueID()) == 0){
                    nonUnique = true;
                    break;
                }
            }
        } while (nonUnique);
        return uniqueID;
    }
    /**
     * Create a new unique ID for an account
     */
    public String getNewAccountUUID() {
        String uniqueID;
        Random rng = new Random();
        int len = 9;
        boolean nonUnique;

        do{
            // Make a new ID
            uniqueID = "";
            for(int i = 0; i < len; i++){
                uniqueID += ((Integer)rng.nextInt(10)).toString();
            }
            //check to make sure ID is unique compared to other users
            nonUnique = false;
            for(Account a: this.accounts){
                if (uniqueID.compareTo(a.getUniqueID()) == 0){
                    nonUnique = true;
                    break;
                }
            }
        } while (nonUnique);
        return uniqueID;
    }

    /**
     * Add a new account for the user
     * @param newAcc - the new account to add
     */
    public void addAccount(Account newAcc) {
        this.accounts.add(newAcc);
    }

    /**
     * Add a new user to bank
     * @param firstName - the user's firstname
     * @param lastName - the user's lastname
     * @param pin - the user's pin
     * @return the new user
     */
    public User addUser(String firstName, String lastName, String pin){
        //Create new user and add it to our bank list
        User newUser = new User(firstName, lastName, pin, this);
        this.users.add(newUser);

        //Create new account object for User, and add it to the user's list
        //and bank list
        Account newAccount = new Account("Savings", newUser, this);
        newUser.addAccount(newAccount);
        this.addAccount(newAccount);

        return newUser;
    }

    public User userLogin(String userID, String pin){
        //find user
        for(User u : this.users){
            //check userID is correct
            if(u.getUniqueID().compareTo(userID) == 0 && u.validatePin(pin)){
                return u;
            }
        }
        //if we haven't found the user, or the pin is wrong
        return null;
    }
}
