import ij.ImagePlus;
import ij.gui.Roi;
import ij.plugin.RoiRotator;
import ij.process.ImageProcessor;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by Graham Perry on 06/09/16.
 * Obtaining 16 viewpoints from an input image.
 *
 * (a) First, two square-shaped crops are extracted from the image,
 * one at 0◦ (red outline) and one at 45◦ (blue outline).
 * Both are also flipped horizontally to obtain four crops in total.
 *
 * (b) Then, four overlapping corner patches are extracted from each crop,
 * and they are rotated so that the galaxy centre is in the bottom-right
 * corner of each patch. These 16 rotated patches constitute the viewpoints.
 *
 *
 * @author Graham Perry
 */

/**
 * After pre-processing and augmentation, we extracted viewpoints by rotating,
 * flipping and cropping the input images. We extracted 16 different viewpoints
 * for each image: first, two square-shaped crops were extracted from an input
 * image, one at 0◦ and one at 45◦.Both were also flipped horizontally to obtain
 * four crops in total. Each of these crops is 69×69 pixels in size. Then, four
 * overlapping corner patches of 45×45 pixels were extracted from each crop, and
 * rotated so that the centre of the galaxy is in the bottom right corner of each
 * patch. These 16 rotated patches constitute the viewpoints (Fig. 7). This
 * approach allowed us to obtain 16 different viewpoints with just two affine
 * transformation operations, thus avoiding additional computation. All viewpoints
 * can be obtained from the two original crops without interpolation (which in
 * practice are array indexing operations). This also means that image edges and
 * padding have no effect on the input, and that the loss of image fidelity after
 * pre- processing, augmentation and viewpoint extraction is minimal
 */
public class DielemanIP extends Service<ArrayList<BufferedImage>> {
    private ArrayList<BufferedImage> outputs;
    private BufferedImage in;
    DielemanIP(BufferedImage in){
        this.in = in;
        outputs = new ArrayList<>();

    }

    @Override
    protected Task<ArrayList<BufferedImage>> createTask() {
        return new Task<ArrayList<BufferedImage>>() {
            public ArrayList<BufferedImage> call() {
                int width = in.getWidth();
                int height = in.getHeight();
                String title = "Input Image";
                ImagePlus imp = new ImagePlus(title, in);
                ImageProcessor ip = imp.getProcessor();
                Roi roi = new Roi((width / 2) - (69/2), (height / 2) - (69/2), 69, 69); // x, y, width, height of the rectangle
                Roi rot = RoiRotator.rotate(roi, 45);
                ip.setRoi(roi);
                ImageProcessor crop1 = ip.crop();
                ip.resetRoi();
                ip.setRoi(rot);
                ImageProcessor crop2 = ip.crop();
                List<Roi> crops = Arrays.asList(new Roi(0, 0, 45, 45), new Roi(69 - 45, 0, 45, 45), new Roi(0, 69 - 45, 45, 45), new Roi(69 - 45, 69 - 45, 45, 45));
                int progress = 0;
                for (Roi crop : crops) {
                    crop1.setRoi(crop);
                    outputs.add(crop1.crop().getBufferedImage());
                    crop1.resetRoi();
                    updateProgress(++progress,16);
                }
                for (Roi crop : crops) {
                    crop2.setRoi(crop);
                    outputs.add(crop2.crop().getBufferedImage());
                    crop2.resetRoi();
                    updateProgress(++progress,16);
                }
                crop1.flipHorizontal();
                crop2.flipHorizontal();
                for (Roi crop : crops) {
                    crop1.setRoi(crop);
                    outputs.add(crop1.crop().getBufferedImage());
                    crop1.resetRoi();
                    updateProgress(++progress,16);
                }
                for (Roi crop : crops) {
                    crop2.setRoi(crop);
                    outputs.add(crop2.crop().getBufferedImage());
                    crop2.resetRoi();
                    updateProgress(++progress,16);
                }
                return outputs;
            }
        };
    }
}
