package Shop;

import Account.User;
import controller.HomeController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProductController implements Initializable {
    private User user;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Label nameValue;

    @FXML
    private Label priceValue;

    @FXML
    private Label stockValue;

    @FXML
    private Label descriptionValue;

    //JavaFX Functions
    @FXML
    void shop(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ShopScreen.fxml"));
        AnchorPane root = loader.load();

        ShopController sC = loader.getController();
        sC.setActiveUser(user);

        rootPane.getChildren().setAll(root);
    }

    public void initData(Product product, User activeUser) {
        user = activeUser;

        nameValue.setText(product.getName());
        priceValue.setText(Double.toString(product.getPrice()));
        stockValue.setText(Long.toString(product.getStock()));
        descriptionValue.setText(product.getDescription());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}