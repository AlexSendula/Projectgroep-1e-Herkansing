package Shop;

import Account.User;
import Receipts.Receipt;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class ShopControllerTest {
    User user = new User("test", "test");
    ShopController shop = new ShopController();

    //De totaalprijs van de bon komt overeen met de totaalprijs van de items in de winkelwagen.
    @Test
    void totalPrice() throws IOException {
        shop.parseData();
        ArrayList<Product> products = new ArrayList<>(shop.getProductList());

        user.getShoppingCart().addToCart(products.get(0));
        user.getShoppingCart().addToCart(products.get(1));

        double shoppingcartPrice = user.getShoppingCart().getTotalPrice();

        new Receipt(user).writeReceipt(Receipt.receiptNumber());
        ArrayList<String> receiptList = new ArrayList<>(Receipt.allReceiptsPerUser(user));
        String receipt = Receipt.readReceipt(receiptList.get(0));

        double receiptPrice = Double.parseDouble(receipt.substring(89, 95));

        assertEquals(shoppingcartPrice, receiptPrice);

        File file = new File("src/main/resources/Receipts/" + receiptList.get(0));
        file.delete();
    }

    //Er zijn minimaal 5 producten.
    //Equivalentieklas, randwaarden, coverage: condition
    @Test
    void numberOfProducts() throws IOException {
        shop.parseData();
        ArrayList<Product> products = new ArrayList<>(shop.getProductList());
        int count = 0;
        for (Product product : products) {
            count++;
        }

        assertTrue(products.size() >= 5);
        assertFalse(products.size() <= 4);


        products.remove(products.size()-1);
        products.remove(products.size()-1);

        assertTrue(products.size() <= 4);
        assertFalse(products.size() >= 5);


    }

    //Elke item bevat een naam, artikelnummer, beschrijving, prijs, voorraad en categorie
    //Equivalentieklas, randwaarden
    @Test
    void productPropertiesComplete() throws IOException {
        shop.parseData();
        ArrayList<Product> products = new ArrayList<>(shop.getProductList());
        for (Product product : products) {
            assertNotNull(product.getName());
            assertNotNull(product.getDescription());
            assertNotNull(product.getCategories());

            assertTrue(product.getId() > 0);
            assertTrue(product.getPrice() > 0);
            assertTrue(product.getStock() > 0);

            assertNotEquals(0, product.getId());
            assertNotEquals(0, product.getPrice());
            assertNotEquals(0, product.getStock());
        }
    }

    //Er zijn items met zowel enkele als meerdere en herhalende categorieen
    //Equivalentieklas
    @Test
    void numberOfCategoriesPerProduct() throws IOException {
        shop.parseData();
        ArrayList<Product> products = new ArrayList<>(shop.getProductList());
        boolean oneCategory = false;
        boolean multipleCategories = false;
        for (Product product : products) {
            if (product.getCategories().size() == 1) {
                oneCategory = true;
            } else if (product.getCategories().size() > 1) {
                multipleCategories = true;
            }
        }
        assertTrue(oneCategory);
        assertTrue(multipleCategories);
    }

    //Elke item heeft realistische waarden
    //Equivalentieklas
    @Test
    void realisticValues() throws IOException {
        shop.parseData();
        ArrayList<Product> products = new ArrayList<>(shop.getProductList());

        for (Product product : products) {
            assertEquals(6, String.valueOf(product.getId()).length());
            assertTrue(product.getName().length() > 2);
            assertTrue(product.getDescription().length() > 5);
            assertTrue(product.getPrice() > 0 && product.getPrice() < 50);
            assertTrue(product.getStock() < 500);
            assertNotNull(product.getCategories());
        }
    }


}
