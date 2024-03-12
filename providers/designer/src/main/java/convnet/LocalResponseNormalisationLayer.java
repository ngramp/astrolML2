package convnet;

import java.util.List;

/**
 * Created by gram on 14/04/16.
 */
public class LocalResponseNormalisationLayer extends NormalisationLayer {
    private final String type = "Local Response Normalisation Layer";

    public LocalResponseNormalisationLayer(List<Layer> inputs, Layer output, int channels, int size) {
        super(inputs, output, channels, size);
    }

    public LocalResponseNormalisationLayer() {
    }

    @Override
    public String getType() {
        return type;
    }
}
