package Account;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CreateAccount {

    public boolean createAccountPossible(ArrayList<String> uI, ArrayList<User> userList) {
        boolean createAccountPossible = true;
        if (!uI.get(0).isBlank() && !uI.get(1).isBlank() && !uI.get(2).isBlank() && !uI.get(3).isBlank() && !uI.get(4).isBlank() && uI.get(4).equals(uI.get(5)) && !uI.get(6).isBlank()) {
            for(User u : userList) {
                if (uI.get(3).equals(u.getUsername()) || uI.get(6).equals(u.getEmail())) {
                    createAccountPossible = false;
                    break;
                }
            }
        } else {
            createAccountPossible = false;
        }
        return createAccountPossible;
    }

    public boolean createAccountJSON(ArrayList<String> uI, ArrayList<User> userList) throws IOException {
        boolean incorrAcc;
        if (createAccountPossible(uI, userList)) {
            incorrAcc = false;
            try {
                Scanner scanner = new Scanner(new File("src/main/resources/user.json"));
                String data = "";

                while (scanner.hasNext()) {
                    data = data + scanner.nextLine() + "\n";
                }

                data = data.substring(0, data.length() - 3);
                data = data + ", \n";

                scanner.close();
                FileWriter fileWriter = new FileWriter("src/main/resources/user.json");

                ObjectMapper mapper = new ObjectMapper();
                String json = mapper.writeValueAsString(new User(uI.get(0), uI.get(1), uI.get(2), uI.get(3), uI.get(4), uI.get(5)));

                fileWriter.write(data + json + "\n ]");
                fileWriter.flush();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            incorrAcc = true;
        }
        return incorrAcc;
    }

    /* Oude login check
    public boolean createAccount(String[] userInformation) {
        boolean incorrAcc;
        if (createAccountPossible(userInformation)) {
            incorrAcc = false;
            try {
                Scanner scanner = new Scanner(new File("src/main/resources/Data"));
                String data = "";
                while (scanner.hasNext()) {
                    data = data + scanner.nextLine() + "\n";
                }
                scanner.close();
                FileWriter fileWriter = new FileWriter("src/main/resources/Data");
                String userString = "";
                for (int i = 0; i < userInformation.length - 1; i++) {
                    userString = userString + userInformation[i] + ",";
                }
                userString = userString + userInformation[6];
                fileWriter.write(data + userString);
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            incorrAcc = true;
        }
        return incorrAcc;
    } */
}
