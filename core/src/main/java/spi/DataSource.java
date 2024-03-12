package spi;


import java.util.Iterator;

/**
 * Created by Graham Perry on 19/06/16.
 *
 * @author Graham Perry
 */
public interface DataSource extends IProvider {
    /**
     * @return An iterable index of objects within the data source
     */
    Iterator<?> getIndex();
}
