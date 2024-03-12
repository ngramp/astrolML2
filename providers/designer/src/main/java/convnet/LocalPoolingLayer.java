package convnet;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.List;


/**
 * Created by gram on 14/04/16.
 */
public class LocalPoolingLayer extends NeuronLayer {
    private final String type = "Local Pooling Layer";
    //indicates that this is to be a max-pooling layer. Also supported is pool=avg for average-pooling.
    private ObjectProperty<Pool> poolType = new SimpleObjectProperty<>();
    //tells the net where in the input image to start the pooling (in x,y coordinates). In principle, you can start anywhere you want. Setting this to a positive number will cause the net to discard some pixels at the top and at the left of the image. Setting this to a negative number will cause it to include pixels that don't exist (which is fine). start=0 is the usual setting.
    private ObjectProperty<Integer> start = new SimpleObjectProperty<Integer>();
    //	defines the size of the pooling region in the x (equivalently, y) dimension. Squares of size (sizeX)2 get reduced to one value by this layer. There are no restrictions on the value of this parameter. It's fine for a pooling square to fall off the boundary of the image.
    private ObjectProperty<Integer> sizeX = new SimpleObjectProperty<Integer>();
    //defines the stride size between successive pooling squares. Setting this parameter smaller than sizeX produces overlapping pools. Setting it equal to sizeX gives the usual, non-overlapping pools. Values greater than sizeX are not allowed.
    private ObjectProperty<Integer> stride = new SimpleObjectProperty<Integer>();
    //allows you to control how many output values in the x (equivalently, y) dimension this operation will produce. This parameter is analogous to the start parameter, in that it allows you to discard some portion of the image by setting it to a value small enough to leave part of the image uncovered. Setting it to zero instructs the net to produce as many outputs as is necessary to ensure that the whole image is covered.
    private ObjectProperty<Integer> outputsx = new SimpleObjectProperty<Integer>();
    private ObjectProperty<Integer> channels = new SimpleObjectProperty<Integer>();

    public LocalPoolingLayer(List<Layer> inputs, Layer output, Neuron neuron, Pool poolType, int start, int sizeX, int stride, int outputsx, int channels) {
        super(inputs, output, neuron);
        this.poolType.set(poolType);
        this.start.set(start);
        this.sizeX.set(sizeX);
        this.stride.set(stride);
        this.outputsx.set(outputsx);
        this.channels.set(channels);
    }

    public LocalPoolingLayer() {
    }

    public ObjectProperty<Pool> poolTypeProperty() {
        return poolType;
    }

    public ObjectProperty<Integer> startProperty() {
        return start;
    }

    public ObjectProperty<Integer> sizeXProperty() {
        return sizeX;
    }

    public ObjectProperty<Integer> strideProperty() {
        return stride;
    }

    public ObjectProperty<Integer> outputsxProperty() {
        return outputsx;
    }

    public ObjectProperty<Integer> channelsProperty() {
        return channels;
    }

    public Pool getPoolType() {
        return poolType.get();
    }

    public void setPoolType(Pool poolType) {
        this.poolType.set(poolType);
    }

    public int getStart() {
        return start.get();
    }

    public void setStart(int start) {
        this.start.set(start);
    }

    public int getSizeX() {
        return sizeX.get();
    }

    public void setSizeX(int sizeX) {
        this.sizeX.set(sizeX);
    }

    public int getStride() {
        return stride.get();
    }

    public void setStride(int stride) {
        this.stride.set(stride);
    }

    public int getOutputsx() {
        return outputsx.get();
    }

    public void setOutputsx(int outputsx) {
        this.outputsx.set(outputsx);
    }

    public int getChannels() {
        return channels.get();
    }

    public void setChannels(int channels) {
        this.channels.set(channels);
    }

    @Override
    public String getType() {
        return type;
    }
}
