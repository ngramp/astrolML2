package convnet;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.List;


/**
 * Created by gram on 14/04/16.
 */
public class LocallyConnectedLayer extends WeightedNeuronLayer {
    private final String type = "Locally Connected Layer";
    //tells the net that the layer named data produces 3-channel images (i.e. color images). Since the images are assumed to be square, that is all that you have to tell it about the data dimensionality. This value must be either 1, 2, 3, or a multiple of 4.
    private ObjectProperty<Integer> channels = new SimpleObjectProperty<Integer>();
    //says that this layer will apply 32 filters to the images. This number must be a multiple of 16.The computation will be most efficient when filters is divisible by 32.
    private ObjectProperty<Integer> filters = new SimpleObjectProperty<Integer>();
    //instructs the net to implicitly pad the images with a 4-pixel border of zeros (this does not cause it to create a copy of the data or use any extra memory). Set to 0 if you don't want padding.
    private ObjectProperty<Integer> padding = new SimpleObjectProperty<Integer>();
    //indicates that the distance between successive filter applications should be 1 pixel.
    private ObjectProperty<Integer> stride = new SimpleObjectProperty<Integer>();
    //says that this layer will use filters of size 9x9 pixels (with 3 channels).
    private ObjectProperty<Integer> filterSize = new SimpleObjectProperty<Integer>();
    //instructs the net to initialize the biases in this layer to 0.5.
    private DoubleProperty initB = new SimpleDoubleProperty();

    public LocallyConnectedLayer(List<Layer> inputs, Layer output, Neuron neuron, double initW, int channels, int filters, int padding, int stride, int filterSize, double initB) {
        super(inputs, output, neuron, initW);
        this.channels.set(channels);
        this.filters.set(filters);
        this.padding.set(padding);
        this.stride.set(stride);
        this.filterSize.set(filterSize);
        this.initB.set(initB);
    }

    public LocallyConnectedLayer() {
    }

    public ObjectProperty<Integer> channelsProperty() {
        return channels;
    }

    public ObjectProperty<Integer> filtersProperty() {
        return filters;
    }

    public ObjectProperty<Integer> paddingProperty() {
        return padding;
    }

    public ObjectProperty<Integer> strideProperty() {
        return stride;
    }

    public ObjectProperty<Integer> filterSizeProperty() {
        return filterSize;
    }

    public DoubleProperty initBProperty() {
        return initB;
    }

    public int getChannels() {
        return channels.get();
    }

    public void setChannels(int channels) {
        this.channels.set(channels);
    }

    public int getFilters() {
        return filters.get();
    }

    public void setFilters(int filters) {
        this.filters.set(filters);
    }

    public int getPadding() {
        return padding.get();
    }

    public void setPadding(int padding) {
        this.padding.set(padding);
    }

    public int getStride() {
        return stride.get();
    }

    public void setStride(int stride) {
        this.stride.set(stride);
    }

    public int getFilterSize() {
        return filterSize.get();
    }

    public void setFilterSize(int filterSize) {
        this.filterSize.set(filterSize);
    }

    public double getInitB() {
        return initB.get();
    }

    public void setInitB(double initB) {
        this.initB.set(initB);
    }

    @Override
    public String getType() {
        return type;
    }
}
