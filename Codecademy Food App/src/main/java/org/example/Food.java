package org.example;

// Create Food class here
public class Food implements PricedItem<Integer> {

    private String name;
    private String description;
    private int price;

    public Food(String name, String description, int price){
        this.name = name;
        this.description = description;
        this.price = price;
    }

    @Override
    public Integer getPrice(){
        return this.price;

    }

    @Override
    public void setPrice(Integer price) {
        this.price = price;

    }

    public String getName(){
        return name;
    }


    @Override
    public String toString(){
        return "Enjoy a " + name + "description: " + description + "  Cost:  $" + price;
    }
}