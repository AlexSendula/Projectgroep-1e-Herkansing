package Account;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CreateAccount {

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

    public boolean createAccount(String[] userInformation){
        boolean incorrAcc;
        if (createAccountPossible(userInformation)){
            incorrAcc = false;
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
            incorrAcc = true;
        }
        return incorrAcc;
    }
}
