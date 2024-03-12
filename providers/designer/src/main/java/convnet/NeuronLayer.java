package convnet;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.List;

/**
 * Created by gram on 14/04/16.
 */
public class NeuronLayer extends Layer {
    private final String type = "Neuron Layer";
    //This layer takes one layer as input and applies a neuron activation function to it. See NeuronTypes for available activation functions.
    private ObjectProperty<Neuron> neuron = new SimpleObjectProperty<>();

    public NeuronLayer(List<Layer> inputs, Layer output, Neuron neuron) {
        super(inputs, output);
        this.neuron.set(neuron);
    }

    public NeuronLayer() {
    }

    public ObjectProperty<Neuron> neuronProperty() {
        return neuron;
    }

    public Neuron getNeuron() {
        return neuron.get();
    }

    public void setNeuron(Neuron neuron) {
        this.neuron.set(neuron);
    }

    @Override
    public String getType() {
        return type;
    }

}
