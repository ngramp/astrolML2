import calibobj.*;
import model.Filters;
import model.ObjCType;
import util.FitsUtil;
import nom.tam.fits.Fits;
import nom.tam.fits.FitsException;
import nom.tam.fits.TableHDU;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.prefs.Preferences;

/**
 * Created by Graham Perry on 12/08/16.
 * The Data Sweep index contains all calibrated detections from sdss with a selection of parameters.
 * Each calib object file contains all fields within a single camcol in a single run.
 * Implemented as an iterotor on each object.
 *
 * @author Graham Perry
 */
public class DataSweepIndex implements Iterator<CalibObj> {
    private static final Logger logger = LoggerFactory.getLogger(DataSweepIndex.class);
    private static final Preferences prefs = Preferences.userRoot().node("PhotoSweep");
    private final String sdssData = "https://data.sdss.org/sas/dr12/env/";
    private final String folder = "PHOTO_SWEEP";
    private Iterator<CamcolIndex> camcolIndex;
    private Iterator<CalibObj> currentCamcol;
    private ObjCType type;
    private boolean primary;
    private Filters filters = new Filters(prefs);

    DataSweepIndex() {
        if(filters.getResolveFilters().get("surveyPrimary") != null){
            primary = filters.getResolveFilters().get("surveyPrimary");
        }else{
            primary = true;
        }
        for(ObjCType type: ObjCType.values()){
            if(type.toString().equals(prefs.get("Object type", ObjCType.GALAXY.toString()))){
                this.type = type;
            }
        }
        download();
    }


    /**
     * downloads and loads the datasweep index
     */
    private void download() {
        String thisType = type.text().toLowerCase();
        if (thisType.equals("galaxy")){
            thisType = thisType.substring(0,3);
        }
        File fitsFile = new File(folder + "/datasweep-index-" + thisType + ".fits");
        try {
            if (!fitsFile.exists()) {
                URL website = new URL(sdssData + fitsFile.toString());
                logger.debug("Downloading " + thisType + " index from SAS");
                FileUtils.copyURLToFile(website, fitsFile);
                logger.debug("Downloaded " + fitsFile);
            }
        } catch (MalformedURLException e) {
            logger.error("Malformed URL");
        } catch (IOException e) {
            logger.error("Input/Output error");
        }
        loadIndexFITS(fitsFile);
    }

    /**
     * downloads and loads one calibobj file one for each camcol+field
     *
     * @param pCamcol the run
     */
    private void download(CamcolIndex pCamcol) {
        String rerun = pCamcol.getRerun();
        String thisType = type.text().toLowerCase();
        if (thisType.equals("galaxy")){
            thisType = thisType.substring(0,3);
        }
        String run = String.format("%06d", pCamcol.getRun());
        String camcol = String.format("%01d", pCamcol.getCamcol());
        String filename = "/" + rerun + "/calibObj-" + run + "-" + camcol + "-" + thisType + ".fits";
        logger.debug(filename);
        File fits = new File(folder + filename);
        if (!fits.exists()) {
            File fitsGZ = new File(folder + filename + ".gz");
            try {
                URL website = new URL(sdssData + fitsGZ.toString());
                logger.debug("Downloading " + fitsGZ.toString() + " from SAS");
                FileUtils.copyURLToFile(website, fitsGZ);
                logger.debug("Downloaded " + fitsGZ.toString());
            } catch (MalformedURLException e) {
                logger.error("Malformed URL");
            } catch (IOException e) {
                logger.error("Input/Output error");
            }
            fits = FitsUtil.decompressGzipFile(fitsGZ);
            FileUtils.deleteQuietly(fitsGZ);
        }
        loadCalibObjFITS(fits);
    }


