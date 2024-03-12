import javafx.scene.Parent;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;
import org.nd4j.linalg.dataset.api.iterator.MultiDataSetIterator;
import spi.NeuralNet;

import java.util.prefs.Preferences;

/**
 * Created by Graham Perry on 22/08/16.
 *
 * @author Graham Perry
 */
public class Dieleman implements NeuralNet{
    private static final Preferences prefs = Preferences.userRoot().node("Dieleman");
    private DielemanNet ourInstance;
    @Override
    public String getTitle() {
        return "Dieleman et al. (2015)";
    }

    @Override
    public String getDescription() {
        return "An implementation of the Galaxy Challenge winner in Deeplearning4J";
    }

    @Override
    public Preferences getPrefs() {
        return prefs;
    }

    @Override
    public Parent getFX() {
        return new DielemanController();
    }

    @Override
    public boolean initialise(DataSetIterator training, DataSetIterator testing){
        return false;
    }
    @Override
    public boolean initialise(MultiDataSetIterator training,MultiDataSetIterator testing){
        if(ourInstance == null){
            ourInstance = new DielemanNet(training,testing);
            return true;
        }
        else{
            return false;
        }
    }
    @Override
    public boolean start(){
        if(ourInstance == null){
            return false;
        }
        else{
            ourInstance.train();
            return true;
        }

    }
    @Override
    public boolean stop(){
        return false;
    }

}
