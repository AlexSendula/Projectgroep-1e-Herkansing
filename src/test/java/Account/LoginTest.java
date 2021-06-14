package Account;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginTest {

    @Test
    void readData() {
        assertFalse(Login.readData("Arma", "123"));
        assertFalse(Login.readData("Arman", "12"));
        assertTrue(Login.readData("Arman", "123"));
    }
}