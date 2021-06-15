package Rewards;

import Account.User;
import Home.HomeController;
import Rewards.Jobs.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class RewardController implements Initializable {
    private User user;
    @FXML
    private AnchorPane rootPane;
    @FXML private Label currentBadge;
    @FXML private Label currentDiscount;
    @FXML private TextArea jobsKnowledge;
    @FXML private ChoiceBox<Job> jobsChoiceBox;
    private ArrayList<User> userList = new ArrayList<>();
    private ArrayList<Badge> badges = new ArrayList<>();

    public void initData(User activeUser) {
        this.user = activeUser;
        currentBadge.setText(user.getBadge().getName());
        currentDiscount.setText(user.getBadge().getDiscountPercentage() +"%");
    }

    @FXML
    void home(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/HomeScreen.fxml"));
        AnchorPane root = loader.load();
        HomeController hC = loader.getController();
        hC.setActiveUser(user);
        rootPane.getChildren().setAll(root);
    }

    @FXML
    void submitKnowledge(MouseEvent mouseEvent) throws IOException {
        boolean addJobTrue = true;
        int points = 7;
        for (Job job : user.getJob()){
            if (job.getName().equals(jobsChoiceBox.getSelectionModel().getSelectedItem().getName())){
                addJobTrue = false;
            }
            points--;
        }

        if (addJobTrue){
            for(User u : userList) {
                if(points < 2) {
                    u.setBadge(new GoldBadge());
                }
                else if(points < 4) {
                    u.setBadge(new SilverBadge());
                }
                else if(points < 6) {
                    u.setBadge(new BronzeBadge());
                }
            }
            jobsChoiceBox.getSelectionModel().getSelectedItem().addKnowledge(jobsKnowledge.getText());
            user.addJob(jobsChoiceBox.getSelectionModel().getSelectedItem());
        }
    }

    private static File userJsonFile() {
        return new File("src/main/resources/user.json");
    }

    public void parseData() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        userList = mapper.readValue(userJsonFile(), new TypeReference<>(){});
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //parseData();
        try {
            parseData();
        } catch ( Exception e) {
            e.printStackTrace();
        }

        jobsChoiceBox.getItems().add(new Baker());
        jobsChoiceBox.getItems().add(new Carpenter());
        jobsChoiceBox.getItems().add(new Dentist());
        jobsChoiceBox.getItems().add(new Doctor());
        jobsChoiceBox.getItems().add(new Farmer());
        jobsChoiceBox.getItems().add(new Pirate());
        jobsChoiceBox.getItems().add(new Plumber());
        jobsChoiceBox.getItems().add(new Police());
        jobsChoiceBox.getItems().add(new Ranger());
        jobsChoiceBox.getItems().add(new Teacher());
    }
}
