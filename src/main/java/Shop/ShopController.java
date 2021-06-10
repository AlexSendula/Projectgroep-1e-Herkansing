package Shop;

import Account.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import Home.HomeController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class ShopController implements Initializable {
    private User user;
    private List<Product> productData;

    @FXML private AnchorPane rootPane;
    @FXML private TableView<Product> shopTable;
    @FXML private TextField filterField;
    @FXML private TableColumn<Product, String> categoryColumn;
    @FXML private TableColumn<Product, String> nameColumn;
    @FXML private TableColumn<Product, Double> priceColumn;
    private ObservableList<Product> productList = FXCollections.observableArrayList();


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


    //Setting up tableview and populating with data
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //parseData();
        try {
            parseData();
        } catch ( Exception e) {
            e.printStackTrace();
        }

        //Table setup
        categoryColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("categories"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Product, Double>("price"));

        shopTable.setItems(getProductList());

        //On click mouse event
        shopTable.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getClickCount() == 2) {
                try {
                    getDetailedProductView();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    //Load table data
    private static File productJsonFile() {
        return new File("src/main/resources/products.json");
    }

    public void parseData() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        productData = mapper.readValue(productJsonFile(), new TypeReference<>(){});
    }

    public ObservableList<Product> getProductList() {
        ObservableList<Product> products = FXCollections.observableArrayList();
        for(Product product : productData) { products.add(product); }
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