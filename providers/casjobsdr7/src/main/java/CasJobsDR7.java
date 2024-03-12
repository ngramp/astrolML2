import javafx.scene.Parent;
import model.CosObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spi.ImageSource;

import java.awt.image.BufferedImage;
import java.util.prefs.Preferences;


/**
 * Created by Graham Perry on 19/06/16.
 *
 * @author Graham Perry
 */

public class CasJobsDR7 implements ImageSource {
    private static final Logger logger = LoggerFactory.getLogger(CasJobsDR7.class);
    private static final Preferences prefs = Preferences.userRoot().node("Casjobs");
    private static CutoutClient client;

    @Override
    public String getDescription() {
        return "Images from CAS covering the DR7 dataset";
    }

    @Override
    public Preferences getPrefs() {
        return prefs;
    }

    @Override
    public Parent getFX() {
        return new ImagesController();
    }

    @Override
    public String getTitle() {
        return "CAS ImgCutout";
    }

    @Override
    public BufferedImage getImage(CosObject obj) {
        client = new CutoutClient(
            prefs.getInt("Width", 400),
            prefs.getInt("Height", 400),
            prefs.getInt("maxTries",20),
            prefs.getFloat("maxLum",25.0f),
            prefs.getDouble("maxP",25.0)/100,
            prefs.getFloat("startSize",0.01f),
            prefs.getFloat("stepSize",0.01f)
        );
        return client.getImage(obj.getRa(),obj.getDec());
    }



}

