import model.Filters;
import nom.tam.fits.Fits;
import nom.tam.fits.FitsException;
import nom.tam.fits.TableHDU;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import photoobj.PhotoField;
import photoobj.PhotoObj;
import photoobj.PhotoRun;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.prefs.Preferences;

/**
 * Created by Graham Perry on 12/08/16.
 *
 * @author Graham Perry
 */
class PhotoRunAll implements Iterator<PhotoObj> {
    private static final Logger logger = LoggerFactory.getLogger(PhotoRunAll.class);
    private static final Preferences prefs = Preferences.userRoot().node("PhotoObj");
    private final String sdssData = "https://data.sdss.org/sas/dr12/env/";
    private final String folder = "BOSS_PHOTOOBJ";
    private final Filters filters = new Filters(prefs);
    private Iterator<PhotoRun> index; //the index of all runs
    private Iterator<PhotoField> runIterator; //the index of all fields
    private Iterator<PhotoObj> objIterator; //the index of all objects
    private PhotoRun currentRun;
    private PhotoField currentField;
    private PhotoObj currentObject;
    private List<Integer> camcolNumbers;
    private Iterator<Integer> camcolIterator; //the index of all camcols


    PhotoRunAll() {

        camcolNumbers = new ArrayList<Integer>();
        camcolNumbers.add(1);
        camcolNumbers.add(2);
        camcolNumbers.add(3);
        camcolNumbers.add(4);
        camcolNumbers.add(5);
        camcolNumbers.add(6);
        camcolIterator = camcolNumbers.listIterator();
        download();
        initialise();
    }


    /**
     * downloads and loads the photoRunAll index
     */
    private synchronized void download() {
        File fitsFile = new File(folder + "/photoRunAll-dr12.fits");
        File temp = new File(folder + "/temp");
        try {
            if (!fitsFile.exists()) {
                URL website = new URL(sdssData + fitsFile.toString());
                logger.debug("Downloading photoRunAll-dr12 index from SAS");
                FileUtils.copyURLToFile(website, temp);
                logger.debug("Downloaded " + fitsFile);
                FileUtils.copyFile(temp, fitsFile);
                FileUtils.forceDelete(temp);
            }
        } catch (MalformedURLException e) {
            logger.error("Malformed URL");
        } catch (IOException e) {
            logger.error("Input/Output error");
        }
        loadIndexFITS(fitsFile);
    }

    /**
     * downloads and loads a field files for a single camcol which index photoobj files in a single run
     *
     * @param pRun the run
     */
    private synchronized void download(PhotoRun pRun, int camcol) {
        String rerun = pRun.getRerun();
        String run = String.format("%06d", pRun.getRun());
        File temp = new File(folder + "/temp");
        File fitsFile = new File(folder + "/" + rerun + "/" + pRun.getRun() + "/photoField-" + run + "-" + camcol + ".fits");
        try {
            if (!fitsFile.exists()) {
                URL website = new URL(sdssData + fitsFile.toString());
                logger.debug("Downloading " + fitsFile + " from SAS");
                FileUtils.copyURLToFile(website, temp);
                logger.debug("Downloaded " + fitsFile);
                FileUtils.copyFile(temp, fitsFile);
                FileUtils.forceDelete(temp);
            }
        } catch (MalformedURLException e) {
            logger.error("Malformed URL");
        } catch (IOException e) {
            logger.error("Input/Output error");
        }


        loadPhotoFieldFITS(fitsFile);
    }

    /**
     * downloads a single photoobj file containing photoobjs from a field+camcol
     *
     * @param pField the field
     */
    private void download(PhotoField pField) {
        String rerun = pField.getRerun();
        String run = String.format("%06d", pField.getRun());
        String camcol = String.format("%01d", pField.getCamcol());
        String frame = String.format("%04d", pField.getField());
        File temp = new File(folder + "/temp");
        File fitsFile = new File(folder + "/" + rerun + "/" + pField.getRun() + "/" + camcol + "/photoObj-" + run + "-" + camcol + "-" + frame + ".fits");
        if (!fitsFile.exists()) {
            try {
                URL website = new URL(sdssData + fitsFile.toString());
                logger.debug("Downloading " + fitsFile + " from SAS");
                FileUtils.copyURLToFile(website, temp);
                logger.debug("Downloaded " + fitsFile);
                FileUtils.copyFile(temp, fitsFile);
                FileUtils.forceDelete(temp);
            } catch (MalformedURLException e) {
                logger.error("Malformed URL");
            } catch (IOException e) {
                logger.error("Input/Output error");
            }
        }
        loadPhotoObjFITS(fitsFile);
    }


