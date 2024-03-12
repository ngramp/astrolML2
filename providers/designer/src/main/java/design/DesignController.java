package design;

import design.canvas.CanvasGroup;
import design.canvas.SceneGestures;
import convnet.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.input.*;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

/**
 * Created by Graham Perry on 22/03/16.
 */

public class DesignController extends StackPane implements Initializable {
    private static final DataFormat SERIALIZED_MIME_TYPE = new DataFormat("application/x-java-serialized-object");
    private static final Logger logger = LoggerFactory.getLogger(DesignController.class);
    private static final Preferences prefs = Preferences.userRoot().node("Designer");
    private final double SCALE_DELTA = 1.1;
    List<CanvasLayer> allCanvasLayers;
    @FXML
    private TitledPane neurPane, normPane, costPane;
    @FXML
    private StackPane canvasArea;
    private List<Layer> neuronLayers;
    private List<Layer> normLayers;
    private List<Layer> costLayers;
    private CanvasGroup canvas;
    private Random random;

    public DesignController() {
        logger.trace("Constructing CNNConfig");
        logger.info("Trying to load fxml for " + "CnnConfig.fxml");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/CnnConfig.fxml"));
            loader.setController(this);
            loader.setRoot(this);
            loader.load();
        } catch (IOException ex) {
            logger.error(ex.toString());
            ex.printStackTrace();
        }
        random = new Random();
        allCanvasLayers = new ArrayList<>();
    }

    @Override
    public void initialize(URL url, ResourceBundle resource) {
        logger.trace("Initialising CNNConfig");
        assert neurPane != null;
        assert normPane != null;
        assert costPane != null;
        assert canvasArea != null;
        setupCanvas();
        setupLayerTypes();
        setupLibrary();


    }

    private void setupLibrary() {
        logger.trace("Setting up CNN Library");
        ListView<Layer> neurList = new ListView<>();
        ListView<Layer> costList = new ListView<>();
        ListView<Layer> normList = new ListView<>();
        neurList.setCellFactory(list -> getCell());
        costList.setCellFactory(list -> getCell());
        normList.setCellFactory(list -> getCell());

        neurList.getItems().addAll(neuronLayers);
        costList.getItems().addAll(costLayers);
        normList.getItems().addAll(normLayers);
        neurPane.setContent(neurList);
        costPane.setContent(costList);
        normPane.setContent(normList);

    }

    private ListCell<Layer> getCell() {
        ListCell<Layer> cell = new ListCell<Layer>() {
            @Override
            public void updateItem(Layer item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                    setText(null);
                } else {
                    Rectangle rect = new Rectangle(20, 20);
                    if (item != null) {
                        rect.setFill(getRandomColor());
                        setGraphic(rect);
                    }
                    setText(item.getType());
                }
            }
        };
        cell.setOnDragDetected(event ->
                {
                    if (!cell.isEmpty()) {
                        Dragboard db = cell.startDragAndDrop(TransferMode.COPY);
                        ClipboardContent cc = new ClipboardContent();
                        cc.put(SERIALIZED_MIME_TYPE, cell.getItem());
                        db.setContent(cc);
                        event.consume();
                    }
                }
        );
        return cell;
    }

    private void setupLayerTypes() {
        neuronLayers = new ArrayList<>();
        normLayers = new ArrayList<>();
        costLayers = new ArrayList<>();
        neuronLayers.add(new NeuronLayer());
        neuronLayers.add(new WeightedNeuronLayer());
        neuronLayers.add(new LocallyConnectedLayer());
        neuronLayers.add(new FullyConnectedLayer());
        neuronLayers.add(new ConvolutionalLayer());
        normLayers.add(new LocalPoolingLayer());
        normLayers.add(new ElementwiseMaxLayer());
        normLayers.add(new ElementwiseSumLayer());
        normLayers.add(new LocalContrastNormalisationLayer());
        normLayers.add(new LocalResponseNormalisationLayer());
        normLayers.add(new SoftmaxLayer());
        costLayers.add(new SumOfSquaresCostLayer());
        costLayers.add(new LogisticRegressionCostLayer());
    }

    //The canvas consists of grouped Canvas and Pane objects. 
    // Regular shapes are manipulated on the Pane, grid and more complex drawings on the canvas
    private void setupCanvas() {
        // create canvas
        canvas = new CanvasGroup();
        canvas.setTranslateX(0);
        canvas.setTranslateY(0);
        canvas.addGrid();
        // handle zoom and pan events
        canvas.setOnScroll(event -> {
            event.consume();

            if (event.getDeltaY() == 0) {
                return;
            }

            double scaleFactor =
                    (event.getDeltaY() > 0)
                            ? SCALE_DELTA
                            : 1 / SCALE_DELTA;

            canvas.setScaleX(canvas.getScaleX() * scaleFactor);
            canvas.setScaleY(canvas.getScaleY() * scaleFactor);
        });

        //set up ScrollPane container
        ScrollPane outer = new ScrollPane();
        outer.setFitToHeight(true);
        outer.setFitToWidth(true);
        outer.setPrefViewportHeight(500);
        outer.setPrefViewportWidth(500);
        //outer.setMouseTransparent(true);
        outer.setPannable(true);
        //stop scrollpane stealing scroll event
        outer.addEventFilter(ScrollEvent.SCROLL, event -> {
            if (event.getDeltaX() != 0) {
                event.consume();
            }
        });
        outer.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        outer.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        //handle drag and drop events   
        canvas.setOnDragEntered((DragEvent event) ->
        {
            outer.setStyle("-fx-background-color: aqua;");
        });
//        outer.setOnMouseClicked(ev -> {
//            if(ev.get instanceof CanvasLayer.class)
//            allCanvasLayers.forEach(CanvasLayer::hideMenus);
//        });
        canvas.setOnDragExited((DragEvent event) ->
        {
            outer.setStyle("");
        });
        canvas.setOnDragOver((DragEvent event) ->
        {
            Dragboard db = event.getDragboard();
            if (db.hasContent(SERIALIZED_MIME_TYPE)) {
                event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
            }
            event.consume();
        });
        canvas.setOnDragDropped((DragEvent event) ->
        {
            Dragboard db = event.getDragboard();
            boolean success = false;
            if (db.hasContent(SERIALIZED_MIME_TYPE)) {
                Layer draggedLayer = (Layer) db.getContent(SERIALIZED_MIME_TYPE);
                logger.trace("Dropped layer of type: " + draggedLayer.getType());
                try {
                    logger.trace("Trying to create new instance of layer:" + draggedLayer.getType());
                    Layer newLayer = draggedLayer.getClass().newInstance();
                    CanvasLayer canvasLayer = new CanvasLayer(newLayer);
                    allCanvasLayers.add(canvasLayer);
                    canvasLayer.setTranslateX(event.getX());
                    canvasLayer.setTranslateY(event.getY());
                    canvas.add(canvasLayer);
                    success = true;
                } catch (InstantiationException e) {
                    logger.error("Failed to create new instance of layer due to :" + e.toString());
                } catch (IllegalAccessException e) {
                    logger.error("Failed to create new instance of layer due to illegal access:" + e.toString());
                }

            }
            event.setDropCompleted(success);
            event.consume();
        });

        //events for dragging and zooming canvas
        SceneGestures sceneGestures = new SceneGestures(canvas);
        //canvasArea.addEventFilter( MouseEvent.MOUSE_PRESSED, sceneGestures.getOnMousePressedEventHandler());
        //canvasArea.addEventFilter( MouseEvent.MOUSE_DRAGGED, sceneGestures.getOnMouseDraggedEventHandler());
        canvasArea.addEventFilter(ScrollEvent.ANY, sceneGestures.getOnScrollEventHandler());

        outer.setContent(canvas);
        canvasArea.getChildren().add(outer);
    }

    private Image getFileIcon(Layer item) {

        return new Image("/client/tabs/temp.png");
    }

    private Color getRandomColor() {
        float hue = random.nextFloat();
        // Saturation between 0.1 and 0.3
        float saturation = (random.nextInt(2000) + 1000) / 10000f;
        float luminance = 0.8f;
        Color color = Color.hsb(hue, saturation, luminance);
        return color;
    }


}
