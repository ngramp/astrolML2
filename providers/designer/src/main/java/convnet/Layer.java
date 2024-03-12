package convnet;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 * Created by gram on 14/04/16.
 */
//Tree style code from http://stackoverflow.com/questions/19330731/tree-implementation-in-java-root-parents-and-children
abstract public class Layer implements Serializable {
    private final String type = "Layer";
    private final UUID id = UUID.randomUUID();
    private ObservableList<Layer> inputs = FXCollections.observableArrayList();
    private ObjectProperty<Layer> output = new SimpleObjectProperty<>();

    public Layer(List<Layer> inputs, Layer output) {
        this.inputs.addAll(inputs);
        this.output.set(output);
    }

    public Layer() {
    }

    public Layer getOutput() {
        return output.get();
    }

    public void setOutput(Layer output) {
        output.addInput(this);
        this.output.set(output);
    }

    public UUID getId() {
        return id;
    }

    public List<Layer> getinputs() {
        return inputs;
    }

    public void addInput(Layer input) {
        //input.setOutput(this); ??
        this.inputs.add(input);
    }

    public boolean isRoot() {
        return (this.output.get() == null);
    }

    public boolean isLeaf() {
        return this.inputs.size() == 0;
    }

    public void removeOutput() {
        this.output.set(null);
    }

    public String getType() {
        return type;
    }


}
