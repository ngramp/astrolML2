import javafx.application.HostServices;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import net.lingala.zip4j.model.FileHeader;
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

/**
 * Created by Graham Perry on 21/08/16.
 *
 * @author Graham Perry
 */
public class KaggleImagesController extends StackPane implements Initializable{
    private static final Logger logger = LoggerFactory.getLogger(KaggleImagesController.class);
    private static final Preferences prefs = Preferences.userRoot().node("Kaggle Images");
    @FXML
    private TextField trainingFileText;
    @FXML
    private TextField testingFileText;
    @FXML
    private Button loadTraining;
    @FXML
    private Button loadTesting;
    @FXML
    private Button testingPreview;
    @FXML
    private Button trainingPreview;
    @FXML
    private FlowPane previewPane;
    @FXML
    private Hyperlink link;
    @FXML
    private ProgressBar previewProgress;
    private File trainingFile;
    private File testingFile;

    KaggleImagesController(){
        logger.info("Trying to load fxml for " + "kaggle-images.fxml");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("kaggle-images.fxml"));
            loader.setController(this);
            loader.setRoot(this);
            loader.load();
        } catch (IOException ex) {
            logger.error(ex.getMessage());
            ex.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupTraining();
        setupTesting();

    }

    private void setupTesting() {
        logger.debug("Setting up testing file chooser");
        testingFileText.setText(prefs.get("Testing file", System.getProperty("user.home")));
        testingFileText.setEditable(false);
        testingFile = new File(prefs.get("Testing file", System.getProperty("user.home")));
        loadTesting.setOnAction(e -> openTestingFile());
        //testingPreview.setOnAction(e -> loadTestingPreview());
        link.setOnAction(e -> openBrowser());
    }

    private void openBrowser() {
        HostServices hostServices = (HostServices) this.getScene().getWindow().getProperties().get("hostServices");
        hostServices.showDocument("https://www.kaggle.com/c/galaxy-zoo-the-galaxy-challenge/data");
    }

//    private void loadTestingPreview() {
//        previewPane.getChildren().clear();
//        if(testingFile.exists()){
//            KaggleImageUtil util = KaggleImageUtil.getInstance();
//            Iterator<BufferedImage> preview = util.getPreview();
//            while(preview.hasNext()){
//                previewPane.getChildren().add(new ImageView(SwingFXUtils.toFXImage(preview.next(),null)));
//            }
//        }
//
//    }

    private void setupTraining() {
        logger.debug("Setting up training file chooser");
        trainingFileText.setText(prefs.get("Training file", System.getProperty("user.home")));
        trainingFileText.setEditable(false);
        trainingFile = new File(prefs.get("Training file", System.getProperty("user.home")));
        loadTraining.setOnAction(e -> openTrainingFile());
        trainingPreview.setOnAction(e -> loadTrainingPreview());
    }
    private class PreviewTask extends Task<Integer>{
        ObservableList<ImageView> data;
        PreviewTask(ObservableList<ImageView> data){
            this.data = data;
        }
        @Override
        protected Integer call() throws Exception {
            if(testingFile.exists()){
                KaggleImageUtil util = KaggleImageUtil.getInstance();
                ArrayList<FileHeader> files = new ArrayList<>(util.previewNames());
                int total = files.size();
                int i = 0;
                for (FileHeader f : files) {
                    BufferedImage bim = util.getImage(f);
                    if(bim != null) {
                        ImageView imgV = new ImageView();
                        logger.info(bim.getHeight() + " " + bim.getWidth());
                        Image im = SwingFXUtils.toFXImage(bim, null);
                        imgV.setImage(im);
                        imgV.setFitHeight(200);
                        imgV.setFitWidth(200);
                        data.add(imgV);
                    }
                    updateProgress(++i, total);
                }
            }
            return null;
        }
    }
    private void loadTrainingPreview() {
        previewPane.getChildren().clear();
        List<ImageView> images = new ArrayList<>();
        ObservableList<ImageView> obImages = FXCollections.observableArrayList(images);
        Task task = new PreviewTask(obImages);
        previewPane.setOrientation(Orientation.HORIZONTAL);
        previewProgress.progressProperty().bind(task.progressProperty());
        task.setOnSucceeded(e -> update(obImages));
        new Thread(task).start();

    }

    private void update(ObservableList<ImageView> obImages) {

        previewPane.getChildren().addAll(obImages);
    }

    private void openTrainingFile() {
        try {
            FileChooser chooser = new FileChooser();
            chooser.setTitle("Select Training File");
            chooser.setInitialFileName(trainingFile.toPath().toString());
            File newFile = chooser.showOpenDialog(this.getScene().getWindow());
            if (newFile != null) {
                trainingFile = newFile;
                trainingFileText.setText(newFile.toPath().toString());
                prefs.put("Training file", trainingFileText.getText());

            }

        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }

    }

    private void openTestingFile() {
        try {
            FileChooser chooser = new FileChooser();
            chooser.setTitle("Select Testing File");
            chooser.setInitialFileName(testingFile.toPath().toString());
            File newFile = chooser.showOpenDialog(this.getScene().getWindow());
            if (newFile != null) {
                testingFile = newFile;
                testingFileText.setText(newFile.toPath().toString());
                prefs.put("Testing file", testingFileText.getText());

            }

        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }

    }
}
