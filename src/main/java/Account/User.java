package Account;

import Shop.ShoppingCart;

public class User {
    private String username;
    private Login login;
    private ShoppingCart shoppingCart;

    public User(String username, String password){
        this.shoppingCart = new ShoppingCart();
        this.username = username;
        this.login = Login.getInstance(this.username, password);
    }

    public String getUsername() {
        return this.username;
    }
}
