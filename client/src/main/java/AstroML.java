import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;


public class AstroML extends Application {
    private static final Logger logger = LoggerFactory.getLogger(AstroML.class);

    public static void main(String[] args)  throws IOException{

        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        logger.info("Starting client application");
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Appv2.fxml"));
        primaryStage.setTitle("Deeplearning for Astronomy");
        Scene scene = new Scene(root, 1200, 800);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

