package com.company;

import java.util.Scanner;

public class ATM {

    public static void main(String[] args) {
	    //init Scanner and Bank
        Scanner sc = new Scanner(System.in);
        Bank curBank = new Bank("Lacag Bank");

        //Add a new user and checking account
        User newUser = curBank.addUser("Nasar", "Issa", "1234");
        Account newAccount = new Account("Checking", newUser,curBank);
        curBank.addAccount(newAccount);
        newUser.addAccount(newAccount);

        User curUser;
        //Login Prompt
        while(true){
            //Stay in login prompt
            curUser = ATM.mainMenuPrompt(curBank, sc);
            //Stay in main menu until user quits
            ATM.printUserMenu(curUser, sc);
        }
    }

    /**
     * Print the Atm's login
     * @param curBank - the bank used by the user
     * @param sc - the Scanner object used to collect user input
     * @return the authenticated user
     */
    private static User mainMenuPrompt(Bank curBank, Scanner sc) {
        String userID;
        String pin;
        User authUser;

        //prompt user for userID and pin until they are both validated
        do{
            System.out.printf("Welcome to %s!\n\n", curBank.getBankName());
            System.out.print("Enter user ID: ");
            userID = sc.nextLine();
            System.out.print("Enter user pin number: ");
            pin = sc.nextLine();

            //try to get the user authenticated
            authUser = curBank.userLogin(userID, pin);
            if(authUser == null){
                System.out.println("Incorrect user ID or pin number. " +
                        "Please try again.");
            }
        }while(authUser == null); //continue looping until correct login

        return authUser;
    }
    /**
     * Print the Atm's main menu for user
     * @param curUser - the bank used by the user
     * @param sc - the Scanner object used to collect user input
     * @return the authenticated user
     */
    private static void printUserMenu(User curUser, Scanner sc) {
        //print summary of the user's accounts
        curUser.printAccountsSummary();

        //initialize choice
        int choice;

        //Show user menu
        do{
            System.out.printf("Welcome %s, what would you like to do today?\n", curUser.getFirstName());
            System.out.println(" 1) Get cash");
            System.out.println(" 2) Quick Cash ");
            System.out.println(" 3) Deposit");
            System.out.println(" 4) Show Transactions");
            System.out.println(" 5) Transfer");
            System.out.println(" 6) Quit");
            System.out.println("");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            if(choice < 1 || choice > 6){
                System.out.println("Invalid choice. Please choose 1-6");
            }
        }while(choice < 1 || choice > 6);

        switch(choice){
            case 1:
                ATM.withdrawFunds(curUser, sc);
                break;
            case 2:
                ATM.withdraw20(curUser, sc);
                break;
            case 3:
                ATM.depositFunds(curUser, sc);
                break;
            case 4:
                ATM.showTransactions(curUser, sc);
                break;
            case 5:
                ATM.transferFunds(curUser, sc);
                break;
            case 6:
                sc.nextLine();
                break;
        }

        //redisplay this menu unless the user wants to quit
        if(choice != 6){
            ATM.printUserMenu(curUser, sc);
        }
    }


    public static void withdrawFunds(User curUser, Scanner sc) {
        int fromAcc;
        double amount;
        double acctBal;
        String memo;

        //get the account to withdraw from
        do{
            System.out.printf("Enter the number(1-%d) of the account +" +
                    "to withdraw from: ", curUser.getNumOfAccounts());
            fromAcc = sc.nextInt() -1;
            if(fromAcc < 0 || fromAcc >= curUser.getNumOfAccounts()) {
                System.out.printf("Invalid choice. Please choose 1-%d.\n", curUser.getNumOfAccounts());
            }
        }while(fromAcc < 0 || fromAcc >= curUser.getNumOfAccounts());
        acctBal = curUser.getAccBalance(fromAcc);

        //get the amount to transfer
        do{
            System.out.printf("Enter how much money you want to withdraw (max %.02f): $",
                    acctBal);
            amount = sc.nextDouble();
            if(amount < 0){
                System.out.println("Amount must be greater than 0.");
            } else if (amount >= acctBal){
                System.out.printf("Amount must be less than than %.02f\n", acctBal);
            }
        }while(amount < 0 || amount > acctBal);

        //erase previous input
        sc.nextLine();
        //create a memo
        System.out.print("Enter a memo: ");
        memo = sc.nextLine();

        curUser.addAccTransaction(fromAcc, -1*amount, memo);


    }


