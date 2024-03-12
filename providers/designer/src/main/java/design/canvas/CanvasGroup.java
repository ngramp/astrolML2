package design.canvas;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Graham Perry on 20/04/16.
 * Modified considerably from source
 *
 * @author Graham Perry
 * @author Roland
 * @see "http://stackoverflow.com/questions/29506156/javafx-8-zooming-relative-to-mouse-pointer"
 */
public class CanvasGroup extends Group {
    private static final Logger logger = LoggerFactory.getLogger(CanvasGroup.class);
    private final double w = 600;//getBoundsInLocal().getWidth();
    private final double h = 600;//getBoundsInLocal().getHeight();
    private DoubleProperty myScale = new SimpleDoubleProperty(1.0);
    private Canvas canvas;
    private Canvas grid;
    private StackPane pane;

    public CanvasGroup() {
        logger.trace("Constructing Canvas");
        pane = new StackPane();
        canvas = new Canvas(w, h);
        pane.setPrefSize(w, h);
        pane.setStyle("-fx-background-color: white; -fx-border-color: blue;");

        // add scale transform
        scaleXProperty().bind(myScale);
        scaleYProperty().bind(myScale);
        getChildren().addAll(canvas, pane);
    }

    public Canvas getCanvas() {
        return canvas;
    }

    /**
     * Add a grid to the canvas, send it to back
     */
    public void addGrid() {
        logger.trace("Adding Grid");

        // add grid
        Canvas grid = new Canvas(w, h);

        // don't catch mouse events
        grid.setMouseTransparent(true);

        GraphicsContext gc = grid.getGraphicsContext2D();

        gc.setStroke(Color.LIGHTGRAY);
        gc.setLineWidth(1);

        // draw grid lines
        double offset = 50;
        for (double i = offset; i < w; i += offset) {
            gc.strokeLine(i, 0, i, h);
            gc.strokeLine(0, i, w, i);
        }
        getChildren().add(grid);
        //grid.toBack();
    }

    public double getScale() {
        return myScale.get();
    }

    public void setScale(double scale) {
        logger.trace("Setting scale to " + scale);
        myScale.set(scale);
    }

    public void setPivot(double x, double y) {
        logger.trace("Adding pivot X:" + getTranslateX() + x + " Y:" + getTranslateY() + y);
        setTranslateX(getTranslateX() + x);
        setTranslateY(getTranslateY() + y);
    }

    public void add(Node node) {
        logger.trace("Adding node to canvas");
        NodeGestures nodeGestures = new NodeGestures(this);
        node.addEventFilter(MouseEvent.MOUSE_PRESSED, nodeGestures.getOnMousePressedEventHandler());
        node.addEventFilter(MouseEvent.MOUSE_DRAGGED, nodeGestures.getOnMouseDraggedEventHandler());
        getChildren().add(node);
    }
}
