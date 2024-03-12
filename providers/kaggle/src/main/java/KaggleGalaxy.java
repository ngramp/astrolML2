import javafx.scene.Parent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spi.DataSource;

import java.util.Iterator;
import java.util.prefs.Preferences;


/**
 * Created by Graham Perry on 19/06/16.
 *
 * @author Graham Perry
 */


public class KaggleGalaxy implements DataSource {
    private static final Logger logger = LoggerFactory.getLogger(KaggleGalaxy.class);
    private static final Preferences prefs = Preferences.userRoot().node("Kaggle");


    @Override
    public String getDescription() {
        return "A subset of the Galaxy Zoo 2 dataset used by Kaggle for the Galaxy Challenge with accompanying target data.";
    }

    @Override
    public Preferences getPrefs() {
        return prefs;
    }

    @Override
    public Parent getFX() {
        return new KaggleController();
    }

    @Override
    public String getTitle() {
        return "DR7 Galaxy Challenge";
    }

    @Override
    public Iterator<KaggleObj> getIndex() {
        return new KaggleGalaxyIndex();
    }


}

