package controller;

import classes.Receipt;
import classes.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class HomeController {

    @FXML
    private AnchorPane rootPane;

    public void shop(MouseEvent mouseEvent) throws IOException {

    }

    public void receipt(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ReceiptScreen.fxml"));
        AnchorPane root = loader.load();

        ReceiptController rC = loader.getController();
        rC.setReceiptLabel(Receipt.readReceipt());

        rootPane.getChildren().setAll(root);
    }

    public void logOutEvent(MouseEvent mouseEvent) throws  IOException {
        User.logOut();
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/LoginScreen.fxml"));
        rootPane.getChildren().setAll(pane);
    }

}
