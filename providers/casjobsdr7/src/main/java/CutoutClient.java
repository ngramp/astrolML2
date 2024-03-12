import cutoutservices.ImgCutoutLocator;
import cutoutservices.ImgCutoutSoap_PortType;
import ij.ImagePlus;
import ij.process.ImageProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * Created by Graham Perry on 08/09/16.
 *
 * @author Graham Perry
 */
public class CutoutClient {
    private static final Logger logger = LoggerFactory.getLogger(CutoutClient.class);
    final int width;
    final int height;
    private final ImgCutoutLocator loc = new ImgCutoutLocator();
    private ImgCutoutSoap_PortType imgProvider = null;
    private final int maxTries;
    private final float maxLum;
    private final float startSize;
    private final float stepSize;
    private final double maxP;
    private final int outlineSize;
    private final int imageSize;

    CutoutClient(int width, int height, int maxTries, float maxLum, double maxP, float startSize, float stepSize){
        this.width =  width;
        this.height =  height;
        this.maxTries = maxTries;
        this.maxLum = maxLum;
        this.maxP = maxP;
        this.outlineSize = (width*2)+(height*2)-2; //substract overlapping pixels
        this.imageSize = width*height;
        this.startSize = startSize;
        this.stepSize = stepSize;

    }
    BufferedImage getImage(double ra, double dec){
        byte[] imageInByte = null;
        double size = startSize;
        int tries = 0;
        double threshold = outlineSize * maxP;
        BufferedImage out =null;
        boolean success = false;
        float[] pixelValues = new float[outlineSize];
        try {
            imgProvider = loc.getImgCutoutSoap();
            do{
                imageInByte = imgProvider.getJpeg(ra, dec, size, width, height, "");
                out = createImageFromBytes(imageInByte);
                ImagePlus imp = new ImagePlus("", out);
                ImageProcessor ip = imp.getProcessor();
                //get the value of the pixels on the edge
                for(int i=0,n=0;i<imageSize;i++){ //does luminance not greyscale
                    //ip.getPixelValue(x, y) origin top-left (0,0)
                    /*
                    0 (0,0) 1  2  3  4 (4,0)
                    5 (0,1) 6  7  8  9 (4,1)
                    10(0,2) 11 12 13 14(4,2)
                    15(0,3) 16 17 18 19(4,3)
                    */
                    if (i < width) {
                        pixelValues[n++] = ip.getPixelValue(i, 0); //top edge
                    } else if (i > imageSize - width) {
                        pixelValues[n++] = ip.getPixelValue(i - (imageSize - width), height-1); //bottom edge
                    } else if (i % width == 0) { //5,10,15 etc
                        pixelValues[n++] = ip.getPixelValue(0, i/width); //left edge
                    } else if ((i+1) % width  == 0) { //4,9,14 etc
                        pixelValues[n++] = ip.getPixelValue(width-1, ((i+1)/width)-1); //right edge
                    }

                }
                //find the proportion above set luminance
                int i = 0;
                int n = 0;
                while(i < threshold && n < outlineSize) {
                    if (pixelValues[n++] > maxLum) {
                        i++;
                    }
                }
                logger.info(i + " out of " + outlineSize);
                if(i >= threshold){ //too near
                    tries++;
                    if(!(tries < maxTries)){
                        logger.info("zooming out to " + (size + stepSize));
                        size = size+stepSize;
                    }
                    else{
                        logger.info("Giving up.");
                    }

                }
                else{
                    //it'll do
                    success = true;
                }


            }while(!success && tries < maxTries);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return out;
    }
    private BufferedImage createImageFromBytes(byte[] imageInByte) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(imageInByte);
        return ImageIO.read(bais);
    }

}
