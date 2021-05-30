package classes;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

@JsonIgnoreProperties
public class Product {
    @JsonProperty("productId")
    private long id;
    private String name;
    private String description;
    private double price;
    private long stock;
    private ArrayList<String> categories;


    public Product() {

    }

    public Product(long id, String name, String description, double price, long stock, ArrayList<String> categories) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.categories = categories;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public long getStock() {
        return stock;
    }

    public ArrayList<String> getCategories() {
        return categories;
    }
}


