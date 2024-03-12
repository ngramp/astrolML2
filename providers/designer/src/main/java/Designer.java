import design.DesignController;
import javafx.scene.Parent;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;
import org.nd4j.linalg.dataset.api.iterator.MultiDataSetIterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spi.NeuralNet;

import java.util.prefs.Preferences;

/**
 * Created by Graham Perry on 22/08/16.
 *
 * @author Graham Perry
 */
public class Designer implements NeuralNet {
    private static final Logger logger = LoggerFactory.getLogger(Designer.class);
    private static final Preferences prefs = Preferences.userRoot().node("Designer");
    @Override
    public String getTitle() {
        return "Neural Network Designer";
    }

    @Override
    public String getDescription() {
        return "A graphical user interface to build a neural network architecture";
    }

    @Override
    public Preferences getPrefs() {
        return prefs;
    }

    @Override
    public Parent getFX() {
        return new DesignController();
    }

    @Override
    public boolean initialise(DataSetIterator training, DataSetIterator testing) {
        return false;
    }

    @Override
    public boolean initialise(MultiDataSetIterator training, MultiDataSetIterator testing) {
        return false;
    }

    @Override
    public boolean start() {
        return false;
    }

    @Override
    public boolean stop() {
        return false;
    }
}
