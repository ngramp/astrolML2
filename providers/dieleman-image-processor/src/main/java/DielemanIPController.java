import javafx.concurrent.Service;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

/**
 * Created by Graham Perry on 06/09/16.
 *
 * @author Graham Perry
 */
public class DielemanIPController  extends StackPane implements Initializable {
    private static final Logger logger = LoggerFactory.getLogger(DielemanIPController.class);
    private static final Preferences prefs = Preferences.userRoot().node("DielemanImageProcessor");
    @FXML
    private TextField loadPath;
    @FXML
    private Button openImageLoc;
    @FXML
    private Button previewButton;
    @FXML
    private FlowPane previewArea;
    @FXML
    private ProgressBar previewProgress;
    @FXML
    private ImageView original;
    private File imageLocation;
    DielemanIPController(){
        logger.info("Trying to load fxml for " + "DielemanImageProcessor.fxml");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/DielemanImageProcessor.fxml"));
            loader.setController(this);
            loader.setRoot(this);
            loader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
            logger.error(ex.getMessage());
        }
        //prefs.clear();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            setup();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setup() throws IOException {
        //handle save location
        logger.debug("Setting up source");
        imageLocation = new File(prefs.get("input file", System.getProperty("user.home")));
        loadPath.setText(imageLocation.toString());
        loadPath.setEditable(false);
        openImageLoc.setOnAction(e -> openFile());
        //handle download tasks
        previewButton.setOnAction(e -> getPreview());
        if(!imageLocation.toString().equals(System.getProperty("user.home"))){
            BufferedImage i = ImageIO.read(imageLocation);
            original.setImage(SwingFXUtils.toFXImage(i, null));
        }
    }
    private void openFile() {
        try {
            FileChooser chooser = new FileChooser();
            chooser.setTitle("Select Sample File");
            chooser.setInitialFileName(imageLocation.toPath().toString());
            chooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("JPG Files", "*.jpg")
            );
            File newFile = chooser.showOpenDialog(this.getScene().getWindow());
            if (newFile != null) {
                imageLocation = newFile;
                loadPath.setText(newFile.toPath().toString());
                prefs.put("input file", loadPath.getText());
                BufferedImage i = ImageIO.read(newFile);
                original.setImage(SwingFXUtils.toFXImage(i, null));

            }

        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }


    private void getPreview() {
        logger.info("getting sample preview");
        try {
            BufferedImage in = ImageIO.read(imageLocation);
            //lock UI elements;
            Service<ArrayList<BufferedImage>> service = new DielemanIP(in);
            service.setOnSucceeded(t -> upPreviewArea(service.getValue()));
            previewProgress.progressProperty().bind(service.progressProperty());
            logger.info("starting sample service");
            service.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void upPreviewArea(ArrayList<BufferedImage> images) {
        logger.info("updating preview");
        previewArea.getChildren().clear();
        previewArea.setVgap(8);
        previewArea.setHgap(4);
        for (BufferedImage i : images) {
            HBox row = new HBox();
            row.setSpacing(8);
            ImageView view = new ImageView(SwingFXUtils.toFXImage(i, null));
            row.getChildren().add(view);
            previewArea.getChildren().add(row);

        }
    }
}
