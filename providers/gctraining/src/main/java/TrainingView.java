import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.prefs.Preferences;


public class TrainingView extends Application {
    private static final Logger logger = LoggerFactory.getLogger(TrainingView.class);
    private static final Preferences prefs = Preferences.userRoot().node("SDSS Training Data");

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        logger.trace("Starting client application");
        Parent root = FXMLLoader.load(getClass().getResource("/training.fxml"));
        primaryStage.setTitle("Training");
        Scene scene = new Scene(root, 1000, 800);
        primaryStage.setScene(scene);
        //primaryStage.setMaximized(true);
        primaryStage.show();

    }
}
