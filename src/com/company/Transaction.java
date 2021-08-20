package com.company;

import java.util.Date;

public class Transaction {
    /**
     * @param amount - the account of the transaction
     * @param timestamp - the time and date of this transaction
     * @param memo - a memo or this transaction
     * @param curAccount - the account the transaction was performed in
     */
    private double amount;
    private Date timestamp;
    private String memo;
    private Account curAccount;


    public Transaction(double amount, Account curAccount){
        this.amount = amount;
        this.timestamp = new Date();
        this.memo = "";
        this.curAccount = curAccount;
    }
    public Transaction(double amount, String memo, Account curAccount){
        this(amount, curAccount);
        this.timestamp = new Date();
        this.memo = memo;
    }

    public double getAmount() {
        return this.amount;
    }

    //Get a string summarizing the transaction
    public String getSummaryLine() {
        if(this.amount >=0){
            return String.format("%s : $%.02f : %s", this.timestamp.toString(),
                    this.amount, this.memo);
        } else {
            return String.format("%s : ($%.02f) : %s", this.timestamp.toString(),
                    -this.amount, this.memo);
        }
    }
}
