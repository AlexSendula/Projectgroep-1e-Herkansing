package Shop;

import Account.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import controller.HomeController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class ShopController implements Initializable {
    private User user;
    private List<Product> productList;

    @FXML
    private Button homeButton;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private TableView<Product> shopTable;

    @FXML
    private TableColumn<Product, String> categoryColumn;

    @FXML
    private TableColumn<Product, String> nameColumn;

    @FXML
    private TableColumn<Product, Double> priceColumn;


    //JavaFX general Functions
    @FXML
    void home(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/HomeScreen.fxml"));
        AnchorPane root = loader.load();

        HomeController hC = loader.getController();
        hC.setActiveUser(user);

        rootPane.getChildren().setAll(root);
    }

    //IDK, but dont touch please.
    public ShopController() {
    }

    public void initData(User activeUser) {
        user = activeUser;
    }

//    public void gsonParser(MouseEvent mouseEvent) {
//    }


    //Setting up tableview and populating with data
    //Table setup
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            parseData();
        } catch ( Exception e) {
            e.printStackTrace();
        }

        categoryColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("categories"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Product, Double>("price"));

        shopTable.setItems(getProduct());

        shopTable.setOnMouseClicked(mouseEvent -> {
            try {
                getDetailedProductView();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    //Load data
    private static File jsonFile() {
        return new File("src/main/resources/products.json");
    }

    public void parseData() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        productList = mapper.readValue(jsonFile(), new TypeReference<>(){});
    }

    public ObservableList<Product> getProduct() {
        ObservableList<Product> products = FXCollections.observableArrayList();
        for(Product product : productList) { products.add(product); }
        return products;
    }

    public void setActiveUser(User activeUser) {
        user = activeUser;
    }

    //Table functions
    private void getDetailedProductView() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ProductScreen.fxml"));
        AnchorPane root = loader.load();

        ProductController pC = loader.getController();
        pC.initData(shopTable.getSelectionModel().getSelectedItem(), user);

        rootPane.getChildren().setAll(root);
    }
}
