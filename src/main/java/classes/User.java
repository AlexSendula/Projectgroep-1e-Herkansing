package classes;

import controller.CreateAccountController;

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

        if (single_instance == null) {
//            TODO: Gegevens uit file laten komen.
            single_instance = new User(1, "username", "password", "a@a.nl");
        }
        return single_instance;
}
    public boolean readData(String inputUsername, String inputPassword) {
        try {
            Scanner scanner = new Scanner(new File("src/main/resources/Data"));
            while (scanner.hasNextLine()){
                String[] Data = scanner.nextLine().split(",");
                if (Data[3].equals(inputUsername) && Data[4].equals(inputPassword)){
                    return true;
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
    public void createAccount(String firstName, String lastName, String Date, String userName, String password, String confirmPassword, String email){
        boolean createAccountPossible = true;
        if (!firstName.equals("") && !lastName.equals("")  && !userName.equals("") && !password.equals("") && password.equals(confirmPassword) && !email.equals("")) {
            try {
            Scanner scanner = new Scanner(new File("src/main/resources/Data"));

            //TODO: controleren of username al bestaat
            while (scanner.hasNextLine()&&createAccountPossible){
                String[] userData = scanner.nextLine().split(",");
                if (userData[3].equals(userName)){
                    createAccountPossible = false;
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        }
        if (createAccountPossible){
            CreateAccountController.incorrectAccount = false;
            try {
                Scanner scanner = new Scanner(new File("src/main/resources/Data"));
                String data = "";
                while (scanner.hasNext()){
                    data = data+scanner.nextLine()+"\n";
                }
                scanner.close();
                FileWriter fileWriter = new FileWriter("src/main/resources/Data");
                fileWriter.write(data +firstName + "," + lastName + "," + Date + "," + userName + "," + password + "," + email);
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }else {
            CreateAccountController.incorrectAccount = true;
        }
    }
    public static void logOut(){
        single_instance = null;
    }

    public String getPassword() {
        return password;
    }
}
