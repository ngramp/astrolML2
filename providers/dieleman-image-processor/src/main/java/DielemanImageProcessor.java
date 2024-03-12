import javafx.scene.Parent;
import spi.ImageProcessor;

import java.awt.*;
import java.util.ArrayList;
import java.util.prefs.Preferences;

/**
 * Created by Graham Perry on 22/08/16.
 *
 * @author Graham Perry
 */
public class DielemanImageProcessor implements ImageProcessor {
    private static final Preferences prefs = Preferences.userRoot().node("DielemanImageProcessor");
    @Override
    public String getTitle() {
        return "Dieleman Image Processor";
    }

    @Override
    public String getDescription() {
        return "Provides 16 45x45 viewpoints of a single galaxy image. To be used with Dieleman Neural Network.";
    }

    @Override
    public Preferences getPrefs() {
        return prefs;
    }

    @Override
    public Parent getFX() {
        return new DielemanIPController();
    }

    @Override
    public ArrayList<Image> process(Image image) {
        ArrayList<Image> ret = new ArrayList<>();
        ret.add(image);
        return ret;
    }
}
