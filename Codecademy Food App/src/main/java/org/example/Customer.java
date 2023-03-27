package org.example;

// Create Customer class here

public class Customer {

    private String name;
    private int money;

    public Customer(String name, int money){
        this.name = name;
        this.money = money;
    }

    public String getName(){
        return this.name;
    }

    public int getMoney(){
        return this.money;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setMoney(int money){
        this.money = money;
    }
}