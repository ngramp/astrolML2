import javafx.scene.Parent;
import model.CosObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spi.ImageSource;

import java.awt.*;
import java.util.prefs.Preferences;

/**
 * Created by Graham Perry on 19/06/16.
 *
 * @author Graham Perry
 */

public class KaggleImages implements ImageSource {
    private static final Logger logger = LoggerFactory.getLogger(KaggleImages.class);
    private static final Preferences prefs = Preferences.userRoot().node("Kaggle Images");

    @Override
    public String getDescription() {
        return "Images from Kaggle covering the DR7 dataset used for Galaxy Challenge";
    }

    @Override
    public Preferences getPrefs() {
        return prefs;
    }

    @Override
    public Parent getFX() {
        return new KaggleImagesController();
    }

    @Override
    public String getTitle() {
        return "Galaxy Challenge Images";
    }

    @Override
    public Image getImage(CosObject obj) {
        KaggleImageUtil util = KaggleImageUtil.getInstance();
        return util.getImage(obj.getObjid());
    }



}

