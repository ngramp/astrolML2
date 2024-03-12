import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.FileHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;
/**
 * Created by Graham Perry on 21/08/16.
 *
 * @author Graham Perry
 */
public class KaggleImageUtil {
    private static final Preferences prefs = Preferences.userRoot().node("Kaggle Images");
    private static final Logger logger = LoggerFactory.getLogger(KaggleImageUtil.class);
    //private final File tempfolder;
    private static KaggleImageUtil ourInstance = new KaggleImageUtil();
    public static KaggleImageUtil getInstance() {
        return ourInstance;
    }
    private KaggleImageUtil() {
        //File compressedFile = new File(prefs.get("Training file", System.getProperty("user.home")));
        //tempfolder = unzip(compressedFile);
    }

//    public BufferedImage getImage(String objid){
//        BufferedImage image = null;
//        try {
//            image = ImageIO.read(new File(tempfolder + File.separator + objid + ".jpg"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return image;
//    }
//    public Iterator<BufferedImage> getPreview(){
//
//        ArrayList<BufferedImage> preview = new ArrayList<>();
//        int previewLimit = 20;
//        File[] directoryListing = tempfolder.listFiles();
//        if (directoryListing != null) {
//            logger.info("Found " + directoryListing.length + " files in " + tempfolder.toString());
//            int n = 0;
//            for (File child : directoryListing){
//                n++;
//                try {
//                    preview.add(ImageIO.read(child));
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                if(previewLimit == n){
//                    break;
//                }
//            }
//        }
//        return preview.listIterator();
//    }
        ArrayList<BufferedImage> getPreview(){

        ArrayList<BufferedImage> preview = new ArrayList<>();
        ArrayList<FileHeader> files = new ArrayList<>(previewNames());
        for (FileHeader f : files){
            BufferedImage bim = getImage(f);
            if(bim != null){
                preview.add(bim);

            }
        }
        return preview;
    }
    List<FileHeader> previewNames(){
        String compressedFile = prefs.get("Training file", System.getProperty("user.home"));
        ZipFile zipFile = null;
        ArrayList<FileHeader> headers = new ArrayList<>();
        try {
            zipFile = new ZipFile(compressedFile);
            headers.addAll(zipFile.getFileHeaders());
        } catch (ZipException e) {
            e.printStackTrace();
        }
        return headers.subList(0,20);
    }
    BufferedImage getImage(String objid){
        return null;
    }

    BufferedImage getImage(FileHeader header){
        String compressedFile = prefs.get("Training file", System.getProperty("user.home"));
        String temppath = System.getProperty("java.io.tmpdir");
        String fileName = header.getFileName();
        BufferedImage image = null;
        File newFile = null;
        try {
            ZipFile zipFile = new ZipFile(compressedFile);
            zipFile.extractFile(header, temppath);
            newFile = new File(temppath, fileName);
            if(newFile.exists() && newFile.getName().contains("jpg")){
                logger.info(newFile.getAbsolutePath());
                image = ImageIO.read(newFile);
            }
        } catch (ZipException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(newFile != null && newFile.exists()){
                newFile.deleteOnExit();
            }
        }

        return image;
    }
    static File unzip(File source){
        //String source = "some/compressed/file.zip";
        File destination = new File(System.getProperty("user.home") + File.separator  + "kaggletemp" + File.separator);
        //String destination = "some/destination/folder";
        String password = "password";

        try {
             ZipFile zipFile = new ZipFile(source);
             if (zipFile.isEncrypted()) {
                zipFile.setPassword(password);
             }
             zipFile.extractAll(destination.getAbsolutePath());
        } catch (ZipException e) {
            e.printStackTrace();
        }
        File ret = new File(destination.getAbsolutePath() + File.separator + source.getName());
        logger.info(ret.toString());
        return ret;
    }
}
