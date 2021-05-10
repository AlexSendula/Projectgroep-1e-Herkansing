package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class LoginController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label incorrectText;

    public void login(MouseEvent mouseEvent) throws IOException {
        if (usernameField.getText().equals("abc") && passwordField.getText().equals("123")){
//            TODO: Username en password uit bestand laten lezen. Gebruik maken van login class.
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/HomeScreen.fxml"));
            rootPane.getChildren().setAll(pane);
        } else {
            incorrectText.setVisible(true);
        }
    }

    public void createAccount(MouseEvent mouseEvent) throws IOException {
//TODO: Naar createaccount sturen
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Punt om data op te halen.
    }
}
