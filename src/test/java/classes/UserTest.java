package classes;

import com.sun.scenario.effect.ImageData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void getInstance() {/*
        assertFalse(User.getInstance("Arman").getPassword().equals("Arman123"));
        assertTrue(User.getInstance("Arman").getPassword().equals("Arman12"));
    */}

    @Test
    void logOut(){/*
        User.getInstance("Arman");
        User.logOut();
        User.getInstance("Jan");
        assertFalse(User.getInstance("").getPassword().equals("Arman12"));
        assertTrue(User.getInstance("").getPassword().equals("Jan12"));
    */}

    @Test
    void readData() {

        assertFalse(User.getInstance().readData("Aleks","ww3"));
        assertTrue(User.getInstance().readData("dylan","ww1"));
    }
}