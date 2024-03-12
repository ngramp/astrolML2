import javafx.concurrent.Task;
import model.CosObject;
import org.datavec.image.loader.BaseImageLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spi.DataSource;
import spi.ImageSource;

import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

;

/**
 * Created by Graham Perry on 18/07/16.
 *
 * @author Graham Perry
 */
public class TrainingTask extends Task<Void> {
    private static final Logger logger = LoggerFactory.getLogger(TrainingTask.class);
    private final DataSource source;
    private final ImageSource images;
    private final Iterator<CosObject> index;
    private BlockingQueue<CosObject> objQueue;

    //Images are of format given by allowedExtension -
    private static final String [] allowedExtensions = BaseImageLoader.ALLOWED_FORMATS;

    private static final long seed = 12345;

    private static final Random randNumGen = new Random(seed);

    private static int height = 50;
    private static int width = 50;
    private static int channels = 3;
    protected static int numExamples = 80;
    private static int outputNum = 4;

    public TrainingTask(DataSource source, ImageSource images) {
        this.source = source;
        this.images = images;
        logger.debug("Starting new background task");
        this.objQueue = new ArrayBlockingQueue<>(1);
        logger.debug("Getting index");
        //index = source.getIndex();
        index = null;
    }


    @Override
    protected Void call() throws Exception {
        while(index.hasNext()){
            CosObject o = index.next();
        }
      //get source index
        //iterate through index
        //convert to writable
        //get image
        //convert to writable
        return null;
    }
}
