package com.company;

import java.util.ArrayList;

public class Account {

    /**
     * @param name - the account name
     * @param uniqueID - the account uniqueidentification number
     * @param holder - the user that holds this account
     * @param accounts - the list of transactions this account has
     */
    private String name;
    private String uniqueID;
    private User holder;
    private ArrayList<Transaction> transactions;

    /**
     * @param name - the account name
     * @param holder- the user of this account
     * @param curBank - the bank that holds this account
     */
    public Account(String name, User holder, Bank curBank){
        this.name = name;
        this.holder = holder;
        this.uniqueID = curBank.getNewAccountUUID();
        this.transactions = new ArrayList<Transaction>();
    }

    /**
     * get unique ID from the account
     * @return the account's uniqueID
     */
    public String getUniqueID() {
        return this.uniqueID;
    }

    /**
     * get account summary
     * @return the account's uniqueID
     */
    public String getSummaryLine() {
        //get the account's balance
        double balance = this.getBalance();

        //format the summary line
        if(balance >= 0){
            return String.format("%s : $%.02f: %s", this.uniqueID, balance, this.name);
        } else {
            return String.format("%s : $(%.02f): %s", this.uniqueID, balance, this.name);
        }
    }

    public double getBalance() {
        double balance = 0;
        for(Transaction t: this.transactions){
            balance += t.getAmount();
        }
        return balance;
    }

    //Print the transaction history of the account
    public void printTransactionHistory() {
        System.out.printf("\nTransaction histroy for account %s\n", this.uniqueID);
        for(int t = this.transactions.size()-1; t >= 0; t--){
            System.out.println(this.transactions.get(t).getSummaryLine());
        }
        System.out.println();
    }

    public void addTransaction(double amount, String memo) {
        //create new transaction object and add it to the list
        Transaction newTransaction = new Transaction(amount, memo, this);
        this.transactions.add(newTransaction);
    }
}
