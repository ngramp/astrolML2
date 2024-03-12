import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

/**
 * Created by Graham Perry on 07/09/16.
 *
 * @author Graham Perry
 */
public class DielemanController extends StackPane implements Initializable{
    private static final Logger logger = LoggerFactory.getLogger(DielemanController.class);
    private static final Preferences prefs = Preferences.userRoot().node("Dieleman");
    @FXML
    private TextField outputNum,batchSize,nEpochs,iterations,learningRate,biasLearningRate,momentum;
    @FXML
    private TableView<Layer> table;
    DielemanController(){
        logger.info("Trying to load fxml for " + "dieleman-neural-net.fxml");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("dieleman-neural-net.fxml"));
            loader.setController(this);
            loader.setRoot(this);
            loader.load();
        } catch (IOException ex) {
            logger.error(ex.toString());
            ex.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configSetup();
        tableSetup();

    }

    private void tableSetup() {
        final ObservableList<Layer> data = FXCollections.observableArrayList(
                new Layer("Input x16", 45,45,3,0 ),
                new Layer("Convolutional x16", 40,40 ,32,6),
                new Layer("Max pooling",20,20 ,32 ,2),
                new Layer("Convolutional x16", 16,16 ,64,5),
                new Layer("Max pooling",8,8 ,64 ,2),
                new Layer("Convolutional x16", 6,6 ,128,3),
                new Layer("Convolutional x16", 4,4 ,128,3),
                new Layer("Max pooling",2,2 ,128 ,2),
                new Layer("Maxout",2028,1 ,1 ,0),
                new Layer("Maxout",2028,1 ,1 ,0),
                new Layer("Outputs",37,1 ,1 ,0)
        );
        TableColumn<Layer,String> type = new TableColumn("Type");
        TableColumn<Layer,String> height = new TableColumn("Height");
        TableColumn<Layer,String> width = new TableColumn("Width");
        TableColumn<Layer,String> channels = new TableColumn("Channels");
        TableColumn<Layer,String> stride = new TableColumn("Stride");
        type.setCellValueFactory(
            new PropertyValueFactory<Layer,String>("type")
        );
        height.setCellValueFactory(
                new PropertyValueFactory<Layer,String>("height")
        );
        width.setCellValueFactory(
                new PropertyValueFactory<Layer,String>("width")
        );
        channels.setCellValueFactory(
                new PropertyValueFactory<Layer,String>("channels")
        );
        stride.setCellValueFactory(
                new PropertyValueFactory<Layer,String>("stride")
        );
        table.setItems(data);
        table.getColumns().addAll(type,height,width,channels,stride);
        table.setEditable(false);
    }

    private void configSetup() {
        nEpochs.setText(Integer.toString(prefs.getInt("nEpochs",10)));
        batchSize.setText(Integer.toString(prefs.getInt("batchSize",16)));
        outputNum.setText(Integer.toString(prefs.getInt("outputNum",37)));
        iterations.setText(Integer.toString(prefs.getInt("iterations",1)));
        learningRate.setText(Double.toString(prefs.getDouble("learningRate",0.04)));
        biasLearningRate.setText(Double.toString(prefs.getDouble("biasLearningRate",2 * 1e-2)));
        momentum.setText(Double.toString(prefs.getDouble("momentum",0.9)));
        nEpochs.textProperty().addListener((observable, oldValue, newValue) -> {
            prefs.putInt("outputNum",Integer.parseInt(newValue));
        });
        batchSize.textProperty().addListener((observable, oldValue, newValue) -> {
            prefs.putInt("batchSize",Integer.parseInt(newValue));
        });
        outputNum.textProperty().addListener((observable, oldValue, newValue) -> {
            prefs.putInt("outputNum",Integer.parseInt(newValue));
        });
        iterations.textProperty().addListener((observable, oldValue, newValue) -> {
            prefs.putInt("iterations",Integer.parseInt(newValue));
        });
        learningRate.textProperty().addListener((observable, oldValue, newValue) -> {
            prefs.putDouble("learningRate",Double.parseDouble(newValue));
        });
        biasLearningRate.textProperty().addListener((observable, oldValue, newValue) -> {
            prefs.putDouble("biasLearningRate",Double.parseDouble(newValue));
        });
        momentum.textProperty().addListener((observable, oldValue, newValue) -> {
            prefs.putDouble("momentum",Double.parseDouble(newValue));
        });

    }

    public static class Layer{
        private final SimpleStringProperty type;
        private final SimpleIntegerProperty width;
        private final SimpleIntegerProperty height;
        private final SimpleIntegerProperty channels;
        private final SimpleIntegerProperty stride;

        Layer(String type,int width,int height, int channels, int stride){
            this.type = new SimpleStringProperty(type);
            this.width = new SimpleIntegerProperty(width);
            this.height = new SimpleIntegerProperty(height);
            this.channels = new SimpleIntegerProperty(channels);
            this.stride = new SimpleIntegerProperty(stride);
        }

        public void setType(String type) {
            this.type.set(type);
        }

        public void setWidth(int width) {
            this.width.set(width);
        }

        public void setHeight(int height) {
            this.height.set(height);
        }

        public void setChannels(int channels) {
            this.channels.set(channels);
        }

        public void setStride(int stride) {
            this.stride.set(stride);
        }

        public String getType() {
            return type.get();
        }

        public SimpleStringProperty typeProperty() {
            return type;
        }

        public int getWidth() {
            return width.get();
        }

        public SimpleIntegerProperty widthProperty() {
            return width;
        }

        public int getHeight() {
            return height.get();
        }

        public SimpleIntegerProperty heightProperty() {
            return height;
        }

        public int getChannels() {
            return channels.get();
        }

        public SimpleIntegerProperty channelsProperty() {
            return channels;
        }

        public int getStride() {
            return stride.get();
        }

        public SimpleIntegerProperty strideProperty() {
            return stride;
        }
    }
}
