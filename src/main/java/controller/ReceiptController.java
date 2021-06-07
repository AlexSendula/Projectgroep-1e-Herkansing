package controller;

import Receipt.Receipt;
import User.User;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ReceiptController implements Initializable {
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
    private ListView<String> receiptsTable;

    @FXML
    public void initData(User activeUser) {
        user = activeUser;

        breadcrumb.setText("Receipts for: " + user.getUsername());

        receiptsTable.setItems(FXCollections.observableArrayList(Receipt.allReceiptsPerUser(user)));
    }

    @FXML
    void back(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/HomeScreen.fxml"));
        AnchorPane root = loader.load();

        HomeController hC = loader.getController();
        hC.setActiveUser(user);

        rootPane.getChildren().setAll(root);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        receiptsTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                receiptLabel.setText(Receipt.readReceipt(newValue));
            }
        });
    }
}
