package classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import static java.lang.Integer.parseInt;

public class BonVerwerker {
    private String naamKlant;
    private ArrayList<String> producten; //TODO: Naar product object veranderen? Verbinden aan product/item klasse.
    private double totaalprijs;
    private double kortingspercentage;
    public double prijsNaKorting;

    public BonVerwerker(String naamKlant, ArrayList<String> producten, double kortingspercentage){
        this.naamKlant = naamKlant;
        this.producten = producten;
        this.kortingspercentage = kortingspercentage;
        this.totaalprijs = 100; //TODO: Alle prijzen van producten op tellen die in arraylist zitten
        this.prijsNaKorting = Math.round((totaalprijs*(1-kortingspercentage/100))*100)/100; //TODO: Afronden op twee decimalen
    }

    public static int bonNummer() {
        String input ="";
        int bonNummer = 0;
        int nieuwNummer=0;
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("src/main/resources/bonNummer"));
            input = scanner.nextLine();
            scanner.close();
            bonNummer = parseInt(input);
            nieuwNummer = bonNummer+1;
            FileWriter writer = new FileWriter("src/main/resources/bonNummer");
            writer.write(""+nieuwNummer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bonNummer;
    }

    public void schrijfBon(int bonNummer) {
        try {
            FileWriter writer = new FileWriter("src/main/resources/Receipts/receipt"+bonNummer+".txt");
            String tekst = "Customer "+this.naamKlant+" has purchased the following products:\n";
            for (String product : this.producten) {
                tekst = tekst + product + ", ";
            }
            tekst = tekst + "\nThe total price: "+this.totaalprijs+".\nYour discount is: "+kortingspercentage+"%.\n"+"Your price after discount: "+this.prijsNaKorting+".";

            tekst = tekst.substring(0, tekst.length() - 1);
            writer.write(tekst);
            writer.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public static String leesBon() {
        String bon = "";
        int bonNummer;
        try {
            Scanner scanner = new Scanner(new File("src/main/resources/bonNummer"));
            bonNummer = parseInt(scanner.nextLine());
            scanner.close();
            scanner = new Scanner(new File("src/main/resources/Receipts/receipt"+bonNummer+".txt"));
            while (scanner.hasNextLine()){
                bon = bon+scanner.nextLine()+"\n";
                bon.substring(0,bon.length()-1);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return bon;
    }
}