    /**
     * Loads an index of runs from photoRunAll
     *
     * @param file a downloaded fits file
     */
    private synchronized void loadIndexFITS(File file) {
        logger.debug("Parsing index from FITS file");
        List<PhotoRun> ind = new ArrayList<>();
        if (file.exists()) {
            try {
                Fits fits = new Fits(file);
                TableHDU table = (TableHDU) fits.getHDU(1);
                logger.debug("Index contains " + table.getNRows() + " rows");
                for (int i = 0; i < table.getNRows(); i++) {
                    Object[] row = table.getRow(i);

                    logger.trace("processing row " + i + " with " + row.length + " columns");
                    PhotoRun photoRun = new PhotoRun(row);
                    ind.add(photoRun);
                    logger.trace("Processed " + i + " rows");
                }
                //runIterator = field.iterator();
                logger.debug("finished parsing " + file);
            } catch (FitsException | IOException e) {
                e.printStackTrace();
            }
        }
        this.index = ind.iterator();
    }

    /**
     * Loads an index of Fields from field files
     *
     * @param file a downloaded fits file
     */
    private synchronized void loadPhotoFieldFITS(File file) {
        logger.debug("Parsing fields from FITS file");
        List<PhotoField> run = new ArrayList<>();
        if (file.exists()) {
            try {
                Fits fits = new Fits(file);
                TableHDU table = (TableHDU) fits.getHDU(1);
                for (int i = 0; i < table.getNRows(); i++) {
                    Object[] row = table.getRow(i);
                    PhotoField photoField = new PhotoField(row);
                    if (photoField.getnTotal() > 0) {
                        run.add(photoField);
                    }
                }
                logger.debug("finished parsing " + file);
            } catch (FitsException | IOException e) {
                e.printStackTrace();
            }
        }
        runIterator = run.iterator();

    }

    /**
     * Loads detected objects in photoObj files
     *
     * @param file a downloaded fits file
     */
    private synchronized void loadPhotoObjFITS(File file) {
        logger.debug("Getting objects from FITS file");
        List<PhotoObj> field = new ArrayList<>();
        if (file.exists()) {
            try {
                Fits fits = new Fits(file);
                TableHDU table = (TableHDU) fits.getHDU(1);
                for (int i = 0; i < table.getNRows(); i++) {
                    Object[] row = table.getRow(i);
                    PhotoObj photoObj = new PhotoObj(row);
                    if (filters.matchCalib(photoObj.getCalibStatus()) &&
                            filters.matchResolve(photoObj.getResolveStatus()) &&
                            filters.matchObjType(photoObj.getObjType()) &&
                            filters.matchFlags1(photoObj.getFlags1()) &&
                            filters.matchFlags2(photoObj.getFlags2())) {
                        field.add(photoObj);
                    }

                }
                logger.debug("finished parsing " + file);
            } catch (FitsException | IOException e) {
                e.printStackTrace();
            }
        }
        objIterator = field.iterator();
    }
    //fields then camcols then runs

    private void nextCamcolOrRun() {
        if (camcolIterator.hasNext()) {
            download(currentRun, camcolIterator.next());
        } else if (index.hasNext()) {
            currentRun = index.next();
            camcolIterator = camcolNumbers.listIterator(); //reset camcol iterator
            download(currentRun, camcolIterator.next());
        }

    }

    private void nextField() {
        if (runIterator == null) {
            initialise();
        } else if ((!runIterator.hasNext() || !camcolIterator.hasNext())) {
            nextCamcolOrRun();
        }
        download(runIterator.next());
    }

    private void initialise() {
        currentRun = index.next();
        download(currentRun, camcolIterator.next());
    }

    @Override
    public boolean hasNext() {
        return index.hasNext() || runIterator.hasNext() || objIterator.hasNext() || camcolIterator.hasNext();
    }

    @Override
    public PhotoObj next() {
        PhotoObj thisObj;
        if (objIterator == null) {
            nextField();
            logger.info(objIterator.toString());
        }
        while (!objIterator.hasNext()) { //skip empty fields (which result from primary only detections)
            nextField();
        }
        thisObj = objIterator.next();
        return thisObj;
    }
}
