package org.example;

import java.util.Scanner;

public class TakeOutSimulator<T>{
    private Customer customer;
    private FoodMenu menu;
    private Scanner input;

    public TakeOutSimulator(Customer customer, FoodMenu menu, Scanner input) {
        this.customer = customer;
        this.menu = menu;
        this.input = input;
    }

    private  T getOutputOnIntInput(String userInputPrompt, IntUserInputRetriever intUserInputRetriever){
        while(true){
            System.out.println(userInputPrompt);
            Scanner myObj = new Scanner(System.in);
            if(myObj.hasNextInt()){
                int userInput = myObj.nextInt();
                try{
                   return (T) intUserInputRetriever.produceOutputOnIntUserInput(userInput);

                }catch(IllegalArgumentException e){
                    System.out.println(userInput + "is not valid input. Try Again!");
                }
            }else{
                System.out.println("Give a valid number input");
            }
        }

    }

    public boolean shouldSimulate() throws IllegalArgumentException {

        String userPrompt = "Enter 1 to CONTINUE simulation or 0 to EXIT program";
        IntUserInputRetriever intUserInputRetriever =  s -> {
            if (s == 1 && customer.getMoney() >= menu.getLowestCostFood().getPrice() ){
                System.out.println("Your selection was 1 and you got enough money");
                return true;
            }else if (s == 0 || customer.getMoney() < menu.getLowestCostFood().getPrice()){
                System.out.println("Your selection was 0 or you don´t have enough money");
                return false;

            }else return new IllegalArgumentException();

        };

        return (boolean) getOutputOnIntInput(userPrompt, intUserInputRetriever);
    }

    public Food getMenuSelection() throws IllegalArgumentException{
        String userPrompt = "Today's Menu Options! \n " + menu.toString();
        IntUserInputRetriever intUserInputRetriever = s -> {
            if(menu.getFood(s) != null){
                return menu.getFood(s);
            }
            else return new IllegalArgumentException();


        };
        try{
            return (Food) getOutputOnIntInput(userPrompt, intUserInputRetriever);
        }catch(ClassCastException e){
            System.out.println("Invalid");
            return null;

        }

    }

    public boolean isStillOrderingFood() throws IllegalArgumentException{
        String userPrompt = "Enter 1 to CONTINUE shopping or 0 to CHECKOUT: ";
        IntUserInputRetriever intUserInputRetriever = s -> {
            if( s == 1){
                return true;
            }
            else if( s == 0){
                return false;
            }
            else return new IllegalArgumentException();
        };
        return (boolean) getOutputOnIntInput(userPrompt, intUserInputRetriever);
    }

    public void checkoutCustomer(ShoppingBag<Food> shoppingBag) {
        System.out.println("Processing payment... \n");
        customer.setMoney(customer.getMoney() - shoppingBag.getTotalPrice());
        System.out.println("Your remaining money: $" + customer.getMoney() + "\n");
        System.out.println("Thank you and enjoy your food! \n");

    }

    public void takeOutPrompt(){
        ShoppingBag<Food> shoppingBag = new ShoppingBag<>();
        int customerMoneyLeft = customer.getMoney();
        boolean status = true;

        while(status){
            Food order;
            System.out.println("You have " + customerMoneyLeft + "$ left to spend \n");
            order = getMenuSelection();
            if(order.getPrice() < customerMoneyLeft ){
                customerMoneyLeft -= order.getPrice() ;
                shoppingBag.addItem(order);
                System.out.println("Adding " + order.getName() + " to Shopping bag");
            }else{
                System.out.println("You don´t have enough money, pick another item or checkout \n");
            }
            status = isStillOrderingFood();

        }
        checkoutCustomer(shoppingBag);

    }

    public void startTakeOutSimulator(){
        boolean status = true;
        while (status){
            System.out.println("Hello, welcome to my restaurant!");
            System.out.println("Welcome " + customer.getName());
            takeOutPrompt();
            status = shouldSimulate();
        }
    }



}