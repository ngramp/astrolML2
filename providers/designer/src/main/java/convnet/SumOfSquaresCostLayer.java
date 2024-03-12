package convnet;

import java.util.List;

/**
 * Created by gram on 14/04/16.
 */
public class SumOfSquaresCostLayer extends CostLayer {

    private final String type = "Sum of Squares Cost Layer";

    public SumOfSquaresCostLayer(List<Layer> inputs, Layer output) {
        super(inputs, output);
    }

    public SumOfSquaresCostLayer() {
    }

    @Override
    public String getType() {
        return type;
    }
}
