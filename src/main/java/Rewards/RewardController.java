package Rewards;

import Account.User;
import Home.HomeController;
import Rewards.Jobs.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RewardController implements Initializable {
    private User user;
    @FXML
    private AnchorPane rootPane;
    @FXML private Label currentBadge;
    @FXML private Label currentDiscount;
    @FXML private TextArea jobsKnowledge;
    @FXML private ChoiceBox<Job> jobsChoiceBox;

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
        for (Job job : user.getJob()){
            if (job.getName().equals(jobsChoiceBox.getSelectionModel().getSelectedItem().getName())){
                addJobTrue = false;
            }
        }
        if (addJobTrue){
            jobsChoiceBox.getSelectionModel().getSelectedItem().addKnowledge(jobsKnowledge.getText());
            user.addJob(jobsChoiceBox.getSelectionModel().getSelectedItem());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
