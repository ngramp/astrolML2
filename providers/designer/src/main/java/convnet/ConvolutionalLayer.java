package convnet;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.List;

/**
 * Created by gram on 14/04/16.
 */
public class ConvolutionalLayer extends LocallyConnectedLayer {
    private final String type = "Convolutional Layer";


    //indicates that the biases of every filter in this layer should be shared amongst all applications of that filter (which is how convnets are usually trained). Setting this to false will untie the biases, yielding a separate bias for every location at which the filter is applied.
    private ObjectProperty<Boolean> sharedBiases = new SimpleObjectProperty<Boolean>();
    //this is a parameter that affects the performance of the weight gradient computation. It's a bit hard to predict what value will result in the best performance (it's problem-specific), but it's worth trying a few. Valid values are ones that divide the area of the output grid in this convolutional layer. For example if this layer produces 32-channel 20x20 output grid, valid values of partialSum are ones which divide 20*20 = 400. This parameter also has an impact on memory consumption. The amount of extra memory used will be: (number of weights in this layer)x(number of outputs this layer produces) / partialSum. My belief is that since one of the defining features of convolutional nets is their small number of weights, this shouldn't be a big deal.
    private ObjectProperty<Integer> partialSum = new SimpleObjectProperty<>();
    // The primary novelty here is the groups=4 parameter. Together with the filters=32 parameter, they state that this convolutional layer is to have 4 groups of 32 filters. Each filter will connect to (i.e. sum over) 8 input channels (a quarter of the total number of input channels).
    private ObjectProperty<Integer> groups = new SimpleObjectProperty<Integer>();
    //indicates that random sparsity should be used in this layer. When this parameter is true, the groups parameter must be greater than 1 (otherwise the connectivity will not be sparse).
    private ObjectProperty<Boolean> randomSparse = new SimpleObjectProperty<Boolean>();
    //an optional parameter indicating how many input channels each filter should look at. The quantity filterChannels*groups must be a multiple of channels.
    private ObjectProperty<Integer> filterChannels = new SimpleObjectProperty<Integer>();

    public ConvolutionalLayer(List<Layer> inputs, Layer output, Neuron neuron, double initW, int channels, int filters, int padding, int stride, int filtersize, double initB, boolean sharedBiases, int partialSum, int groups, boolean randomSparse, int filterChannels) {
        super(inputs, output, neuron, initW, channels, filters, padding, stride, filtersize, initB);
        this.sharedBiases.set(sharedBiases);
        this.partialSum.set(partialSum);
        this.groups.set(groups);
        this.randomSparse.set(randomSparse);
        this.filterChannels.set(filterChannels);
    }

    public ConvolutionalLayer() {

    }

    public ObjectProperty<Boolean> sharedBiasesProperty() {
        return sharedBiases;
    }

    public ObjectProperty<Integer> partialSumProperty() {
        return partialSum;
    }

    public ObjectProperty<Integer> groupsProperty() {
        return groups;
    }

    public ObjectProperty<Boolean> randomSparseProperty() {
        return randomSparse;
    }

    public ObjectProperty<Integer> filterChannelsProperty() {
        return filterChannels;
    }

    public boolean getSharedBiases() {
        return sharedBiases.get();
    }

    public void setSharedBiases(boolean sharedBiases) {
        this.sharedBiases.set(sharedBiases);
    }

    public int getPartialSum() {
        return partialSum.get();
    }

    public void setPartialSum(int partialSum) {
        this.partialSum.set(partialSum);
    }

    public int getGroups() {
        return groups.get();
    }

    public void setGroups(int groups) {
        this.groups.set(groups);
    }

    public boolean getRandomSparse() {
        return randomSparse.get();
    }

    public void setRandomSparse(boolean randomSparse) {
        this.randomSparse.set(randomSparse);
    }

    public int getFilterChannels() {
        return filterChannels.get();
    }

    public void setFilterChannels(int filterChannels) {
        this.filterChannels.set(filterChannels);
    }

    @Override
    public String getType() {
        return type;
    }
}
