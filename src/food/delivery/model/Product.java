package food.delivery.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Product {

    private String id;
    private String name;
    List<String> ingredients;
    private double price;

    public Product(String name, List<String> ingredients, double price) {
        this.name = name;
        this.ingredients = ingredients;
        this.price = price;
        this.id = UUID.randomUUID().toString();
    }

    public Product(String name, List<String> ingredients, double price , String id) {
        this.name = name;
        this.ingredients = ingredients;
        this.price = price;
        this.id = id;
    }

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public Product(String name, double price, String id) {
        this.name = name;
        this.price = price;
        this.id = id;
    }

    @Override
    public String toString() {
        return  "Name= " + name + ' ' +
                "Ingredients= " + ingredients + ' '+
                "Price= " + price;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
