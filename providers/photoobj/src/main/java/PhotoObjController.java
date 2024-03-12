import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import photoobj.PhotoObj;
import util.ModelTableViewBuilder;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.prefs.Preferences;

;

/**
 * Created by gram on 22/03/16.
 */
public class PhotoObjController extends StackPane implements Initializable {
    private static final Logger logger = LoggerFactory.getLogger(PhotoObjController.class);
    private static final Preferences prefs = Preferences.userRoot().node("PhotoObj");

    @FXML
    private VBox objTypes,flags1,flags2,resolveStatus,calibStatus;
    @FXML
    private ScrollPane previewContainer;
    @FXML
    private Slider qualitySlider;
    @FXML
    private ProgressBar previewProgress;
    @FXML
    private Label message;
    @FXML
    private Button previewButton;
    private Filters aFilters = new Filters(prefs);

    PhotoObjController() {
        logger.info("Trying to load fxml for " + "PhotoObj.fxml");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/PhotoObj.fxml"));
            loader.setController(this);
            loader.setRoot(this);
            loader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
            logger.error(ex.getMessage());
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resource) {
        try {
            setupCalibStatus();
            setupFlags1();
            setupFlag2();
            setupResolveStatus();
            setupObjType();
            setupFilters();
            setupPreview();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void setupPreview() {
        previewButton.setOnAction(e -> {
            showPreview();
        });
    }

    private void setupFilters() {

        qualitySlider.setMin(0);
        qualitySlider.setMax(10);
        qualitySlider.setShowTickLabels(true);
        qualitySlider.setShowTickMarks(true);
        qualitySlider.setMajorTickUnit(1);
        qualitySlider.setMinorTickCount(1);
        qualitySlider.setBlockIncrement(0.1);
        qualitySlider.setValue(prefs.getFloat("Quality",0.0f));
        qualitySlider.valueProperty().addListener((ov, old_val, new_val) -> prefs.putFloat("Quality",new_val.floatValue()));
    }
    private CheckBox setCheck(String filter, String helptext){
        CheckBox check = new CheckBox(filter);
        check.setAllowIndeterminate(true);
        if (prefs.getBoolean(check.getText() + "undef",true)){
            check.setIndeterminate(true);
        }
        else{
            check.selectedProperty().setValue(prefs.getBoolean(check.getText(),true));
        }
        check.indeterminateProperty().addListener((ov, old_val, new_val) -> prefs.putBoolean(check.getText() + "undef",new_val));
        check.selectedProperty().addListener((ov, old_val, new_val) -> prefs.putBoolean(check.getText(),new_val));
        check.setTooltip(new Tooltip(helptext));
        return check;
    }
    private void setupCalibStatus() throws NoSuchFieldException, IllegalAccessException {
        Map<String,Boolean> filters = aFilters.getCalibFilters();
        for(String filter : filters.keySet()){
            String help = (String) CalibStatus.class.getField(filter + "Desc").get(null);
            calibStatus.getChildren().add(setCheck(filter,help));

        }
    }
    private void setupFlags1() throws NoSuchFieldException, IllegalAccessException {
      Map<String,Boolean> filters = aFilters.getFlags1Filters();
        for(String filter : filters.keySet()){
            String help = (String) Flags1.class.getField(filter + "Desc").get(null);
            flags1.getChildren().add(setCheck(filter,help));
        }
    }

    private void setupFlag2() throws NoSuchFieldException, IllegalAccessException {
        Map<String,Boolean> filters = aFilters.getFlags2Filters();
        for(String filter : filters.keySet()){
            String help = (String) Flags2.class.getField(filter + "Desc").get(null);
            flags2.getChildren().add(setCheck(filter,help));
        }
    }
    private void setupResolveStatus() throws IllegalAccessException, NoSuchFieldException {
        Map<String,Boolean> filters = aFilters.getResolveFilters();
        for(String filter : filters.keySet()){
            String help = (String) ResolveStatus.class.getField(filter + "Desc").get(null);
            resolveStatus.getChildren().add(setCheck(filter,help));
        }

    }
    private void setupObjType() throws NoSuchFieldException {
        Map<String,Boolean> f = aFilters.getObjTypeFilters();
        for(String filter : f.keySet()){
            objTypes.getChildren().add(setCheck(filter,""));
        }

    }

    private void showPreview() {

        TableView<PhotoObj> table = ModelTableViewBuilder.buildUpon(PhotoObj.class);
        previewContainer.setContent(table);
        List<PhotoObj> index = new ArrayList<>();
        ObservableList<PhotoObj> data = FXCollections.observableArrayList(index);
        table.setItems(data);
        Task download = new PreviewTask(data);
        previewProgress.progressProperty().bind(download.progressProperty());
        new Thread(download).start();


    }
    private class PreviewTask extends Task<Integer>{
        ObservableList<PhotoObj> data;
        PreviewTask(ObservableList<PhotoObj> data){
            this.data = data;
        }
        @Override
        protected Integer call() throws Exception {
            Iterator<PhotoObj> iter = new PhotoRunAll();
            for(int i = 0; i < 50 && iter.hasNext(); i++){
                data.add(iter.next());
                updateProgress(i,50);
            }
            return null;
        }
    }

}
