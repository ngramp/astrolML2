package spi;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Graham Perry on 21/08/16.
 *
 * @author Graham Perry
 */
public interface ImageProcessor extends IProvider {
    /**
     * Given an Image, returns an array of processed images
     * @param image The original image
     * @return An arraylist of processed iamges
     */
    ArrayList<Image> process(Image image);
}