    public static void withdraw20(User curUser, Scanner sc) {
        int fromAcc;
        double amount;
        double acctBal;
        String memo;

        //get the account to withdraw from
        do{
            System.out.printf("Enter the number(1-%d) of the account +" +
                    "to withdraw from: ", curUser.getNumOfAccounts());
            fromAcc = sc.nextInt() -1;
            if(fromAcc < 0 || fromAcc >= curUser.getNumOfAccounts()) {
                System.out.printf("Invalid choice. Please choose 1-%d.\n", curUser.getNumOfAccounts());
            }
        }while(fromAcc < 0 || fromAcc >= curUser.getNumOfAccounts());
        acctBal = curUser.getAccBalance(fromAcc);

        //remove 20 dollars from account
        amount = 20.00;
        if(amount > acctBal){
            System.out.printf("Amount must be less than than %.02f\n", acctBal);
        } else{
            //erase previous input
            sc.nextLine();
            //create a memo
            System.out.print("Enter a memo: ");
            memo = sc.nextLine();

            curUser.addAccTransaction(fromAcc, -1*amount, memo);
        }

    }


    private static void depositFunds(User curUser, Scanner sc) {
        int toAcc;
        double amount;
        double acctBal;
        String memo;

        //get the account to deposit to
        do{
            System.out.printf("Enter the number(1-%d) of the account +" +
                    "to deposit in: ", curUser.getNumOfAccounts() );
            toAcc = sc.nextInt() -1;
            if(toAcc < 0 || toAcc >= curUser.getNumOfAccounts()) {
                System.out.printf("Invalid choice. Please choose 1-%d.\n", curUser.getNumOfAccounts());
            }
        }while(toAcc < 0 || toAcc >= curUser.getNumOfAccounts());
        acctBal = curUser.getAccBalance(toAcc);

        //get the amount to deposit
        do{
            System.out.printf("Enter how much money you want to deposit (max %.02f): $",
                    acctBal);
            amount = sc.nextDouble();
            if(amount < 0){
                System.out.println("Amount must be greater than 0.");
            }
        }while(amount < 0);

        //erase previous input
        sc.nextLine();
        //create a memo
        System.out.print("Enter a memo: ");
        memo = sc.nextLine();

        curUser.addAccTransaction(toAcc, amount, memo);
    }

    //show transaction histroy for an account
    public static void showTransactions(User curUser, Scanner sc) {
        int accNumber;

        //choose what account to look at
        do{
            System.out.printf("Enter the number(1-%d) of the account +" +
                            "whose transactions you want to see: ", curUser.getNumOfAccounts());
            accNumber = sc.nextInt()-1;
            if(accNumber < 0 || accNumber >= curUser.getNumOfAccounts()){
                System.out.printf("Invalid choice. Please choose 1-%d.\n",curUser.getNumOfAccounts() );
            }
        }while(accNumber < 0 || accNumber >= curUser.getNumOfAccounts());
        curUser.printAccTransactionHistory(accNumber);
    }

    //
    public static void transferFunds(User curUser, Scanner sc) {
        int fromAcc;
        int toAcc;
        double amount;
        double acctBal;

        //get the account to transfer from
        do{
            System.out.printf("Enter the number(1-%d) of the account +" +
                    "to transfer from: ", curUser.getNumOfAccounts());
            fromAcc = sc.nextInt() -1;
            if(fromAcc < 0 || fromAcc >= curUser.getNumOfAccounts()) {
                System.out.printf("Invalid choice. Please choose 1-%d.\n", curUser.getNumOfAccounts());
            }
        }while(fromAcc < 0 || fromAcc >= curUser.getNumOfAccounts());
        acctBal = curUser.getAccBalance(fromAcc);

        //get the account to transfer to
        do{
            System.out.printf("Enter the number(1-%d) of the account +" +
                    "to transfer to: ", curUser.getNumOfAccounts());
            toAcc = sc.nextInt() -1;
            if(toAcc < 0 || toAcc >= curUser.getNumOfAccounts()) {
                System.out.printf("Invalid choice. Please choose 1-%d.\n", curUser.getNumOfAccounts());
            }
        }while(toAcc < 0 || toAcc >= curUser.getNumOfAccounts());

        //get the amount to transfer
        do{
            System.out.printf("Enter how much money you want to send (max %.02f): $",
                    acctBal);
            amount = sc.nextDouble();
            if(amount < 0){
                System.out.println("Amount must be greater than 0.");
            } else if (amount >= acctBal){
                System.out.printf("Amount must be less than than %.02f\n", acctBal);
            }
        }while(amount < 0 || amount > acctBal);

        //make the transfer
        curUser.addAccTransaction(fromAcc, -1*amount, String.format("Transfer to account %s", curUser.getAccUniqueID(toAcc)));
        curUser.addAccTransaction(toAcc, amount, String.format("Transfer to account %s", curUser.getAccUniqueID(fromAcc)));

    }



}
