package classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class User {
    private int id;
    private String username;
    private String password;
    private String email;
    private static User single_instance = null;

    private User(int id, String username, String password, String email){
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }
    public static User getInstance() {

        return single_instance;

        if (single_instance == null)
//            TODO: Gegevens uit file laten komen.
            single_instance = new User(1, "username", "password", "a@a.nl");

        return single_instance;
}
    public boolean readData(int inputId, String inputUsername, String inputPassword) {
        try {
            Scanner scanner = new Scanner(new File("src/main/resources/Data"));
            while (scanner.hasNextLine()){
                String[] Data = scanner.nextLine().split(",");
                if (Data[0].equals(inputId) && Data[1].equals(inputUsername) && Data[2].equals(inputPassword)){
                    return true;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void logOut(){
        single_instance = null;
    }

    public String getPassword() {
        return password;
    }
}
