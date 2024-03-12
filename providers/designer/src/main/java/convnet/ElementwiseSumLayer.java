package convnet;

import javafx.beans.property.DoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

/**
 * Created by gram on 14/04/16.
 */
public class ElementwiseSumLayer extends ElementwiseLayer {
    private final String type = "Elementwise Sum Layer";
    private ObservableList<DoubleProperty> coeffs = FXCollections.observableArrayList();

    public ElementwiseSumLayer(List<Layer> inputs, Layer output, List<DoubleProperty> coeffs) {
        super(inputs, output);
        this.coeffs.addAll(coeffs);
    }

    public ElementwiseSumLayer() {
    }

    @Override
    public String getType() {
        return type;
    }

    public ObservableList<DoubleProperty> coeffsProperty() {
        return coeffs;
    }

    public ObservableList<DoubleProperty> getCoeffs() {
        return coeffs;
    }

    public void setCoeffs(ObservableList<DoubleProperty> coeffs) {
        this.coeffs = coeffs;
    }
}
