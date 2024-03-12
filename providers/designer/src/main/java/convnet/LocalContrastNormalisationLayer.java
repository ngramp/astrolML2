package convnet;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

import java.util.List;

/**
 * Created by gram on 14/04/16.
 */
public class LocalContrastNormalisationLayer extends NormalisationLayer {
    private final String type = "Local Contrast Normalisation Layer";
    private DoubleProperty scale = new SimpleDoubleProperty();
    private DoubleProperty pow = new SimpleDoubleProperty();

    public LocalContrastNormalisationLayer(List<Layer> inputs, Layer output, int channels, int size, double scale, double pow) {
        super(inputs, output, channels, size);
        this.scale.set(scale);
        this.pow.set(pow);
    }

    public LocalContrastNormalisationLayer() {
    }

    @Override
    public String getType() {
        return type;
    }

    public double getScale() {
        return scale.get();
    }

    public void setScale(double scale) {
        this.scale.set(scale);
    }

    public double getPow() {
        return pow.get();
    }

    public void setPow(double pow) {
        this.pow.set(pow);
    }

    public DoubleProperty scaleProperty() {
        return scale;
    }

    public DoubleProperty powProperty() {
        return pow;
    }
}
