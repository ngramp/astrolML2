package convnet;

import java.util.List;

/**
 * Created by gram on 14/04/16.
 */
public class ElementwiseMaxLayer extends ElementwiseLayer {
    private final String type = "Elementwise Max Layer";

    public ElementwiseMaxLayer(List<Layer> inputs, Layer output) {
        super(inputs, output);
    }

    public ElementwiseMaxLayer() {
    }

    @Override
    public String getType() {
        return type;
    }
}
