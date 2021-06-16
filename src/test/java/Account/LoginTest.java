package Account;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class LoginTest {

    @Test
    void login() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File("src/main/resources/user.json");
        ArrayList<User> userList = mapper.readValue(file, new TypeReference<>(){});

        assertNotNull(Login.checkData("1", "1", userList));
    }
}

