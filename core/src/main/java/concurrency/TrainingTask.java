package concurrency;

import model.CosObject;
import spi.DataSource;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by Graham Perry on 18/07/16.
 *
 * @author Graham Perry
 */
public class TrainingTask extends Task<Void> {
    private static final Logger logger = LoggerFactory.getLogger(TrainingTask.class);
    private final DataSource source;
    private final Iterator index;
    private BlockingQueue<CosObject> objQueue;
    private ObservableList<Event> events;

    public TrainingTask(DataSource source) {
        this.source = source;
        logger.debug("Starting new background task");
        this.objQueue = new ArrayBlockingQueue<>(1);
        events = FXCollections.observableList(new ArrayList<Event>()); //not good to use observable in api - coupling
        logger.debug("Getting index");
        index = source.getIndex();
    }

    public ObservableList<Event> getEvents() {
        return events;
    }

    public Void call() {
        ObjectProducer producer = new ObjectProducer(objQueue);
        ObjectComsumer consumer = new ObjectComsumer(objQueue);
        new Thread(producer).start();
        new Thread(consumer).start();
        return null;
    }

    private class ObjectProducer implements Runnable {
        private final BlockingQueue<CosObject> queue;

        ObjectProducer(BlockingQueue q) {
            queue = q;
        }

        public void run() {
            try {
                synchronized (index) {
                    while (index.hasNext()) {
                        CosObject obj = (CosObject) index.next();
                        if (obj == null) {
                            logger.error("null object");
                        }
                        logger.debug("Queueing Object " + obj.toString());
                        //events.add(new Event((new Date()).toString(), "Queueing Object " + obj.toString()));
                        queue.put(obj);
                    }
                }

            } catch (InterruptedException e) {
                logger.error("ObjectProducer interrupted");
                e.printStackTrace();
            }
        }

    }

    private class ObjectComsumer implements Runnable {
        private final BlockingQueue<CosObject> queue;

        ObjectComsumer(BlockingQueue q) {
            queue = q;
        }

        public void run() {
            try {
                while (true) {
                    logger.debug("Processing object");
                    //events.add(new Event((new Date()).toString(), "Processing object"));
                    consume(queue.take());
                }
            } catch (InterruptedException e) {
                logger.error("ObjectConsumer interrupted");
                e.printStackTrace();
            }
        }

        private void consume(CosObject obj) {
            //obj.getJPGImage().saveImage();
        }
    }


}
