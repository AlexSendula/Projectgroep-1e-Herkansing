package Account;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Login {
    private static Login single_instance = null;

    private Login(){}

    public static Login getInstance(String username, String password) {
        if (single_instance == null && readData(username, password)) {
            single_instance = new Login();
            return single_instance;
        }
        return null;

    }

    public static boolean readData(String inputUsername, String inputPassword) {
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

    public static void logOut(){
        single_instance = null;
    }

}
