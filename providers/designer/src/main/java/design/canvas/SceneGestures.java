package design.canvas;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Listeners for making the scene's canvas draggable and zoomable
 */
public class SceneGestures {
    private static final Logger logger = LoggerFactory.getLogger(SceneGestures.class);

    private static final double MAX_SCALE = 10.0d;
    private static final double MIN_SCALE = .1d;
    CanvasGroup canvas;
    Group group;
    private DragContext sceneDragContext = new DragContext();
    //    private EventHandler<MouseEvent> onMousePressedEventHandler = new EventHandler<MouseEvent>() {
//
//        public void handle(MouseEvent event) {
//
//            // right mouse button => panning
//            if( !event.isMiddleButtonDown())
//                return;
//
//            sceneDragContext.mouseAnchorX = event.getSceneX();
//            sceneDragContext.mouseAnchorY = event.getSceneY();
//
//            sceneDragContext.translateAnchorX = canvas.getTranslateX();
//            sceneDragContext.translateAnchorY = canvas.getTranslateY();
//
//        }
//
//    };
    private EventHandler<MouseEvent> onMousePressedEventHandler = new EventHandler<MouseEvent>() {

        public void handle(MouseEvent event) {

            // right mouse button => panning
            if (!event.isMiddleButtonDown())
                return;

            sceneDragContext.mouseAnchorX = event.getSceneX();
            sceneDragContext.mouseAnchorY = event.getSceneY();

            sceneDragContext.translateAnchorX = canvas.getTranslateX();
            sceneDragContext.translateAnchorY = canvas.getTranslateY();

        }

    };
    //    private EventHandler<MouseEvent> onMouseDraggedEventHandler = new EventHandler<MouseEvent>() {
//        public void handle(MouseEvent event) {
//
//            // right mouse button => panning
//            if( !event.isMiddleButtonDown())
//                return;
//            System.out.println("her" + (sceneDragContext.translateAnchorX + event.getSceneX() - sceneDragContext.mouseAnchorX));
//            canvas.setTranslateX(sceneDragContext.translateAnchorX + event.getSceneX() - sceneDragContext.mouseAnchorX);
//            canvas.setTranslateY(sceneDragContext.translateAnchorY + event.getSceneY() - sceneDragContext.mouseAnchorY);
//
//            event.consume();
//        }
//    };
    private EventHandler<MouseEvent> onMouseDraggedEventHandler = new EventHandler<MouseEvent>() {
        public void handle(MouseEvent event) {

            // right mouse button => panning
            if (!event.isMiddleButtonDown())
                return;
            canvas.setTranslateX(sceneDragContext.translateAnchorX + event.getSceneX() - sceneDragContext.mouseAnchorX);
            canvas.setTranslateY(sceneDragContext.translateAnchorY + event.getSceneY() - sceneDragContext.mouseAnchorY);

            event.consume();
        }
    };
    /**
     * Mouse wheel handler: zoom to pivot point
     */
    private EventHandler<ScrollEvent> onScrollEventHandler = new EventHandler<ScrollEvent>() {

        @Override
        public void handle(ScrollEvent event) {

            double delta = 1.2;

            double scale = canvas.getScale(); // currently we only use Y, same value is used for X
            double oldScale = scale;

            if (event.getDeltaY() < 0)
                scale /= delta;
            else
                scale *= delta;

            scale = clamp(scale, MIN_SCALE, MAX_SCALE);

            double f = (scale / oldScale) - 1;

            double dx = (event.getSceneX() - (canvas.getBoundsInParent().getWidth() / 2 + canvas.getBoundsInParent().getMinX()));
            double dy = (event.getSceneY() - (canvas.getBoundsInParent().getHeight() / 2 + canvas.getBoundsInParent().getMinY()));

            canvas.setScale(scale);

            // note: pivot value must be untransformed, i. e. without scaling
            canvas.setPivot(f * dx, f * dy);
            //canvas.setPivot(event.getX(), event.getY());

            event.consume();

        }

    };

    public SceneGestures(CanvasGroup canvas) {
        this.canvas = canvas;
        this.group = group;
    }

    private static double clamp(double value, double min, double max) {

        if (Double.compare(value, min) < 0)
            return min;

        if (Double.compare(value, max) > 0)
            return max;

        return value;
    }

    public EventHandler<MouseEvent> getOnMousePressedEventHandler() {
        return onMousePressedEventHandler;
    }

    public EventHandler<MouseEvent> getOnMouseDraggedEventHandler() {
        return onMouseDraggedEventHandler;
    }

    public EventHandler<ScrollEvent> getOnScrollEventHandler() {
        return onScrollEventHandler;
    }
}
