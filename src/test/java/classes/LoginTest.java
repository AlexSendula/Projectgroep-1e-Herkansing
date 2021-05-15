package classes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginTest {

    @Test
    void validateLogin() {
        assertFalse(new Login().validateLogin("pass", "password"));
        assertTrue(new Login().validateLogin("password", "password"));
    }
}