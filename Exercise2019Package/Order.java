package Exercise2019Package;

import java.util.ArrayList;

public class Order {
    private Integer id;
    private Integer teamSize;
    private ArrayList<Pizza> pizzas;
    private ArrayList<String> ingredients;
    private Integer score;

    public Order(Integer id, Integer teamSize) {
        this.id = id;
        this.teamSize = teamSize;
    }

    public void addPizza(Pizza pizza) {
        this.pizzas.add(pizza);

        for (int i = 0; i < pizza.getIngredients().size(); i++) {
            if (!ingredients.contains(pizza.getIngredients().get(i))) {
                ingredients.add(pizza.getIngredients().get(i));
            }
        }
    }

    public ArrayList<Pizza> getPizzas() {
        return this.pizzas;
    }

    public Integer comparePizza(Pizza pizza) {
        ArrayList<String> uniqueIngredients = pizza.getIngredients();

        uniqueIngredients.removeAll(ingredients);

        return uniqueIngredients.size();
    }
}