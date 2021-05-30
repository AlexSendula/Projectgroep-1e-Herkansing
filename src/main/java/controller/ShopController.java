package controller;

import classes.Product;
import classes.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.beans.PropertyVetoException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class ShopController implements Initializable {

    private List<Product> productList;

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

//    @FXML
//    private TableColumn<Product, ?> linkColumn;


    public void gsonParser(MouseEvent mouseEvent) {

    }

    private static File jsonFile() {
        return new File("src/main/java/classes/products.json");
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
    }

    public void logOutEvent(MouseEvent mouseEvent) throws  IOException {
        User.logOut();
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/LoginScreen.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    public void homeEvent (MouseEvent mouseEvent) throws  IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/HomeScreen.fxml"));
        rootPane.getChildren().setAll(pane);
    }
}
