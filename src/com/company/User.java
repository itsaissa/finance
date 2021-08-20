package com.company;

import java.lang.reflect.Array;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class User {
    /**
     * @param firstName - the user's first name
     * @param lastName - the user's last name
     * @param uniqueID - the user's unique identification number
     * @param pinHash - the SHA hash of the user's pin number
     * @param accounts - the list of accounts this user has
     */
    private String firstName;
    private String lastName;
    private String uniqueID;
    private byte pinHash[];
    private ArrayList<Account> accounts;

    /**
     * @param firstName - the user's first name
     * @param lastName - the user's last name
     * @param pin - the user's account pin number
     * @param curBank - the bank the user is a part of
     */

    public User(String firstName, String lastName, String pin, Bank curBank){
        this.firstName = firstName;
        this.lastName = lastName;
        try {
            MessageDigest sha = MessageDigest.getInstance("SHA");
            this.pinHash = sha.digest(pin.getBytes());
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Error: Caught NoSuchAlgortithmException");
            e.printStackTrace();
            System.exit(1);
        }

        this.uniqueID = curBank.getNewUserUUID();
        this.accounts = new ArrayList<Account>();
        System.out.printf("Welcome to %s, %s %s. Your new ID is %s!\n\n", curBank.getBankName(), firstName, lastName,
                uniqueID);
    }

    /**
     * Add a new account for the user
     * @param newAcc - the new account to add
     */
    public void addAccount(Account newAcc) {
        this.accounts.add(newAcc);
    }

    /**
     * get unique ID from user
     * @return the user's uniqueID
     */
    public String getUniqueID(){
        return this.uniqueID;

    }

    /**
     * Check whether the entered pin is correct
     * @enteredPin the user's entered pin number
     * @return a boolean that determines the pin's validity
     */
    public boolean validatePin(String enteredPin) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA");
            return MessageDigest.isEqual(md.digest(enteredPin.getBytes()), this.pinHash);
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Error: Caught NoSuchAlgortithmException");
            e.printStackTrace();
            System.exit(1);
        }
        return false;
    }

    public String getFirstName() {
        return this.firstName;
    }

    //Print summaries of accounts
    public void printAccountsSummary() {
        System.out.printf("\n\n%s's accounts summary\n", this.getFirstName());
        for(int a=0; a< this.accounts.size(); a++){
            System.out.printf(" %d) %s\n", a+1,
                    this.accounts.get(a).getSummaryLine());
        }
        System.out.println();
    }

    //get the total number of accounts for one user
    public int getNumOfAccounts() {
        return this.accounts.size();
    }

    //Print transaction history for a single acocubnt
    public void printAccTransactionHistory(int accNumberIndex) {
        this.accounts.get(accNumberIndex).printTransactionHistory();
    }

    public double getAccBalance(int accIndex) {
        return this.accounts.get(accIndex).getBalance();
    }

    //Get the Unique ID of an account
    public String getAccUniqueID(int accIndex){
        return this.accounts.get(accIndex).getUniqueID();
    }

    public void addAccTransaction(int accIndex, double amount, String memo) {
        this.accounts.get(accIndex).addTransaction(amount, memo);

    }
}
