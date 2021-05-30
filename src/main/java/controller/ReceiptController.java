package controller;

import classes.Receipt;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class ReceiptController {

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Text receiptLabel;

    public void setReceiptLabel(String receipt) {
        receiptLabel.setText(receipt);
    }
}
