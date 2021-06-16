package Account;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CreateAccountTest {

    //Coverage: decision
    @Test
     void createAccountPossible() throws IOException {
        CreateAccountController controller = new CreateAccountController();
        controller.parseData();
        ArrayList<String> userInfo = new ArrayList<>();
        userInfo.add("Kees");
        userInfo.add("Jan");
        userInfo.add("2021-06-09");
        userInfo.add("Jan");
        userInfo.add("wachtwoord");
        userInfo.add("wachtwoor");
        userInfo.add("hendrik@asd.nl");
        assertFalse(new CreateAccount().createAccountPossible(userInfo,controller.getUserList()));
        userInfo.remove(5);
        userInfo.add(5,"wachtwoord");
        assertTrue(new CreateAccount().createAccountPossible(userInfo,controller.getUserList()));

        userInfo.remove(3);
        userInfo.add(3,"dylan");
        assertFalse(new CreateAccount().createAccountPossible(userInfo,controller.getUserList()));
        userInfo.remove(3);
        userInfo.add(3,"nietdylan");
        assertTrue(new CreateAccount().createAccountPossible(userInfo,controller.getUserList()));
    }
}