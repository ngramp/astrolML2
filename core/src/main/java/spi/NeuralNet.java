package spi;

import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;
import org.nd4j.linalg.dataset.api.iterator.MultiDataSetIterator;

/**
 * Created by Graham Perry on 21/08/16.
 *
 * @author Graham Perry
 */
public interface NeuralNet extends IProvider {
    boolean initialise(DataSetIterator training, DataSetIterator testing);
    boolean initialise(MultiDataSetIterator training, MultiDataSetIterator testing);
    boolean start();
    boolean stop();
}
