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
public class NullImageProcessor implements ImageProcessor {
    private static final Preferences prefs = Preferences.userRoot().node("NullImageProcessor");
    @Override
    public String getTitle() {
        return "None";
    }

    @Override
    public String getDescription() {
        return "Simply returns the input image";
    }

    @Override
    public Preferences getPrefs() {
        return prefs;
    }

    @Override
    public Parent getFX() {
        return null;
    }

    @Override
    public ArrayList<Image> process(Image image) {
        ArrayList<Image> ret = new ArrayList<>();
        ret.add(image);
        return ret;
    }
}
