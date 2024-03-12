import javafx.concurrent.Service;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import model.Sample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

;


/**
 * Created by gram on 22/03/16.
 */
public class ImagesController extends StackPane implements Initializable {
    private static final Logger logger = LoggerFactory.getLogger(ImagesController.class);
    private static final Preferences prefs = Preferences.userRoot().node("Casjobs");
    @FXML
    private StackPane imageFetcher;
    @FXML
    private TextField imgHeightText, imgWidthText;
    @FXML
    private TextField savePath,maxTries,maxLum,maxP,startSize,stepSize;
    @FXML
    private Button openSaveLoc;
    @FXML
    private Button previewButton;
    @FXML
    private VBox imageP;
    @FXML
    private ProgressBar previewProgress;

    private FlowPane currentP;


    private File saveLocation;

    public ImagesController() {
        logger.info("Trying to load fxml for " + "CnnConfig.fxml");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Images.fxml"));
            loader.setController(this);
            loader.setRoot(this);
            loader.load();
        } catch (IOException ex) {
            logger.error(ex.toString());
            ex.printStackTrace();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resource) {
        setupSource();
        setupPreview();
        setupAlg();

    }

    private void setupAlg() {
        maxTries.setText(Integer.toString(prefs.getInt("maxTries",20)));
        maxTries.textProperty().addListener((observable, oldValue, newValue) -> {
            prefs.putInt("maxTries", Integer.parseInt(newValue));
        });
        maxLum.setText(Float.toString(prefs.getFloat("maxLum",25.0f)));
        maxLum.textProperty().addListener((observable, oldValue, newValue) -> {
            prefs.putFloat("maxLum", Float.parseFloat(newValue));
        });
        maxP.setText(Double.toString(prefs.getDouble("maxP",25.0)));
        maxP.textProperty().addListener((observable, oldValue, newValue) -> {
            prefs.putDouble("maxP", Double.parseDouble(newValue));
        });
        startSize.setText(Float.toString(prefs.getFloat("startSize",0.01f)));
        startSize.textProperty().addListener((observable, oldValue, newValue) -> {
            prefs.putFloat("startSize", Float.parseFloat(newValue));
        });
        stepSize.setText(Float.toString(prefs.getFloat("stepSize",0.01f)));
        stepSize.textProperty().addListener((observable, oldValue, newValue) -> {
            prefs.putFloat("stepSize", Float.parseFloat(newValue));
        });

    }

    private void setupPreview() {
        logger.debug("Setting up preview");
        previewButton.setOnAction(e -> getPreview());
    }




    private void setupSource() {
        //handle save location
        logger.debug("Setting up source");
        savePath.setText(prefs.get("Save path", System.getProperty("user.home")));
        savePath.setEditable(false);
        saveLocation = new File(prefs.get("Save path", System.getProperty("user.home")));
        openSaveLoc.setOnAction(e -> openFileLocation());

        //handle download tasks
        previewButton.setOnAction(e -> getPreview());

        imgWidthText.setText(Integer.toString(prefs.getInt("Width", 400)));
        imgWidthText.textProperty().addListener((observable, oldValue, newValue) -> {
            prefs.putInt("Width", Integer.parseInt(newValue));
        });
        imgHeightText.setText(Integer.toString(prefs.getInt("Height", 400)));
        //update prefs on change
        imgHeightText.textProperty().addListener((observable, oldValue, newValue) -> {
            prefs.putInt("Height", Integer.parseInt(newValue));
        });
    }

    private void openFileLocation() {
        try {
            DirectoryChooser chooser = new DirectoryChooser();
            chooser.setTitle("Select Save Location");
            //TODO:get project base directory
            chooser.setInitialDirectory(new File(prefs.get("Save path", System.getProperty("user.home"))));
            File file = chooser.showDialog(imageFetcher.getScene().getWindow());
            if (file != null) {
                saveLocation = file;
                savePath.setText(saveLocation.toPath().toString());
                prefs.put("Save path", savePath.getText());

            }

        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }

    }

    private List<Sample> buildSample() {
        logger.debug("building sample");
        List<Sample> sample = new ArrayList<>();
        float scale = (float) 0.1;
        sample.add(new Sample("274-51913-230", 159.815, -0.655, scale));
        sample.add(new Sample("275-51910-275", 161.051, 0.152, scale));
        sample.add(new Sample("275-51910-525", 161.739, 0.893, scale));
        sample.add(new Sample("276-51909-19", 164.090, -0.889, scale));
        sample.add(new Sample("278-51900-39", 168.470, 0.004, scale));
        sample.add(new Sample("278-51900-112", 168.092, -0.255, scale));
        sample.add(new Sample("278-51900-225", 167.091, -0.216, scale));
        sample.add(new Sample("278-51900-430", 167.114, 0.249, scale));
        sample.add(new Sample("279-51984-456", 168.956, 0.860, scale));
        sample.add(new Sample("279-51984-520", 169.472, -0.007, scale));
        sample.add(new Sample("281-51614-230", 171.109, -0.427, scale));
        sample.add(new Sample("282-51658-167", 173.898, -0.585, scale));
        sample.add(new Sample("285-51930-309", 178.908, -0.771, scale));
        sample.add(new Sample("286-51999-359", 180.271, 0.114, scale));
        sample.add(new Sample("288-52000-173", 184.837, -0.242, scale));
        sample.add(new Sample("349-51699-582", 255.537, 64.206, scale));
        sample.add(new Sample("353-51703-328", 255.737, 60.563, scale));
        sample.add(new Sample("353-51703-365", 256.157, 60.585, scale));
        sample.add(new Sample("355-51788-167", 258.984, 57.238, scale));
        sample.add(new Sample("355-51788-563", 260.121, 58.797, scale));
        sample.add(new Sample("358-51818-349", 260.930, 57.007, scale));
        sample.add(new Sample("387-51791-72", 0.744, 0.142, scale));
        sample.add(new Sample("389-51795-481", 3.874, 0.640, scale));
        sample.add(new Sample("390-51900-196", 5.183, -0.440, scale));
        sample.add(new Sample("390-51900-464", 5.432, 0.296, scale));
        return sample;
    }

    private void getPreview() {
        logger.info("getting sample preview");
        newGroup();
        int width = prefs.getInt("Width", 400);
        int height = prefs.getInt("Height", 400);
        List<Sample> samples = buildSample();
        Service<ArrayList<BufferedImage>> service = new GetSamplesService(this,samples, width, height);
        //lock UI elements;
        //service.setOnSucceeded(t -> updateSampleView(service.getValue()));
        previewProgress.progressProperty().bind(service.progressProperty());
        logger.info("starting sample service");
        service.start();

    }
    void updateSampleView(BufferedImage image) {
        logger.info("updating preview");
        ImageView img = new ImageView(SwingFXUtils.toFXImage(image, null));
        img.setFitHeight(100);
        img.setFitWidth(100);
        currentP.getChildren().add(img);
    }


    public void newGroup() {
        currentP = new FlowPane();
        currentP.setOrientation(Orientation.HORIZONTAL);
        currentP.setMaxWidth(Double.MAX_VALUE);
        currentP.setPrefWrapLength(1000);
        currentP.setVgap(4);
        currentP.setHgap(4);
        imageP.getChildren().add(currentP);
    }
}
