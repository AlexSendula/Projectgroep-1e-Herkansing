package Shop;

import Account.User;
import Receipts.Receipt;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;



class ReceiptTest {

    @Test
    void testToString() {
        User user = new User("Jan","Hendrik");
        user.getShoppingCart().addToCart(new Product());
        Receipt receipt = new Receipt(user);
        receipt.writeReceipt(50);

        assertTrue(receipt.toString().equals("Customer Jan has purchased the following products:\n" +
                "null.\n" +
                "The total price: 0.0.\n" +
                "Your discount is: 0.0%.\n" +
                "Your price after discount: 0.0"));
        assertFalse(receipt.toString().equals("Customer Hendrik has purchased the following products:\n" +
                "null.\n" +
                "The total price: 0.0.\n" +
                "Your discount is: 0.0%.\n" +
                "Your price after discount: 0.0"));
    }
}