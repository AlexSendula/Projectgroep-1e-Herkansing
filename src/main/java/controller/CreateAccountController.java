package controller;

import Users.CreateAccount;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.DatePicker;
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
    @FXML
    private DatePicker birthDay;


    public void createAccount(MouseEvent mouseEvent) throws IOException {
        String[] userInformation = {firstNameTextField.getText(), lastNameTextField.getText(), String.valueOf(birthDay.getValue()), userNameTextField.getText(), passwordField.getText(), confirmPasswordField.getText(), emailTextField.getText()};
        if (!new CreateAccount().createAccount(userInformation)){
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/LoginScreen.fxml"));
            rootPane.getChildren().setAll(pane);
        } else {
            incorrectLabel.setVisible(true);
        }
    }

    public void backButton(MouseEvent mouseEvent) throws  IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/LoginScreen.fxml"));
        rootPane.getChildren().setAll(pane);
    }

}
