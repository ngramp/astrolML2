package convnet;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.List;

/**
 * Created by gram on 14/04/16.
 */
abstract public class NormalisationLayer extends Layer {
    private final String type = "Normalisation Layer";
    //accross maps or same map (cmrnorm or rnorm)
    //private int type;
    //indicates that this layer takes 32-channel input because that's what the maxpool layer produces. The number of "channels" here just serves to define the shape of the input and has no actual bearing on the output (unlike in convolutional layers, which sum over channels).
    private ObjectProperty<Integer> channels = new SimpleObjectProperty<Integer>();
    //the N variable in the above formula, this defines the size of the local regions used for response normalization. Squares of (size)2 are used to normalize each pixel. The squares are centered at the pixel.
    private ObjectProperty<Integer> size = new SimpleObjectProperty<Integer>();

    public NormalisationLayer(List<Layer> inputs, Layer output, int channels, int size) {
        super(inputs, output);
        this.channels.set(channels);
        this.size.set(size);
    }

    public NormalisationLayer() {
    }

    public ObjectProperty<Integer> channelsProperty() {
        return channels;
    }

    public ObjectProperty<Integer> sizeProperty() {
        return size;
    }

    public int getChannels() {
        return channels.get();
    }

    public void setChannels(int channels) {
        this.channels.set(channels);
    }

    public int getSize() {
        return size.get();
    }

    public void setSize(int size) {
        this.size.set(size);
    }

    @Override
    public String getType() {
        return type;
    }
}