    /**
     * Loads an index of fields from photosweepindex
     *
     * @param file a downloaded fits file
     */
    private void loadIndexFITS(File file) {
        Set<CamcolIndex> indexSet = new HashSet<>();
        logger.debug("Parsing index from " + file.toString());
        List<PhotoSweepCamcol> ind = new ArrayList<>();
        if (file.exists()) {
            try {
                Fits fits = new Fits(file);
                TableHDU table = (TableHDU) fits.getHDU(1);
                for (int i = 0; i < table.getNRows(); i++) {
                    Object[] row = table.getRow(i);
                    PhotoSweepCamcol photoSweepCamcol = new PhotoSweepCamcol(row, type.code());
                    if (primary) {
                        if (photoSweepCamcol.getNprimary() > 0) {
                            indexSet.add(new CamcolIndex(photoSweepCamcol.getRerun(), photoSweepCamcol.getRun(), photoSweepCamcol.getCamcol()));
                            ind.add(photoSweepCamcol);
                        }
                    } else {
                        indexSet.add(new CamcolIndex(photoSweepCamcol.getRerun(), photoSweepCamcol.getRun(), photoSweepCamcol.getCamcol()));
                        ind.add(photoSweepCamcol);
                    }
                }
                logger.debug("finished parsing " + file.toString() + ", got " + ind.size() + " records");
            } catch (FitsException | IOException e) {
                e.printStackTrace();
            }
        }

        this.camcolIndex = Collections.synchronizedSet(indexSet).iterator();
    }

    /**
     * Loads all calibobjs for a field from field files
     *
     * @param file a downloaded fits file
     */
    private void loadCalibObjFITS(File file) {
        logger.debug("Parsing calibObjs from " + file.toString());
        List<CalibObj> camcol = new ArrayList<>();
        if (file.exists()) {
            try {
                Fits fits = new Fits(file);
                TableHDU table = (TableHDU) fits.getHDU(1);
                for (int i = 0; i < table.getNRows(); i++) {
                    Object[] row = table.getRow(i);
                    CalibObj calibObj;

                    switch (type) {
                        case GALAXY:
                            calibObj = new CalibObjGalaxy(row);
                            break;
                        case STAR:
                            calibObj = new CalibObjStar(row);
                            break;
                        default:
                            calibObj = new CalibObjSky(row);
                            break;
                    }
                    if (filters.matchCalib(calibObj.getCalibStatus()) &&
                            filters.matchResolve(calibObj.getResolveStatus()) &&
                            filters.matchObjType(calibObj.getObjType()) &&
                            filters.matchFlags1(calibObj.getFlags1()) &&
                            filters.matchFlags2(calibObj.getFlags2())) {
                        camcol.add(calibObj);
                    }
                }
                logger.debug("finished parsing " + file.toString() + ", got " + camcol.size() + " records");
            } catch (FitsException | IOException e) {
                e.printStackTrace();
            }
        }
        currentCamcol = Collections.synchronizedList(camcol).iterator();

    }

    private boolean nextCamcol() {
        boolean ret = false;
        if (camcolIndex.hasNext()) {

            final CamcolIndex cam = camcolIndex.next();
            System.out.println(cam.getCamcol() + ": " + cam.getRun());
            download(cam);
            ret = true;
        }
        return ret;
    }

    @Override
    public boolean hasNext() {
        return camcolIndex.hasNext() || currentCamcol.hasNext();
    }

    @Override
    public CalibObj next() {
        CalibObj ret = null;
        if (currentCamcol == null) {
            nextCamcol();
        }
        if (currentCamcol.hasNext()) {
            ret = currentCamcol.next();
        } else {
            if (nextCamcol()) {
                ret = currentCamcol.next();
            }
        }
        return ret;
    }

    private class CamcolIndex {
        private int run;
        private String rerun;
        private int camcol;

        public CamcolIndex(String rerun, int run, int camcol) {

            this.run = run;
            this.rerun = rerun;
            this.camcol = camcol;
        }

        public int getRun() {
            return run;
        }

        public void setRun(int run) {
            this.run = run;
        }

        public String getRerun() {
            return rerun;
        }

        public void setRerun(String rerun) {
            this.rerun = rerun;
        }

        public int getCamcol() {
            return camcol;
        }

        public void setCamcol(int camcol) {
            this.camcol = camcol;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            CamcolIndex that = (CamcolIndex) o;

            if (getRun() != that.getRun()) return false;
            if (getCamcol() != that.getCamcol()) return false;
            return getRerun() != null ? getRerun().equals(that.getRerun()) : that.getRerun() == null;

        }

        @Override
        public int hashCode() {
            int result = getRun();
            result = 31 * result + (getRerun() != null ? getRerun().hashCode() : 0);
            result = 31 * result + getCamcol();
            return result;
        }
    }
}
