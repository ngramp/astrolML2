import javafx.application.HostServices;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.ModelTableViewBuilder;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

/**
 * Created by Graham Perry on 21/08/16.
 *
 * @author Graham Perry
 */
public class KaggleController extends StackPane implements Initializable {

    private static final Logger logger = LoggerFactory.getLogger(KaggleController.class);
    private static final Preferences prefs = Preferences.userRoot().node("Kaggle");
    @FXML
    private TextField fileloc;
    @FXML
    private Button openCSV;
    @FXML
    private Button preview;
    @FXML
    private StackPane tableContainer;
    @FXML
    private Hyperlink link;
    @FXML
    private SplitPane split;
    private File prefFile;

    KaggleController(){
        logger.info("Trying to load fxml for " + "kaggle.fxml");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("kaggle.fxml"));
            loader.setController(this);
            loader.setRoot(this);
            loader.load();
        } catch (IOException ex) {
            logger.error(ex.toString());
            ex.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupFileChooser();
    }

    private void loadPreview() {
        TableView<KaggleObj> table = ModelTableViewBuilder.buildUpon(KaggleObj.class);

        tableContainer.getChildren().add(table);
        Iterator<KaggleObj> iter =  new KaggleGalaxyIndex();
        List<KaggleObj> index = new ArrayList<>();
        while(iter.hasNext()){
            index.add(iter.next());
        }

        ObservableList<KaggleObj> data = FXCollections.observableArrayList(index);
        table.setItems(data);
    }

    private void setupFileChooser() {
        logger.debug("Setting up csv file chooser");
        prefFile = new File(prefs.get("CSV file", System.getProperty("user.home")+"/test.csv"));
        fileloc.setText(prefFile.toString());
        fileloc.setEditable(false);
        openCSV.setOnAction(e -> openFile());
        preview.setOnAction(e -> loadPreview());
        link.setOnAction(e -> openBrowser());
    }

    private void openBrowser() {
        HostServices hostServices = (HostServices) this.getScene().getWindow().getProperties().get("hostServices");
        hostServices.showDocument("https://www.kaggle.com/c/galaxy-zoo-the-galaxy-challenge/data");
    }

    private void openFile() {
        try {
            FileChooser chooser = new FileChooser();
            chooser.setTitle("Select Training File");
            chooser.setInitialFileName(prefFile.toPath().toString());
            chooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("CSV Files", "*.csv"),
                    new FileChooser.ExtensionFilter("Zip Files", "*.zip")
                    );
            File newFile = chooser.showOpenDialog(this.getScene().getWindow());
            if (newFile != null) {
                prefFile = newFile;
                fileloc.setText(newFile.toPath().toString());
                prefs.put("CSV file", fileloc.getText());

            }

        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }


}
