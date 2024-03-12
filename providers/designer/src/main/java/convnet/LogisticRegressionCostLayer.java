package convnet;

import java.util.List;

/**
 * Created by gram on 14/04/16.
 */
public class LogisticRegressionCostLayer extends CostLayer {
    private final String type = "Logistic Regression Cost Layer";

    public LogisticRegressionCostLayer(List<Layer> inputs, Layer output) {
        super(inputs, output);
    }

    public LogisticRegressionCostLayer() {
    }

    @Override
    public String getType() {
        return type;
    }
}
