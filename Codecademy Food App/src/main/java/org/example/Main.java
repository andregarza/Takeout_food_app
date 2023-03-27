package org.example;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.println("What is your name: ");
        String userName = input.next();
        System.out.println("How much money you have: ");
        try{
            int userMoney = input.nextInt();
            Customer customer = new Customer(userName, userMoney);
            TakeOutSimulator takeOutSimulator = new TakeOutSimulator<>(customer,new FoodMenu(), input);
            takeOutSimulator.startTakeOutSimulator();

        }catch(InputMismatchException e){
            System.out.println("Invalid amount !");
        }



    }
}