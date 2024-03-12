import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.*;
import spi.*;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

;

/**
 * Controller class for main client
 * Created by Graham Perry on 18/04/16.
 */
public class AppController implements Initializable {
    private static final Logger logger = LoggerFactory.getLogger(AppController.class);
    private static final Preferences prefs = Preferences.userRoot().node("App");
    private static final MyServices<DataSource> dataProviders =  MyServices.getProviders(DataSource.class);
    private static final MyServices<ImageSource> imageProviders =  MyServices.getProviders(ImageSource.class);
    private static final MyServices<NeuralNet> neuralNetProviders =  MyServices.getProviders(NeuralNet.class);
    private static final MyServices<Training> trainingProviders =  MyServices.getProviders(Training.class);
    private static final MyServices<ImageProcessor> imageProcessorProviders =  MyServices.getProviders(ImageProcessor.class);
    @FXML
    private TabPane content;
    @FXML
    private ToolBar statusbar;
    @FXML
    private ChoiceBox<ImageSource> imageDataChoice;
    @FXML
    private ChoiceBox<DataSource> objectDataChoice;
    @FXML
    private ChoiceBox<NeuralNet> nnChoice;
    @FXML
    private ChoiceBox<Training> trainingChoice;
    @FXML
    private ChoiceBox<ImageProcessor> imageProcessorChoice;
    @FXML
    private Button openNN;
    @FXML
    private Button openTraining;
    @FXML
    private Button openObjectData;
    @FXML
    private Button openImageData;
    @FXML
    private Button openImageProcessor;
    @FXML
    private Label nnDesc;
    @FXML
    private Label idDesc;
    @FXML
    private Label ipDesc;
    @FXML
    private Label odDesc;
    @FXML
    private Label trDesc;

    @Override
    public void initialize(URL url, ResourceBundle resource) {
        assert content != null : "content has not been loaded";
        //dsPane.setExpanded(true);
        this.content.getSelectionModel().clearSelection();
        // By default, select first tab and load its content.
        this.content.getSelectionModel().selectFirst();

        loadObjectDataPlugins();
        loadImagePlugins();
        loadImageProcessorPlugins();
        loadNNPlugins();
        loadTrainingPlugins();
    }

    /**
     * Loads service providers for Neural Networks
     */
    private void loadNNPlugins() {

        nnChoice.setConverter(neuralNetProviders.getClassConverter());
        NeuralNet current = nnChoice.getConverter().fromString(prefs.get("NN Provider", neuralNetProviders.getList().get(0).getTitle()));
        nnChoice.setItems(FXCollections.observableArrayList(neuralNetProviders.getList()));

        nnChoice.setValue(current);
        nnChoice.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            prefs.put("NN Provider", nnChoice.getConverter().toString(newValue));
            openNN.setOnAction(e -> addTab(newValue.getTitle(),newValue.getFX()));
            nnDesc.setText(newValue.getDescription());
        });
        nnDesc.setText(current.getDescription());
        openNN.setOnAction(e -> addTab(current.getTitle(),current.getFX()));
    }

    /**
     * Loads available training service providers
     */
    private void loadTrainingPlugins() {
        trainingChoice.setConverter(trainingProviders.getClassConverter());
        Training current = trainingChoice.getConverter().fromString(prefs.get("Training Provider", trainingProviders.getList().get(0).getTitle()));
        trainingChoice.setItems(FXCollections.observableArrayList(trainingProviders.getList()));
        trainingChoice.setValue(current);
        trainingChoice.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            prefs.put("Training Provider", trainingChoice.getConverter().toString(newValue));
            openTraining.setOnAction(e -> addTab(newValue.getTitle(),newValue.getFX()));
            trDesc.setText(newValue.getDescription());
        });
        trDesc.setText(current.getDescription());
        openTraining.setOnAction(e -> addTab(current.getTitle(),current.getFX()));
    }

    /**
     * Loads Image source service providers
     */
    private void loadImagePlugins() {
        imageDataChoice.setConverter(imageProviders.getClassConverter());
        ImageSource current = imageDataChoice.getConverter().fromString(prefs.get("Image Provider", imageProviders.getList().get(0).getTitle()));
        imageDataChoice.setItems(FXCollections.observableArrayList(imageProviders.getList()));
        imageDataChoice.setValue(current);
        imageDataChoice.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            prefs.put("Image Provider", imageDataChoice.getConverter().toString(newValue));
            openImageData.setOnAction(e -> addTab(newValue.getTitle(),newValue.getFX()));
            idDesc.setText(newValue.getDescription());
        });
        idDesc.setText(current.getDescription());
        openImageData.setOnAction(e -> addTab(current.getTitle(),current.getFX()));
    }

    /**
     * Loads image processing service providers
     */
    private void loadImageProcessorPlugins() {
        imageProcessorChoice.setConverter(imageProcessorProviders.getClassConverter());
        ImageProcessor current = imageProcessorChoice.getConverter().fromString(prefs.get("Image Processor Provider", imageProcessorProviders.getList().get(0).getTitle()));
        imageProcessorChoice.setItems(FXCollections.observableArrayList(imageProcessorProviders.getList()));
        imageProcessorChoice.setValue(current);
        imageProcessorChoice.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            prefs.put("Image Processor Provider", imageProcessorChoice.getConverter().toString(newValue));
            openImageProcessor.setOnAction(e -> addTab(newValue.getTitle(),newValue.getFX()));
            ipDesc.setText(newValue.getDescription());
        });
        ipDesc.setText(current.getDescription());
        openImageProcessor.setOnAction(e -> addTab(current.getTitle(),current.getFX()));
    }

    /**
     * Loads data source service providers
     */
    private void loadObjectDataPlugins() {
        objectDataChoice.setConverter(dataProviders.getClassConverter());
        DataSource current = objectDataChoice.getConverter().fromString(prefs.get("Data Provider", dataProviders.getList().get(0).getTitle()));
        objectDataChoice.setItems(FXCollections.observableArrayList(dataProviders.getList()));
        objectDataChoice.setValue(current);
        objectDataChoice.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            prefs.put("Data Provider", objectDataChoice.getConverter().toString(newValue));
            openObjectData.setOnAction(e -> addTab(newValue.getTitle(),newValue.getFX()));
            odDesc.setText(newValue.getDescription());
        });
        odDesc.setText(current.getDescription());
        openObjectData.setOnAction(e -> addTab(current.getTitle(),current.getFX()));
    }

    //add tabs to content area
    public void addTab(String title, Parent content) {
        Tab tab = new Tab(title, content);
        this.content.getTabs().add(tab);
        this.content.getSelectionModel().select(tab);

    }

}
