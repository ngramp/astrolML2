package spi;

import model.CosObject;

import java.awt.*;

/**
 * Created by Graham Perry on 19/08/16.
 *
 * @author Graham Perry
 */
public interface ImageSource extends IProvider {
    Image getImage(CosObject obj);
}
