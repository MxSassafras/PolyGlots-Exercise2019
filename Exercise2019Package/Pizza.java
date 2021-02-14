package Exercise2019Package;

import java.util.ArrayList;

public class Pizza {
    private Integer id;
    private ArrayList<String> ingredients = new ArrayList<String>();

    public Pizza(Integer id) {
        this.id = id;
    }

    public void addIngredient(String ingredient) {
        this.ingredients.add(ingredient);
    }

    public Integer getId() {
        return id;
    }

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

//    @Override
//    public int compareTo(Pizza pizza){
//        Integer size = new Integer(ingredients.size());
//        Integer secondSize = new Integer(pizza.getIngredients().size());
//        return size.compareTo(secondSize);
//    }
}