package convnet;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

import java.util.List;

/**
 * Created by gram on 14/04/16.
 */
public class WeightedNeuronLayer extends NeuronLayer {

    private final String type = "Weighted Neuron Layer";
    //instructs the net to initialize the weights in this layer from a normal distribution with mean zero and standard deviation 0.00001.
    private DoubleProperty initW = new SimpleDoubleProperty();

    public WeightedNeuronLayer() {
    }

    public WeightedNeuronLayer(List<Layer> inputs, Layer output, Neuron neuron, double initW) {
        super(inputs, output, neuron);
        this.initW.set(initW);
    }

    public DoubleProperty initWProperty() {
        return initW;
    }

    public double getInitW() {
        return initW.get();
    }

    public void setInitW(double initW) {
        this.initW.set(initW);
    }

    public String getType() {
        return type;
    }


}
