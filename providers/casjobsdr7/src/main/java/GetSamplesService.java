import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import model.Sample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

;

/**
 * Created by gram on 29/03/16.
 */
public class GetSamplesService extends Service<ArrayList<BufferedImage>> {
    private static final Logger logger = LoggerFactory.getLogger(GetSamplesService.class);
    private final int width, height;
    private final List<Sample> samples;
    private ImagesController controller;
    // Uses Java 7 diamond operator

    GetSamplesService(ImagesController that, List<Sample> samples, int h, int w) {
        this.samples = new ArrayList<>(samples);
        this.height = h;
        this.width = w;
        this.controller = that;
    }

    private BufferedImage createImageFromBytes(byte[] imageInByte) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(imageInByte);
        return ImageIO.read(bais);
    }
    @Override
    protected Task<ArrayList<BufferedImage>> createTask() {
        return new Task<ArrayList<BufferedImage>>() {
            public ArrayList<BufferedImage> call() {

                ArrayList<BufferedImage> images = new ArrayList<>();
                //TODO:check img service working
                int index = 0;
                CasJobsDR7 cas = new CasJobsDR7();
                for (Sample s : samples) {
                    BufferedImage bif = cas.getImage(s);
                    if(bif != null){
                        final BufferedImage fbif = bif;
                        images.add(fbif);
                        Platform.runLater(() ->
                            controller.updateSampleView(fbif)
                        );
                    }
                    updateProgress(++index, samples.size());
                }
                return images;

            }
        };
    }
}