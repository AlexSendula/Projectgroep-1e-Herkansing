package controller;

import classes.Receipt;
import classes.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ReceiptController {
    private User user;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Text breadcrumb;

    @FXML
    private Text receiptLabel;

    @FXML
    private Button backButton;

    @FXML
    void back(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/HomeScreen.fxml"));
        AnchorPane root = loader.load();

        HomeController hC = loader.getController();
        hC.setActiveUser(user);

        rootPane.getChildren().setAll(root);
    }

    public void setActiveUser(User activeUser) {
        user = activeUser;
    }

    public void init() throws FileNotFoundException {
        breadcrumb.setText("Receipts for: " + user.getUsername());
        for(String s : Receipt.allReceiptsPerUser(user)) {
            receiptLabel.setText(s);
        }
    }
}
