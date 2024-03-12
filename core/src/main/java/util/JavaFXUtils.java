package util;

import javafx.fxml.FXMLLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

/**
 * Created by gram on 27/03/16.
 */
public class JavaFXUtils {
    private static final Logger logger = LoggerFactory.getLogger(JavaFXUtils.class);

    public static void loadFxml(URL fxml, Object context) {

        FXMLLoader fxmlLoader = new FXMLLoader(fxml);
        fxmlLoader.setRoot(context);
        //do not set controller in client.fxml as it creates stack overflow with this pattern.
        fxmlLoader.setController(context);

        try {
            fxmlLoader.load();

        } catch (FileNotFoundException exception) {
            logger.error("Failed to find file :" + fxml.toString());
            throw new RuntimeException(exception);
        } catch (IOException exception) {
            logger.error("Failed to load fxml in :" + fxml.toString() + " due to " + exception.getCause());
            throw new RuntimeException(exception);
        }
    }
}
