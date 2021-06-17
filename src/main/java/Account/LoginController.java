package Account;

import Notifications.Notification;
import Notifications.WrongCombination;
import Home.HomeController;
import Rewards.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class LoginController implements Initializable {
    private ArrayList<User> userList;
    private ArrayList<Badge> badges = new ArrayList<>();

    @FXML
    private AnchorPane rootPane;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label incorrectText;

    public void login(ActionEvent Event) throws IOException {
        String[] loginData = {usernameField.getText(),passwordField.getText()};
        User user = Login.checkData(loginData, userList);
        if (user != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/HomeScreen.fxml"));
            AnchorPane root = loader.load();
            HomeController hC = loader.getController();

            for(Badge b : badges){
                if(user.getBadge().getName().equals(b.getName())) {
                    user.setBadge(b);
                }
            }

            hC.setActiveUser(user);
            rootPane.getChildren().setAll(root);
        } else {
            Notification wrong = new WrongCombination();
            incorrectText.setText(wrong.showNotification());
            incorrectText.setVisible(true);
        }
    }

    public void createAccount(MouseEvent mouseEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/CreateAccountScreen.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    private static File productJsonFile() {
        return new File("src/main/resources/user.json");
    }

    public void parseData() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        userList = mapper.readValue(productJsonFile(), new TypeReference<>(){});
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Account.Login.logOut();
        badges.add(new GoldBadge());
        badges.add(new SilverBadge());
        badges.add(new BronzeBadge());
        badges.add(new NoBadge());

        try {
            parseData();
        } catch ( Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<User> getUserList() {
        return userList;
    }
}
