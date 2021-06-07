package Receipts;

import Users.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import static java.lang.Integer.parseInt;

public class Receipt {
    private String nameClient;
    private ArrayList<String> products; //TODO: Naar product object veranderen? Verbinden aan product/item klasse.
    private double totalPrice;
    private double discountPercentage;
    public double priceAfterDiscount;

    public Receipt(String nameClient, ArrayList<String> products, double discountPercentage) {
        this.nameClient = nameClient;
        this.products = products;
        this.discountPercentage = discountPercentage;
        this.totalPrice = 100; //TODO: Alle prijzen van producten op tellen die in arraylist zitten
        this.priceAfterDiscount = Math.round((totalPrice * (1 - discountPercentage / 100)) * 100) / 100; //TODO: Afronden op twee decimalen
    }

    public static int receiptNumber() {
        String input = "";
        int receiptNumber = 0;
        int newNumber = 0;
        Scanner scanner = null;

        try {
            scanner = new Scanner(new File("src/main/resources/bonNummer"));
            input = scanner.nextLine();
            scanner.close();
            receiptNumber = parseInt(input);
            newNumber = receiptNumber + 1;
            FileWriter writer = new FileWriter("src/main/resources/bonNummer");
            writer.write("" + newNumber);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return receiptNumber;
    }

    public void writeReceipt(int receiptNumber) {
        try {
            FileWriter writer = new FileWriter("src/main/resources/Receipts/receipt" + receiptNumber + "-" + this.nameClient + ".txt");
            String text = "Customer " + this.nameClient + " has purchased the following products:\n";
            for (String product : this.products) {
                text = text + product + ", ";
            }
            text = text + "\nThe total price: " + this.totalPrice + ".\nYour discount is: " + discountPercentage + "%.\n" + "Your price after discount: " + this.priceAfterDiscount + ".";

            text = text.substring(0, text.length() - 1);
            writer.write(text);
            writer.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public static String readReceipt(String fileName) {
        String receipt = "";

        try {
            Scanner scanner = new Scanner(new File("src/main/resources/Receipts/" + fileName));
            while (scanner.hasNextLine()) {
                receipt += scanner.nextLine() + "\n";
                receipt.substring(0, receipt.length() - 1);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return receipt;
    }

    public static ArrayList<String> allReceiptsPerUser(User activeUser) {
        ArrayList<String> receipts = new ArrayList<>();

        String target_dir = "src/main/resources/Receipts/";
        File dir = new File(target_dir);
        File[] files = dir.listFiles();

        for (File f : files) {
            String fileName = f.getName();
            if (f.getName().substring(f.getName().lastIndexOf("-") + 1).equals(activeUser.getUsername() + ".txt")) {
                receipts.add(fileName);
            }
        }
        return receipts;
    }
}
