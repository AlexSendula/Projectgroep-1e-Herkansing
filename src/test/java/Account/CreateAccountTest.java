package Account;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreateAccountTest {

    //Coverage: decision
    @Test
     void createAccountPossible() {
        String[] userInfo = {"Kees", "Jan", "2021-06-09", "Jan", "wachtwoord", "wachtwoord", "hendrik@asd.nl"};
        String[] userInfoCorr = {"Kees","Jan","2021-06-09","Jan123","wachtwoord","wachtwoord","hendrik123@asd.nl"};

        String[] wachtwoordCheck = {"Kees","Jan","2021-06-09","Jan123","wachtwoor","wachtwoord","hendrik123@asd.nl"};

        assertTrue(new CreateAccount().createAccountPossible(userInfoCorr));
        assertFalse(new CreateAccount().createAccountPossible(userInfo));
        assertFalse(new CreateAccount().createAccountPossible(wachtwoordCheck));
    }
}