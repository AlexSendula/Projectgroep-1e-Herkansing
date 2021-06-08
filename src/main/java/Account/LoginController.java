package Account;

import Notifications.Notification;
import Notifications.WrongCombination;
import controller.HomeController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

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
        if (Login.readData(usernameField.getText(),passwordField.getText())){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/HomeScreen.fxml"));
            AnchorPane root = loader.load();
            HomeController hC = loader.getController();
            hC.setActiveUser(new User(usernameField.getText(), passwordField.getText()));
            rootPane.getChildren().setAll(root);
        } else {
            Notification wrong = new WrongCombination();
            incorrectText.setText(wrong.showNotification());
            incorrectText.setVisible(true);
        }
    }

    public void createAccount(MouseEvent mouseEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/CreateAccountScreen.fxml"));
        rootPane.getChildren().setAll(pane);
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Account.Login.logOut();
    }
}
