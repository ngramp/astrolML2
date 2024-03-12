import util.JavaFXUtils;
import javafx.fxml.Initializable;
import javafx.scene.control.TitledPane;;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

/**
 * Created by gram on 23/03/16.
 */
public class NewProjectDialog extends TitledPane implements Initializable {
    private static final Logger logger = LoggerFactory.getLogger(NewProjectDialog.class);
    private static final Preferences prefs = Preferences.userNodeForPackage(NewProjectDialog.class);
    public NewProjectDialog() {
        URL fxml = getClass().getResource("/NewProjectDialog.client.fxml");
        JavaFXUtils.loadFxml(fxml, this);

    }

    @Override
    public void initialize(URL url, ResourceBundle resource) {

    }
}
