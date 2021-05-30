package controller;

import classes.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    private User user;

    @FXML
    private AnchorPane rootPane;

    public void shop(MouseEvent mouseEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/Shop.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    public void setActiveUser(User activeUser) {
        user = activeUser;
    }

    public void receipt(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ReceiptScreen.fxml"));
        AnchorPane root = loader.load();

        ReceiptController rC = loader.getController();
        rC.initData(user);

        rootPane.getChildren().setAll(root);
    }

    public void logOutEvent(MouseEvent mouseEvent) throws  IOException {
        User.logOut();
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/LoginScreen.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
