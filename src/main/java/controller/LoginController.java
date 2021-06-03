package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import classes.*;

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
        if (User.getInstance(usernameField.getText()).readData(usernameField.getText(),passwordField.getText())){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/HomeScreen.fxml"));
            AnchorPane root = loader.load();
            HomeController hC = loader.getController();
            hC.setActiveUser(User.getInstance(usernameField.getText()));
            rootPane.getChildren().setAll(root);
        } else {
            incorrectText.setVisible(true);
        }
    }

    public void createAccount(MouseEvent mouseEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/CreateAccountScreen.fxml"));
        rootPane.getChildren().setAll(pane);
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
