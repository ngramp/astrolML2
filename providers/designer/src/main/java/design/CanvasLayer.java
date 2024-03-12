package design;

import convnet.ConvolutionalLayer;
import convnet.Layer;
import convnet.Neuron;
import util.JavaFXUtils;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Circle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

;

/**
 * Created by Graham Perry on 21/04/16.
 *
 * @author Graham Perry
 */
public class CanvasLayer extends VBox implements Initializable {
    private static final Logger logger = LoggerFactory.getLogger(CanvasLayer.class);
    private static final Preferences prefs = Preferences.userRoot().node("Designer");
    private final Layer layer;
    @FXML
    private TitledPane parameters, definition;
    @FXML
    private Box layerBox;
    @FXML
    private Circle output, input;
    @FXML
    private Spinner<Integer> paddingSpin, partialSumSpin, groupsSpin, channelsSpin, strideSpin, filtersSpin, filterSizeSpin, initialWeightSpin, initialBiasSpin;
    @FXML
    private CheckBox sharedBiasCheck;
    @FXML
    private ChoiceBox<Neuron> neuronChoice;
    @FXML
    private HBox partialSum, padding, groups, channels, stride, filters, filterSize, initialWeight, initialBias, sharedBias;
    @FXML
    private VBox neuron;
    @FXML
    private Spinner<Integer> biasRateSpin, biasMomentumSpin, weightRateSpin, l2decaySpin, weightMomentumSpin;
    @FXML
    private HBox biasRate, biasMomentum, weightRate, l2decay, weightMomentum;

    public CanvasLayer(Layer layer) {
        logger.trace("Constructing canvas layer");
        URL fxml = getClass().getResource("/fxml/canvas-layer.fxml");
        JavaFXUtils.loadFxml(fxml, this);

        this.layer = layer;

    }

    public CanvasLayer(ConvolutionalLayer layer) {
        logger.trace("Constructing canvas layer");
        URL fxml = getClass().getResource("/fxml/canvas-layer.fxml");
        JavaFXUtils.loadFxml(fxml, this);

        this.layer = layer;

    }

    private void configureControl() {
        //TODO: set up controls configured for layer type
    }

    private void bindControls() {
        //TODO bind the value of the controls to the properties of layer
        //layer.bindControls(){

        //}
        ConvolutionalLayer conv = (ConvolutionalLayer) layer;
        partialSumSpin.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(10, 1));
        Bindings.bindBidirectional(partialSumSpin.getValueFactory().valueProperty(), conv.partialSumProperty());

    }

    public void hideMenus() {
        definition.setVisible(false);
        parameters.setVisible(false);
    }

    private void setupBox() {
        logger.trace("Seting up layer visual");
        PhongMaterial material1 = new PhongMaterial();
        material1.setDiffuseColor(Color.GRAY);
        material1.setSpecularColor(Color.LIGHTGRAY);
        material1.setSpecularPower(5.0);
        layerBox.setMaterial(material1);

        layerBox.setOnMouseEntered(me -> {
            PhongMaterial material11 = new PhongMaterial();
            material11.setDiffuseColor(Color.BLUE.deriveColor(3, 2, 2, 1));
            material11.setSpecularColor(Color.LIGHTBLUE.deriveColor(2, 2, 2, 1));
            material11.setSpecularPower(10.0);
            layerBox.setMaterial(material11);
        });
        layerBox.setOnMouseExited(me -> {
            PhongMaterial material11 = new PhongMaterial();
            material1.setDiffuseColor(Color.GRAY);
            material1.setSpecularColor(Color.LIGHTGRAY);
            material1.setSpecularPower(5.0);
            layerBox.setMaterial(material11);
            //logger.info(Platform.isSupported(ConditionalFeature.SCENE3D));

        });
        layerBox.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.SECONDARY) {
                if (definition.isVisible() || parameters.isVisible()) {
                    definition.setVisible(false);
                    parameters.setVisible(false);
                } else {
                    definition.setVisible(true);
                    parameters.setVisible(true);
                }
            } else {
                definition.setVisible(false);
                parameters.setVisible(false);
            }

        });

    }

    @Override
    public void initialize(URL url, ResourceBundle resource) {
        setupMenus();
        setupBox();

    }

    private void setupMenus() {
        logger.trace("Trying to load layer menu fxml");

        //definition = new LayerDefinitionControl();
        //parameters = new LayerParametersControl();
        definition.setVisible(false);
        parameters.setVisible(false);

    }

}
