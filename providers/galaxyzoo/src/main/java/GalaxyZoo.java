import javafx.scene.Parent;
import model.ObjCType;
import spi.DataSource;

import java.util.Iterator;
import java.util.prefs.Preferences;

/**
 * Created by Graham Perry on 19/06/16.
 *
 * @author Graham Perry
 */
public class GalaxyZoo implements DataSource {

    @Override
    public String getDescription() {
        return "Galaxies from DR7 matched to the GalaxyZoo training data.";
    }

    @Override
    public Preferences getPrefs() {
        return null;
    }

    @Override
    public Parent getFX() {
        return null;
    }

    @Override
    public String getTitle() {
        return "DR7 Galaxy Zoo";
    }


    @Override
    public Iterator getIndex() {
        return null;
    }


}
