package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class CreateAccountController {

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


    public void createAccount(MouseEvent mouseEvent) throws IOException {
        if (userNameTextField.getText().equals("test") && passwordField.getText().equals("123") && confirmPasswordField.getText().equals("123")) {
//              TODO: Exceptions maken voor alle ingevulde gegevens. Alle gegevens in bestand opslaan en gebruik maken van een User class. (?)
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/LoginScreen.fxml"));
            rootPane.getChildren().setAll(pane);
        } else {
            incorrectLabel.setVisible(true);
        }
    }
}
