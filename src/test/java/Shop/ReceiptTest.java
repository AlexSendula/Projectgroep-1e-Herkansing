package Shop;

import Account.User;
import Receipts.Receipt;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;



class ReceiptTest {

    @Test
    void testToString() throws IOException {
        ShopController shop = new ShopController();
        User user = new User("Jan","Hendrik");
        shop.parseData();
        ArrayList<Product> products = new ArrayList<>(shop.getProductList());
        user.getShoppingCart().addToCart(products.get(0));
        user.getShoppingCart().addToCart(products.get(1));
        Receipt receipt = new Receipt(user);
        assertTrue(receipt.toString().equals("Customer Jan has purchased the following products:\n" +
                "Laptop, Televisie.\n" +
                "The total price: 644.98.\n" +
                "Your discount is: 0.0%.\n" +
                "Your price after discount: 644.98"));
        assertFalse(receipt.toString().equals("Customer Hendrik has purchased the following products:\n" +
                "Laptop, Televisie.\n" +
                "The total price: 644.98.\n" +
                "Your discount is: 0.0%.\n" +
                "Your price after discount: 644.98"));
    }
}