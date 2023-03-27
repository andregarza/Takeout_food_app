package org.example;

import java.util.ArrayList;
import java.util.List;

// Create FoodMenu class here
public class FoodMenu{
    private List<Food> menu;
    private Food sushi;
    private Food pizza;
    private Food tacos;

    public FoodMenu(){
        menu = new ArrayList<>();
        sushi = new Food("Sushi ", "a roll of tuna Sushi", 140 );
        pizza = new Food("Pizza", " a hawaiian pizza ", 220);
        tacos = new Food("Pastor Tacos", "3 al pastor Tacos", 120);
        menu.add(sushi);
        menu.add(pizza);
        menu.add(tacos);
    }

    @Override
    public String toString() {
        Food food;
        String singleString = "";
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<menu.size();i++){
            food = menu.get(i);
            sb.append((i+1) +". " + food.toString() + "\n");
            singleString = sb.toString();
        }
        return singleString;
    }

    public Food getFood(int index){
        try{
            return menu.get(index - 1);
        }catch(IndexOutOfBoundsException  | IllegalArgumentException  | ClassCastException e) {
            System.out.println("The index you have entered is invalid");
            return null;
        }

    }

    public Food getLowestCostFood(){
        Integer lowest = Integer.MAX_VALUE;
        Food lowestFood = this.pizza;
        for (Food item:menu){
            if(item.getPrice() < lowest){
                lowest = item.getPrice();
                lowestFood = item;
            }
        }
        return lowestFood;
    }

}
