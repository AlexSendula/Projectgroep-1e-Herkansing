package Shop;


import java.util.ArrayList;

public class ShoppingCart {
    private ArrayList<Product> products;

    public ShoppingCart(){
        this.products = new ArrayList<>();
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void addToCart(Product product){
        this.products.add(product);
    }

    public double getTotalPrice(){
        double totalPrice = 0;
        for (int n = 0; n<this.getProducts().size(); n++){
            totalPrice = totalPrice + this.getProducts().get(n).getPrice();
        }
        return totalPrice;
    }
}
