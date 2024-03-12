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

public class PhotoSweep implements DataSource {
    private static final Logger logger = LoggerFactory.getLogger(PhotoSweep.class);
    private static final Preferences prefs = Preferences.userRoot().node("PhotoSweep");

    @Override
    public String getDescription() {
        return "Trimmed, calibrated objects from DR12. Warning: very large dataset.";
    }

    @Override
    public Preferences getPrefs() {
        return prefs;
    }

    @Override
    public Parent getFX() {
        return new DataSweepController();
    }


    @Override
    public String getTitle() {
        return "DR12 Data Sweep";
    }

    @Override
    public Iterator getIndex() {
        return new DataSweepIndex();
    }


}

