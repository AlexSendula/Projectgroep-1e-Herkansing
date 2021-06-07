package Users;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Login {
    private User user;
    private static Login single_instance = null;

    private Login(String username){
        this.user = new User(username);
    }


    public static Login getInstance(String username) {
        if (single_instance == null) {
            single_instance = new Login(username);
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
    public static void logOut(){
        single_instance = null;
    }

    public User getUser() {
        return user;
    }
}
