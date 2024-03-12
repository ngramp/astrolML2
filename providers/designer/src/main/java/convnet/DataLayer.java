package convnet;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.List;

/**
 * Created by gram on 14/04/16.
 */
public class DataLayer extends Layer {
    private final String type = "Data Layer";
    private StringProperty dataIdx = new SimpleStringProperty();

    //a data layer is mapped to the image data stream, and hence has null input layers


    public DataLayer(List<Layer> inputs, Layer output, String dataIdx) {
        super(inputs, output);
        this.dataIdx.set(dataIdx);
    }

    @Override
    public String getType() {
        return type;
    }

    public StringProperty dataIdxProperty() {
        return dataIdx;
    }

}
