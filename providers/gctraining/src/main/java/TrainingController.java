import concurrency.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.MyServices;
import spi.DataSource;
import spi.ImageSource;
import spi.NeuralNet;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

/**
 * Created by gram on 22/03/16.
 */
public class TrainingController extends StackPane implements Initializable {
    private static final Logger logger = LoggerFactory.getLogger(TrainingController.class);
    private static final MyServices<DataSource> dataProviders =  MyServices.getProviders(DataSource.class);
    private static final MyServices<ImageSource> imageProviders =  MyServices.getProviders(ImageSource.class);
    private static final MyServices<NeuralNet> neuralProviders =  MyServices.getProviders(NeuralNet.class);
    private static final Preferences prefs = Preferences.userRoot().node("Training Setup");
    private static final Preferences imagePrefs = Preferences.userRoot().node("SDSS Image Data");
    private static final Preferences sourcePrefs = Preferences.userRoot().node("SDSS Object Data");
    @FXML
    TableView<Event> events;
    @FXML
    TableColumn<Event, String> event, time;
    @FXML
    private Button reset, pause, train;
    @FXML
    private AnchorPane output, settings;
    @FXML
    private ProgressBar totalProgress;

    public TrainingController() {
        logger.info("Trying to load fxml for " + "training.fxml");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("training.fxml"));
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
        setupButtons();
        setupOutput();


    }

    private void setupOutput() {

    }

    private void setupButtons() {
        reset.setOnAction(e -> resetAction());
        train.setOnAction(e -> trainAction());
        pause.setOnAction(e -> pauseAction());
    }

    private void pauseAction() {

    }

    private void trainAction() {
        String title = sourcePrefs.get("Source", "DR12 Sky objects");
        DataSource source = dataProviders.getClassConverter().fromString(title);
//        Config config = new Config(
//                imagePrefs.getBoolean("Primary targets", true),
//                source.getObjType()
//        );
//        TrainingTask task = new TrainingTask(source, config);
//        totalProgress.progressProperty().bind(task.progressProperty());
//        ObservableList<Event> threadEvents = task.getEvents();

//        event.setCellValueFactory(
//                new PropertyValueFactory<Event, String>("event"));
//        time.setCellValueFactory(
//                new PropertyValueFactory<Event, String>("time"));
//        events.setItems(threadEvents);
//        new Thread(task).start();


        //TODO: batch train
    }

    private void resetAction() {

    }
}
