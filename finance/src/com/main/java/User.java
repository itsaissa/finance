package com.main.java;

import java.util.LinkedList;
import java.util.Deque;

public class User {

    /**
     * string name- the user's name
     * int id - the user's id number
     * int pin - the user's pin number
     * double income - the user's total income
     * double expenses - the user's total expenses
     */
    private final String name;
    private final int id;
    private final int pin;
    private double income;
    private double expenses;
    private final Deque<Double> netMonthlyIncome;
    private final String[] months = new String[] {"January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"};
    private int monthOffset;


    /**
     * Initialize User object
     * @param name - the user's name
     * @param id - the user's id number
     * @param pin - the user's pin number
     */
    public User(String name, int id, int pin){
        this.name = name;
        this.id = id;
        this.pin = pin;
        this.income = 0.00;
        this.expenses = 0.00;
        this.netMonthlyIncome = new LinkedList<>();
    }

    /**
     * get the user's
     * @return the user's name
     */
    public String getName() {
        return name;
    }

    /**
     * get the user's id number
     * @return the user's id number
     */
    public int getId() {
        return id;
    }

    /**
     * get the user's pin number
     * @return the user's pin number
     */
    public int getPin() {
        return pin;
    }

    /**
     * get the user's total income
     * @return the user's total income
     */
    public double getIncome() {
        return income;
    }

    /**
     * get the user's total expenses
     * @return the user's total expenses
     */
    public double getExpenses() {
        return expenses;
    }

    /**
     * set the user's total income for the month
     */
    public void setIncome(double income) {
        this.income = income;
    }
    /**
     * set the user's total expenses for the month
     */
    public void setExpenses(double expenses) {
        this.expenses = expenses;
    }

    /**
     * Print's out user's name, id, and pin
     * @return the user's uniqueID
     */
    @Override
    public String toString() {
        return "Hello " + this.getName() + "!\n" +
                "Your id is " + this.getId() + " " +
                "and your pin is " + this.getPin() + ".\n";
    }

    /**
     * Print's the user's total income and expenses
     */
    public void financeSummary() {
        System.out.printf("Your previous monthly Income: $%.2f | Your previous monthly expenses: $%.2f\n\n",this.getIncome(), this.getExpenses());
    }

    /**
     * Calculates the net income from your total income and expenses
     * @return the user's total expenses
     */
    public String calculateNet(Double curIncome, Double curExpenses) {
        setIncome(curIncome);
        setExpenses(curExpenses);
        double netNumber = this.getIncome() - this.getExpenses();

        if(netMonthlyIncome.size() == months.length){
            netMonthlyIncome.removeLast();
            if(monthOffset == months.length){
                monthOffset = 0;
            }
            monthOffset++;
        }
        netMonthlyIncome.offerFirst(netNumber);


        if(netNumber >= 0){
            return String.format("$%.2f", netNumber);
        }else{
            return String.format("-$%.2f", netNumber*-1);
        }
    }
    /**
     * Prints the net income of the user for the past twelve months from
     */
    public void peekLastTwelveMonths() {
        if(netMonthlyIncome.isEmpty()){
            System.out.println("\nThere is no data gathered on your finances. Simulate a month and enter your financial details there.\n");
        }
        else {
            int len = netMonthlyIncome.size();
            int counter = len - 1 + monthOffset % months.length;
            System.out.printf("\n%15sThe Last 12 Months%15s" + "\n", " ", " ");

            for(Double d : netMonthlyIncome){
                if(d >= 0 && counter < months.length){
                    System.out.printf(months[counter % months.length] + ": $%.2f\n", d);
                }
                else if(d < 0 && counter < months.length) {
                    System.out.printf(months[counter % months.length] + ": $-%.2f\n", d*-1);
                }
                else if(d >= 0 && counter == 0){
                    counter = months.length - 1;
                    System.out.printf(months[counter % months.length] + ": $%.2f\n", d);
                }
                else if(d < 0 && counter == 0){
                    counter = months.length - 1;
                    System.out.printf(months[counter % months.length] + ": -$%.2f\n",d * -1);
                }
                else{
                    if(d >= 0){
                        System.out.printf(months[counter % months.length] + ": $%.2f\n", d);
                    }else{
                        System.out.printf(months[counter % months.length] + ": -$%.2f\n",d * -1);
                    }
                }
                counter--;
            }
            System.out.println("\n");
        }
    }

    public boolean hasNoData() {
        return netMonthlyIncome.isEmpty();
    }
}
