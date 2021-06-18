package Account;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class LoginTest {

    @Test
    void login() throws IOException {
        LoginController loginController = new LoginController();
        loginController.parseData();

        String[] loginInfo = {"dylan","123"};
        assertTrue(Login.checkData(loginInfo,loginController.getUserList()) != null);
        String[] wrongLogin = {"dylan","12"};
        assertFalse(Login.checkData(wrongLogin,loginController.getUserList()) != null);
    }
} 