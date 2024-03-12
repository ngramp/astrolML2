import calibobj.CalibObj;
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
import util.ModelTableViewBuilder;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.prefs.Preferences;

;

/**
 * Created by gram on 22/03/16.
 */
public class DataSweepController extends StackPane implements Initializable {
    private static final Logger logger = LoggerFactory.getLogger(DataSweepController.class);
    private static final Preferences prefs = Preferences.userRoot().node("PhotoSweep");
    @FXML
    private VBox objTypes,flags1,flags2,resolveStatus,calibStatus;
    @FXML
    private RadioButton galaxy, star, sky;
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
    private ToggleGroup typeGroup;

    private Filters aFilters = new Filters(prefs);
    DataSweepController() {
        logger.info("Trying to load fxml for " + "DataSweep.fxml");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/DataSweep.fxml"));
            loader.setController(this);
            loader.setRoot(this);
            loader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
            logger.error(ex.getMessage());
        }
        //prefs.clear();
        setupChoices();
    }
    @Override
    public void initialize(URL url, ResourceBundle resource) {
        try {
            setupToggles();
            setupCalibStatus();
            setupFlags1();
            setupFlag2();
            setupResolveStatus();
            setupFilters();
            setupPreview();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        //objectData.setContent(new DR12TableView());
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

    private void setupToggles() {
        typeGroup = new ToggleGroup();
        galaxy.setToggleGroup(typeGroup);
        star.setToggleGroup(typeGroup);
        sky.setToggleGroup(typeGroup);
        typeGroup.selectToggle(getToggleForObjType(Enum.valueOf(ObjCType.class,prefs.get("Object type",ObjCType.GALAXY.toString()))));
        typeGroup.selectedToggleProperty().addListener((ov, old_toggle, new_toggle) -> {
            if (typeGroup.getSelectedToggle() != null) {
                updateSourceChoice(new_toggle);
            }
        });
    }
    private Toggle getToggleForObjType(ObjCType type){
        switch (type){
            case GALAXY:
                return galaxy;
            case STAR:
                return star;
            case SKY:
                return sky;
        }
        return null;
    }

    private void setupChoices() {

    }

    private void updateSourceChoice(Toggle newToggle) {
        if (newToggle == galaxy) {
            prefs.put("Object type", ObjCType.GALAXY.toString());
        } else if (newToggle == star) {
            prefs.put("Object type", ObjCType.STAR.toString());
        } else if (newToggle == sky) {
            prefs.put("Object type", ObjCType.SKY.toString());
        }
    }

    private void showPreview() {

        TableView<CalibObj> table = ModelTableViewBuilder.buildUpon(CalibObj.class);
        previewContainer.setContent(table);
        List<CalibObj> index = new ArrayList<>();
        ObservableList<CalibObj> data = FXCollections.observableArrayList(index);
        table.setItems(data);
        Task download = new PreviewTask(data);
        previewProgress.progressProperty().bind(download.progressProperty());
        new Thread(download).start();


    }
    private class PreviewTask extends Task<Integer>{

        ObservableList<CalibObj> data;
        PreviewTask(ObservableList<CalibObj> data){
            this.data = data;
        }
        @Override
        protected Integer call() throws Exception {
            Iterator<CalibObj> iter = new DataSweepIndex();
            for(int i = 0; i < 50 && iter.hasNext(); i++){
                data.add(iter.next());
                updateProgress(i,50);
            }
            return null;
        }
    }


}
