package Shop;

import Receipts.Receipt;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class ReceiptTest {
    @Test
    void schrijfBon() {
        int bonNummer = Receipt.receiptNumber();
        ArrayList<String> producten = new ArrayList<>();
        producten.add("Houtbalk");
        producten.add("Schroef");
        new Receipt("Arman",producten,10).writeReceipt(bonNummer);
        String resultaat = "Customer Arman has purchased the following products:\n" +
                "Houtbalk, Schroef, \n" +
                "The total price: 100.0.\n" +
                "Your discount is: 10.0%.\n" +
                "Your price after discount: 90.0";
        String foutResultaat = "Customer Arman has purchased the following products:\n" +
                "Houtbalk, Schroef, \n" +
                "The total price: 120.0.\n" +
                "Your discount is: 20.0%.\n" +
                "Your price after discount: 80.0";
        String input = "";
        try {
            Scanner scanner = new Scanner(new File("src/main/resources/Receipts/receipt"+bonNummer+".txt"));
            while (scanner.hasNextLine()) {
                input = input+scanner.nextLine()+"\n";
            }
            input.substring(0,input.length()-1);
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assertTrue(input.contains(resultaat));
        assertFalse(foutResultaat.contains(input));
    }
}