package convnet;

import java.util.List;

/**
 * Created by gram on 14/04/16.
 */
public class SoftmaxLayer extends Layer {
    //input must be fullyconnectedlayer
    private final String type = "Softmax Layer";

    public SoftmaxLayer(List<Layer> inputs, Layer output) {
        super(inputs, output);
    }

    public SoftmaxLayer() {
    }

    @Override
    public String getType() {
        return type;
    }
}
