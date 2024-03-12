package util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;

/**
 * Utility for converting FITS data types to Java data types
 * Created by Graham Perry on 17/06/16.
 *
 * @author Graham Perry
 */
public class FitsUtil {
    private static final Logger logger = LoggerFactory.getLogger(FitsUtil.class);

    public static File
    decompressGzipFile(File gzipFile) {
        logger.trace("decompressing fits");
        File fit = null;
        String filename = gzipFile.toString().substring(0, gzipFile.toString().length() - 3);
        File fitsFile = new File(filename);
        try {
            FileInputStream fis = new FileInputStream(gzipFile);
            GZIPInputStream gis = new GZIPInputStream(fis);
            FileOutputStream fos = new FileOutputStream(fitsFile);
            byte[] buffer = new byte[1024];
            int len;
            while ((len = gis.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
            }
            //close resources
            fos.close();
            gis.close();
            fit = new File(filename);

        } catch (IOException e) {
            System.out.println("IO error");
        }
        return fit;
    }

    public int toInt(Object item) {

        logger.trace("creating int");
        return ((int[]) item)[0];
    }

    public int uInt(Object item) {
        logger.trace("creating unsigned int");
        byte[] bimg = (byte[]) item;
        for (int i = 0; i < bimg.length; i += 1) {
            bimg[i] = (byte) (bimg[i] & 0xFF);
        }
        return (int) bimg[0];
    }

    public short uShort(Object item) {
        logger.trace("creating unsigned short");
        byte[] bimg = (byte[]) item;
        for (int i = 0; i < bimg.length; i += 1) {
            bimg[i] = (byte) (bimg[i] & 0xFF);
        }
        return (short) bimg[0];
    }

    public String toString(Object item) {

        logger.trace("creating string");
        return (String) item;
    }

    public float toFloat(Object item) {

        logger.trace("creating float");
        return ((float[]) item)[0];
    }

    public short toShort(Object item) {
        logger.trace("creating short " + ((short[]) item)[0]);
        return ((short[]) item)[0];
    }

    public double toDouble(Object item) {
        logger.trace("creating double");
        return ((double[]) item)[0];
    }

    public List<Float> floatList(Object items) {
        logger.trace("creating float list");
        List<Float> floatList = new ArrayList<Float>();
        float[] floats = (float[]) items;
        for (int index = 0; index < floats.length; index++) {
            floatList.add(floats[index]);
        }
        return floatList;
    }

    public List<Integer> intList(Object items) {
        logger.trace("creating int list");
        List<Integer> intList = new ArrayList<Integer>();
        int[] ints = (int[]) items;
        for (int index = 0; index < ints.length; index++) {
            intList.add(ints[index]);
        }
        return intList;
    }

    public List<Short> shortList(Object items) {
        logger.trace("creating short list");
        List<Short> shortList = new ArrayList<Short>();
        short[] shorts = (short[]) items;
        for (short index = 0; index < shorts.length; index++) {
            shortList.add(shorts[index]);
        }
        return shortList;
    }

    public float[] floatArray(Object items) {
        logger.trace("creating float array");

        float[] floats = (float[]) items;
        return floats;
    }

    public double[] doubleArray(Object items) {
        logger.trace("creating double array");

        double[] doubles = (double[]) items;
        return doubles;
    }

    public int[] intArray(Object items) {
        logger.trace("creating int array");
        int[] ints = (int[]) items;
        return ints;
    }

    public short[] shortArray(Object items) {
        logger.trace("creating short array");
        short[] shorts = (short[]) items;
        return shorts;
    }
}
