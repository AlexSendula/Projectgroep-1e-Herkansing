package Account;

import Notifications.MissingOrWrongFields;
import Notifications.Notification;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CreateAccountController implements Initializable {
    private ArrayList<User> userList;

    @FXML
    private AnchorPane rootPane;
    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private TextField userNameTextField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField confirmPasswordField;
    @FXML
    private TextField emailTextField;
    @FXML
    private Label incorrectLabel;
    @FXML
    private DatePicker birthDay;


    public void createAccount(MouseEvent mouseEvent) throws IOException {
        ArrayList<String> userInformation = new ArrayList<>();
        userInformation.add(firstNameTextField.getText());
        userInformation.add(lastNameTextField.getText());
        userInformation.add(String.valueOf(birthDay.getValue()));
        userInformation.add(userNameTextField.getText());
        userInformation.add(passwordField.getText());
        userInformation.add(confirmPasswordField.getText());
        userInformation.add(emailTextField.getText());

        if (!new CreateAccount().createAccountJSON(userInformation, userList)){
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/LoginScreen.fxml"));
            rootPane.getChildren().setAll(pane);
        } else {
            Notification notCorrect = new MissingOrWrongFields();
            incorrectLabel.setText(notCorrect.showNotification());
            incorrectLabel.setVisible(true);
        }
    }

    public void backButton(MouseEvent mouseEvent) throws  IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/LoginScreen.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    private static File productJsonFile() {
        return new File("src/main/resources/user.json");
    }

    public void parseData() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        userList = mapper.readValue(productJsonFile(), new TypeReference<>(){});
    }

    public ArrayList<User> getUserList() {
        return userList;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            parseData();
        } catch ( Exception e) {
            e.printStackTrace();
        }
    }
}
