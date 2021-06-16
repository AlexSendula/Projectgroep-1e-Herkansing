package Shop;


import Account.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

@JsonIgnoreProperties
public class ShoppingCart {
    @JsonProperty("product")
    private ArrayList<Product> product;
    @JsonProperty("totalPrice")
    private double totalPrice;

    private User user;

    public ShoppingCart(){
        this.product = new ArrayList<>();
    }

    public ArrayList<Product> getProduct() {
        return product;
    }

    public void addToCart(Product product){
        this.product.add(product);
    }

    public double getTotalPrice(){
        totalPrice = 0;
        for (int n = 0; n<this.getProduct().size(); n++){
            totalPrice = totalPrice + this.getProduct().get(n).getPrice();
        }
        return totalPrice;
    }
}
