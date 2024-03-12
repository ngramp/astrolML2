import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

/**
 * Created by gram on 23/03/16.
 */
public class SidebarItem extends VBox implements Initializable {
    private static final Logger logger = LoggerFactory.getLogger(SidebarItem.class);
    private final Preferences prefs;
    @FXML
    private Label titleText;
    @FXML
    private Button editButton;
    @FXML
    private VBox status;
    private AppController controller;
    private String contentFXML;
    private String title;


    SidebarItem(AppController controller, String title, String contentFxml) {
        this.title = title;
        this.contentFXML = contentFxml;
        this.prefs = Preferences.userRoot().node(title);
        this.controller = controller;

        URL fxml = getClass().getResource("/fxml/sidebaritem.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(fxml);
        fxmlLoader.setRoot(this);
        //do not set controller in client.contentFXML as it creates stack overflow with this pattern.
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();

        } catch (FileNotFoundException exception) {
            logger.error("Failed to find file : /fxml/sidebaritem.fxml");
        } catch (IOException exception) {
            logger.error("Failed to load FXML in : /fxml/sidebaritem.fxml due to " + exception.getCause());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resource) {
        //assert appController != null : "it'snull";
        setTitle(title);
        prefsUpdate();
        editButton.setOnAction(e -> {
            controller.addTab(this.getTitle(),this.getContent());
        });
        prefs.addPreferenceChangeListener(e -> prefsUpdate());
    }

    private void prefsUpdate() {
        Platform.runLater(() -> {
            try {
                status.getChildren().clear();
                String[] keys = prefs.keys();
                for (String key : keys) {
                    String value = prefs.get(key, "No value for this key");
                    Text entry = new Text(key + ": " + value);
                    status.getChildren().add(entry);
                }
            } catch (BackingStoreException e) {
                e.printStackTrace();
            }
        });

    }


    private String getTitle() {
        return this.title;
    }

    private void setTitle(String title) {
        this.title=title;
        this.titleText.setText(title);
    }




    public Parent getContent() {
        Parent content = null;
        try {
            content = FXMLLoader.load(getClass().getResource(contentFXML));
        } catch (IOException exception) {
            logger.error("Failed to load FXML in : " + contentFXML+ " due to " + exception.getCause());
            throw new RuntimeException(exception);
        }
        return content;
    }


}
