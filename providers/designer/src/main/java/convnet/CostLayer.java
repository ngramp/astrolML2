package convnet;

import java.util.List;

/**
 * Created by gram on 14/04/16.
 */

abstract public class CostLayer extends Layer {
    private final String type = "Cost Layer";

    public CostLayer(List<Layer> inputs, Layer output) {
        super(inputs, output);
    }

    public CostLayer() {
    }

    @Override
    public String getType() {
        return type;
    }
}
