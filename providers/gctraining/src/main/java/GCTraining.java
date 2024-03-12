import javafx.scene.Parent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spi.Training;

import java.util.prefs.Preferences;


/**
 * Created by Graham Perry on 19/06/16.
 *
 * @author Graham Perry
 */

public class GCTraining implements Training {
    private static final Logger logger = LoggerFactory.getLogger(GCTraining.class);
    private static final Preferences prefs = Preferences.userRoot().node("GCTraining");

    @Override
    public String getDescription() {
        return "Training provdier for the Galaxy Challenge/Kaggle data set.";
    }

    @Override
    public Preferences getPrefs() {
        return prefs;
    }

    @Override
    public Parent getFX() {
        return new TrainingController();
    }


    @Override
    public String getTitle() {
        return "Galaxy Challenge Training";
    }



}

