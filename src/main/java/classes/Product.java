package classes;


import java.util.ArrayList;

public class Product {
    private String name;
    private String description;
    private double price;
    private ArrayList<String> categories;

    public Product(String name, String description, double price, ArrayList<String> categories) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.categories = categories;
    }
}


