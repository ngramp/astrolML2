package spi;

import javafx.scene.Parent;
import java.util.prefs.Preferences;

/**
 * Created by Graham Perry on 20/08/16.
 *
 * @author Graham Perry
 */
public interface IProvider {
    /**
     * @return The title of the service provide to be used in menus and tabs
     */
    String getTitle();

    /**
     * @return A description of the service provide to be used in the sidebar
     */
    String getDescription();

    /**
     * @return Preferences specific to this service rpovider
     */
    Preferences getPrefs();

    /**
     * @return The fxml controller class for this service provider
     */
    Parent getFX();
}
