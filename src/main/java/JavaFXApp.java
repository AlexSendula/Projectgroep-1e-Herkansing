import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class JavaFXApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/HomeScreen.fxml"));

        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("ZeroXess");
        primaryStage.setMinHeight(800.0);
        primaryStage.setMinWidth(900.0);
        primaryStage.show();
    }
}
