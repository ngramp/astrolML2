package convnet;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.List;

/**
 * Created by gram on 14/04/16.
 */
public class FullyConnectedLayer extends WeightedNeuronLayer {
    private final String type = "Fully Connected Layer";
    private ObjectProperty<Integer> neuronCount = new SimpleObjectProperty<Integer>();

    public FullyConnectedLayer(List<Layer> inputs, Layer output, Neuron neuron, double initW, int neuronCount) {
        super(inputs, output, neuron, initW);
        this.neuronCount.set(neuronCount);
    }

    public FullyConnectedLayer() {
    }

    public int getNeuronCount() {
        return neuronCount.get();
    }

    public void setNeuronCount(int neuronCount) {
        this.neuronCount.set(neuronCount);
    }

    public String getType() {
        return type;
    }

    public ObjectProperty<Integer> neuronCountProperty() {
        return neuronCount;
    }
}
