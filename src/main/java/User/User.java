package User;

import controller.CreateAccountController;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class User {
    private String username;
    private static User single_instance = null;

    private User(String username){
        this.username = username;
    }
    public static User getInstance(String username) {
        if (single_instance == null) {
            single_instance = new User(username);
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

    public boolean createAccountPossible(String[] userInformation) {
        boolean createAccountPossible = true;
        if (!userInformation[0].isBlank() && !userInformation[1].isBlank() && !userInformation[2].isBlank() && !userInformation[3].isBlank() && !userInformation[4].isBlank() && userInformation[5].equals(userInformation[4]) && !userInformation[6].isBlank()) {
            try {
                Scanner scanner = new Scanner(new File("src/main/resources/Data"));

                while (scanner.hasNextLine() && createAccountPossible) {
                    String[] userData = scanner.nextLine().split(",");
                    if (userData[3].equals(userInformation[3])) {
                        createAccountPossible = false;
                    }
                }
                scanner.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            createAccountPossible = false;
        }
        return createAccountPossible;
    }
        public void createAccount(String[] userInformation){
        if (createAccountPossible(userInformation)){
            CreateAccountController.incorrectAccount = false;
            try {
                Scanner scanner = new Scanner(new File("src/main/resources/Data"));
                String data = "";
                while (scanner.hasNext()){
                    data = data+scanner.nextLine()+"\n";
                }
                scanner.close();
                FileWriter fileWriter = new FileWriter("src/main/resources/Data");
                String userString = "";
                for (int i=0;i<userInformation.length-1 ;i++){
                    userString= userString+userInformation[i]+",";
                }
                userString = userString+userInformation[6];
                fileWriter.write(data +userString);
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

    public String getUsername() {
        return this.username;
    }
}
