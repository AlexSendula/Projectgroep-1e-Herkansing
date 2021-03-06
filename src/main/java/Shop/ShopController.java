package Shop;

import Account.User;
import Receipts.Receipt;
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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.*;

import static java.lang.Math.round;

public class ShopController implements Initializable {
    private User user;
    private List<Product> productData;

    @FXML private AnchorPane rootPane;
    @FXML private TableView<Product> shopTable;
    @FXML private TextField filterField;
    @FXML private TableColumn<Product, String> categoryColumn;
    @FXML private TableColumn<Product, String> nameColumn;
    @FXML private TableColumn<Product, Double> priceColumn;
    @FXML private Label orderPlacedText;
    @FXML private Label totalPriceLabel;
    @FXML private Label cartLabel;
    @FXML private Button currencyButton;
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
    @FXML
    void order(MouseEvent mouseEvent) throws IOException {
        if (user.getShoppingCart().getProduct().size()>0) {
            new Receipt(user).writeReceipt(Receipt.receiptNumber());
            orderPlacedText.setVisible(true);
        }
        user.getShoppingCart().getProduct().clear();
    }

    @FXML
    void clearCart(MouseEvent mouseEvent)throws IOException {
        user.getShoppingCart().getProduct().clear();
        updateTotalPriceLabel();
    }

    @FXML
    void currencyChange(MouseEvent mouseEvent) throws IOException {
        if (cartLabel.getText().contains("???")) {
            cartLabel.setText("Cart: ");
            currencyButton.setText("???");
            totalPriceLabel.setText("" + addDiscount(user.getShoppingCart().getCurrencyAdapterImpl().getTotalPrice(user.getShoppingCart()))+"$");
        } else {
            currencyButton.setText("$");
            updateTotalPriceLabel();
        }
    }


    //IDK, but dont touch please.
    public ShopController() {
    }

    public void initData(User activeUser) {
        user = activeUser;

        updateTotalPriceLabel();
    }

    public void updateTotalPriceLabel(){
        cartLabel.setText("Cart: ???");
        totalPriceLabel.setText(addDiscount(user.getShoppingCart().getTotalPrice()));
    }

    public String addDiscount(double originalPrice){
        double discount = (100-user.getBadge().getDiscountPercentage()) / 100;
        double newPrice = originalPrice * (discount);
        DecimalFormat df = new DecimalFormat(".##");
        return df.format(newPrice);
    }


    //Setting up tableview and populating with data
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            parseData();
        } catch ( Exception e) {
            e.printStackTrace();
        }
        orderPlacedText.setVisible(false);
        //Table setup
        categoryColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("categories"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Product, Double>("price"));

        //Search filter
        productList.addAll(productData);

        FilteredList<Product> filteredList = new FilteredList<>(productList, b -> true);

        filterField.textProperty().addListener(((observableValue, oldValue, newValue) -> {
            filteredList.setPredicate(product -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                for (String c : product.getCategories()) {
                    if (c.toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                }
                return false;
            });
        }));

        SortedList<Product> sortedList = new SortedList<>(filteredList);


        sortedList.comparatorProperty().bind(shopTable.comparatorProperty());

        //Setting table items
//        shopTable.setItems(getProductList());
        shopTable.setItems(sortedList);

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
        for(Product product : productData) {
            products.add(product);
        }
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