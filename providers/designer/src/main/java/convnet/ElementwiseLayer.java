package convnet;

import java.util.List;

/**
 * Created by gram on 14/04/16.
 */
public class ElementwiseLayer extends Layer {

    private final String type = "ElementwiseLayer";


    public ElementwiseLayer(List<Layer> inputs, Layer output) {
        super(inputs, output);
    }

    public ElementwiseLayer() {
    }

    @Override
    public String getType() {
        return type;
    }
}
