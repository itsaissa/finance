package com.main.java;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        User newUser = new User("John Doe", generateRandomIDNumber(), generateRandomPINNumber());
        Storage curStorage = new Storage();
        curStorage.addUser(newUser);
        Scanner sc = new Scanner(System.in);
        User curUser;
        System.out.println(newUser.toString());
        while(true){
            curUser = Main.login(curStorage, sc);

            System.out.println("Welcome back, " + curUser.getName());
            curUser.financeSummary();

            Main.mainMenu(curUser, sc);
        }
    }
    /**
     * generate a random Id number for a user
     * @return a random Id number
     */
    private static int generateRandomIDNumber() {
        int returnedID;
        Random rnd = new Random();
        returnedID = rnd.nextInt(100);
        return returnedID;
    }
    /**
     * generate a random pin number for a user
     * @return a random pin number
     */
    private static int generateRandomPINNumber() {
        int returnedPIN;
        Random rnd = new Random();
        returnedPIN = rnd.nextInt(100);
        return returnedPIN;
    }

    /**
     * authenitcate a user's credentials such as id and pin number
     * @param sc - An input scanner object
     * @param curStorage - The current storage object for the app
     * @return the current user using the app
     */
    private static User login(Storage curStorage, Scanner sc) {
        int id;
        int pin;
        User curUser;
        do{
            System.out.print("Welcome to the Finance App!\n" +
                    "Enter your ID number here: ");
            id = sc.nextInt();
            System.out.print("Enter your pin here: ");
            pin = sc.nextInt();
            curUser = curStorage.validateUser(id, pin);
            System.out.println("");
        }while(curUser == null);
        return curUser;
    }

    /**
     * Prints the main menu options of the app
     * @param sc - An input scanner object
     * @param curUser - The current user of the app
     */
    private static void mainMenu(User curUser, Scanner sc) {

        int choice;
        do{
            System.out.println("Pick one of the following options");
            System.out.println("1. Financial Summary");
            System.out.println("2. Simulate a month");
            System.out.println("3. Peek your net income in the last twelve months");
            System.out.println("4. Give Monthly Advice");
            System.out.println("5. Logout");
            System.out.print("Enter your choice here: ");
            choice = sc.nextInt();

            if(choice < 1 || choice > 5){
                System.out.println("Invalid option. Please choose 1-3");
            }
        }while(choice < 1 || choice > 5);

        switch(choice){
            case 1:
                curUser.financeSummary();
                break;
            case 2:
                Main.simulateMonth(sc, curUser);
                break;
            case 3:
                curUser.peekLastTwelveMonths();
                break;
            case 4:
                Main.giveMonthlyAdvice(curUser);
                break;
            case 5:
                sc.nextLine();
                System.out.println("\nThank you for using Finance.\nSecurely logged out!\n");
                break;
        }

        if(choice != 5){
            Main.mainMenu(curUser, sc);
        }
    }

    private static void giveMonthlyAdvice(User curUser) {
        if(curUser.hasNoData() == true){
            System.out.println("\nWe have no data on you. Simulate a month and input your data there.\n");
        }
        else{
            int netNumber = (int)curUser.getIncome() - (int)curUser.getExpenses();
            if(netNumber > 1000){
                System.out.println("\nNow you have some serious cash leftover! Invest in crypto, and keep flipping high-valued objects\n");
            }
            else if(netNumber > 100 && netNumber <= 1000){
                System.out.println("\nNow you have a decent amount of cash saved. Consider flipping in-demand items or get started in crypto investing.\n");
            }
            else if(netNumber >= 0 && netNumber <= 100){
                System.out.println("\nYou barely have any extra cash, if any. I suggest investing it in making more money or save some for a 'rainy day'." +
                        "Consider selling water bottles on the weekend.\n");
            }
            else if(netNumber < 0 && netNumber > -100 ){
                System.out.println("\nYou have some debt you need to fix. Get a side hustle on the weekends as a means of earning some more income.\n");
            }
            else if(netNumber <= -100  && netNumber > -1000 ){
                System.out.println("\nOkay, you have some serious debt you have to pay off. Consider working double hard during your breaks.\n" +
                        "You can't afford to be slacking off.");
            }
            else{
                System.out.println("\nWow, you have ALOT of debt to pay off. Consider getting a second job to get more income and keep saving your cash.\n");
            }
        }
    }

    private static void simulateMonth(Scanner sc, User curUser) {
        double curIncome;
        double curExpenses;
        System.out.print("\nHow much did you earn this month? Enter here: $");
        curIncome = sc.nextDouble();
        System.out.print("How much did you spend this month? Enter here: $");
        curExpenses = sc.nextDouble();
        System.out.println("\nYour total net income for this month is " + curUser.calculateNet(curIncome, curExpenses) + "\n");
    }

}
