package photoobj;

import util.FitsUtil;

/**
 * Created by Graham Perry on 13/07/16.
 *
 * @author Graham Perry
 *         https://data.sdss.org/datamodel/files/BOSS_PHOTOOBJ/RERUN/RUN/photoField.html
 */
public class PhotoField {
    private String fieldID; //char[19]; //Field identifier composed of [run, camcol, field, rerun, skyversion].
    private short skyVersion; //int8; //Layer of catalog (currently only one layer, 0)
    private int run; //int32; //Run number
    private String rerun; //char[3]; //Rerun number
    private int camcol; //int32; //Camera column
    private int field; //int32; //Field number
    private short nTotal; //int16; //Number of objects
    private short nObjects; //int16; //Number of (non-BRIGHT) objects in the field
    private short nChild; //int16; //Number of "child" objects in the field
    private short ngals; //int16; //Number of objects classified as "galaxy"
    private short nStars; //int16; //Number of objects classified as "star"
    private short[] nCR; //int16[5]; //Number of cosmics
    private short[] nBrightObj; //int16[5]; //Number of bright objects
    private short[] nFaintObj; //int16[5]; //Number of faint objects
    private int quality; //int32; //Quality of field
    private double[] mjd; //float64[5]; //MJD(TAI) when row 0 of u-band field was read
    private float[] a; //float32[5]; //a term in astrometry
    private float[] b; //float32[5]; //b term in astrometry
    private float[] c; //float32[5]; //c term in astrometry
    private float[] d; //float32[5]; //d term in astrometry
    private float[] e; //float32[5]; //e term in astrometry
    private float[] ff; //float32[5]; //f term in astrometry
    private float[] dRow0; //float32[5]; //Zero-order row distortion coefficient
    private float[] dRow1; //float32[5]; //First-order row distortion coefficient
    private float[] dRow2; //float32[5]; //Second-order row distortion coefficient
    private float[] dRow3; //float32[5]; //Third-order row distortion coefficient
    private float[] dCol0; //float32[5]; //Zero-order column distortion coefficient
    private float[] dCol1; //float32[5]; //First-order column distortion coefficient
    private float[] dCol2; //float32[5]; //Second-order column distortion coefficient
    private float[] dCol3; //float32[5]; //Third-order column distortion coefficient
    private float[] csRow; //float32[5]; //Slope in row DCR correction for blue objects
    private float[] csCol; //float32[5]; //Slope in column DCR correction for blue objects
    private float[] ccRow; //float32[5]; //Constant row DCR correction for blue objects
    private float[] ccCol; //float32[5]; //Constant column DCR correction for blue objects
    private float[] riCut; //float32[5]; //r-i cutoff between blue and red objects
    private float[] airmass; //float32[5]; //Airmass for star at frame center (midway through u-band exposure) (mag)
    private float[] muErr; //float32[5]; //Error in mu in astrometric calibration (arcsec)
    private float[] nuErr; //float32[5]; //Error in nu in astrometric calibration (arcsec)
    private float[] pixScale; //float32[5]; //Mean size of pixel (r-band) (arcsec)
    private double ra; //float64
    private double dec; //float64
    private double CX; //float64
    private double CY; //float64
    private double CZ; //float64
    private double raMin; //float64; //Minimum ra of field (deg)
    private double raMax; //float64; //Maximum ra of field (deg)
    private double decMin; //float64; //Minimum dec of field (deg)
    private double decMax; //float64; //Maximum dec of field (deg)
    private double primaryArea; //float64; //Area covered by primary part of field (deg)
    private float[] rowOffset; //float32[5]; //Offset to add to transformed row coordinates (pix)
    private float[] colOffset; //float32[5]; //Offset to add to transformed row coordinates (pix)
    private int[] saturation_level; //int32[5]; //Saturation level (counts)
    private float[] neff_psf; //float32[5]; //Effective area of PSF (pix)
    private float[] sky_psp; //float32[5]; //Sky estimate from PSP (nmgy)
    private float[] sky_frames; //float32[5]; //Frames sky value (nmgy)
    private float[] sky_frames_sub; //float32[5]; //Frames sky value after object subtraction (nmgy)
    private float[] sigPix; //float32[5]; //Sigma of pixel values frame (clipped) (nmgy)
    private float[] dev_ap_correction; //float32[5]; //deV aperture correction
    private float[] dev_ap_correctionerr; //float32[5]; //Error in deV aperture correction
    private float[] exp_ap_correction; //float32[5]; //exponential aperture correction
    private float[] exp_ap_correctionerr; //float32[5]; //Error in exponential aperture correction
    private float[] dev_model_ap_correction; //float32[5]; //model aperture correction, for deV case
    private float[] dev_model_ap_correctionerr; //float32[5]; //Error in model aperture correction, for deV case
    private float[] exp_model_ap_correction; //float32[5]; //model aperture correction, for exponential case
    private float[] exp_model_ap_correctionerr; //float32[5]; //Error in model aperture correction, for exponential case
    private float[] median_fibercolor; //float32; //Median fiber magnitude of objects
    private float[] median_psfcolor; //float32; //Median PSF magnitude of objects
    private float[] q; //float32[5]; //Means Stokes Q parameter frame
    private float[] u; //float32[5]; //Means Stokes U parameter frame
    private float[] sky; //float32[5]; //PSP estimate of sky (nmgy)
    private float[] skySig; //float32[5]; //Fractional Sigma of Sky Value Distribution, expressed as magnitude. Sky noise = skySig * sky * ln(10)/2.5 (mag)
    private float[] skyErr; //float32[5]; //Error in PSP estimate of sky (nmgy)
    private float[] skySlope; //float32[5]; //Rate of change in sky value along columns (nmgy)
    private float[] lbias; //float32[5]; //Left-hand amplifier bias level XXX make sure to apply DSCALE, counts or ADU? (ADUs)
    private float[] rbias; //float32[5]; //Right-hand amplifier bias level (ADUs)
    private int[] psf_nstar; //int32[5]; //Number of stars used in PSF measurement
    private float[] psf_ap_correctionerr; //float32[5]; //Photometric uncertainty due to imperfect PSF model (mag)
    private float[] psf_sigma1; //float32[5]; //Inner gaussian sigma for composite fit (arcsec)
    private float[] psf_sigma2; //float32[5]; //Outer gaussian sigma for composite fit (arcsec)
    private float[] psf_b; //float32[5]; //Ratio of inner to outer components at origin (composite fit)
    private float[] psf_p0; //float32[5]; //Value of power law at origin in composite fit XXX
    private float[] psf_beta; //float32[5]; //Slope of power law in composite fit
    private float[] psf_sigmap; //float32[5]; //Width of power law in composite fit
    private float[] psf_width; //float32[5]; //Effective PSF width from 2-Gaussian fit (arcsec)
    private float[] psf_psfcounts; //float32[5]; //Flux via fit to PSF XXX (counts)
    private float[] psf_sigma1_2g; //float32[5]; //Sigma of inner gaussian in 2-Gaussian fit (arcsec)
    private float[] psf_sigma2_2g; //float32[5]; //Sigma of outer gaussian in 2-Gaussian fit (arcsec)
    private float[] psf_b_2g; //float32[5]; //Ratio of inner to outer components at origin (2-Gaussian fit)
    private float[] psfCounts; //float32[5]; //PSF counts XXX (counts)
    private int[] psf_nprof; //int32[5]
    private float[][] psf_mean_nmgy; //float32[15,5]
    private float[][] psf_med_nmgy; //float32[15,5]
    private float[][] psf_sig_nmgy; //float32[15,5]
    private float[] gain; //float32[5]; //Gain averaged over amplifiers (electrons)
    private float[] dark_variance; //float32[5]; //Dark variance
    private float score; //float32; //Quality score of field (0-1)
    private float[] aterm; //float32[5]; //nanomaggies per count due to instrument (nmgy)
    private float[] kterm; //float32[5]; //atmospheric k-term at reference time in calibration
    private float[] kdot; //float32[5]; //linear time variation of atmospheric k-term (1)
    private double[] ref_tai; //float64[5]; //reference TAI used for k-term (second)
    private double[] tai; //float64[5]; //TAI used for k-term (second)
    private int psp_status; //int32; //Maximum value of status over all 5 filters
    private int photo_status; //int32; //Frames processing status bitmask (-1: UNKNOWN, 0: OK, 1: ABORTED; 2:MISSING, 3:TOO_LONG); from fpFieldStat file)
    private int[] image_status; //int32[5]; //image status bitmask
    private int[] calib_status; //int32[5]; //calibration status bitmask
    private int[] nstars_offset; //int32[5]; //number of stars used for fieldOffset determination
    private float[] field_offset; //int32[5]; //offset of field from initial calibration (final minus initial magnitudes) (mag)
    private float[] nMgyPerCount; //float32[5]; //nanomaggies per count (nmgy)
    private float[] nMgyPerCount_Ivar; //float32[5]; //Inverse variance of nanomaggies per count ()
    private int ifield; //int32; //field id used by resolve pipeline
    private double mu_start; //float64; //start of field in stripe coords (parallel to scan direction)
    private double mu_end; //float64; //end of field in stripe coords (parallel to scan direction)
    private double nu_start; //float64; //start of field in stripe coords (perpendicular to scan direction)
    private double nu_end; //float64; //end of field in stripe coords (perpendicular to scan direction)
    private int ifindx; //int32; //first entry for this field in the window_findx.fits file matching the fields to balkans
    private int nbalkans; //int32; //number of balkans contained in this field (and corresponding number of entries in window_findx.fits

    public PhotoField(String fieldID, short skyVersion, int run, String rerun, int camcol, int field, short nTotal, short nObjects, short nChild, short ngals, short nStars, short[] nCR, short[] nBrightObj, short[] nFaintObj, int quality, double[] mjd, float[] a, float[] b, float[] c, float[] d, float[] e, float[] ff, float[] dRow0, float[] dRow1, float[] dRow2, float[] dRow3, float[] dCol0, float[] dCol1, float[] dCol2, float[] dCol3, float[] csRow, float[] csCol, float[] ccRow, float[] ccCol, float[] riCut, float[] airmass, float[] muErr, float[] nuErr, float[] pixScale, double ra, double dec, double CX, double CY, double CZ, double raMin, double raMax, double decMin, double decMax, double primaryArea, float[] rowOffset, float[] colOffset, int[] saturation_level, float[] neff_psf, float[] sky_psp, float[] sky_frames, float[] sky_frames_sub, float[] sigPix, float[] dev_ap_correction, float[] dev_ap_correctionerr, float[] exp_ap_correction, float[] exp_ap_correctionerr, float[] dev_model_ap_correction, float[] dev_model_ap_correctionerr, float[] exp_model_ap_correction, float[] exp_model_ap_correctionerr, float[] median_fibercolor, float[] median_psfcolor, float[] q, float[] u, float[] sky, float[] skySig, float[] skyErr, float[] skySlope, float[] lbias, float[] rbias, int[] psf_nstar, float[] psf_ap_correctionerr, float[] psf_sigma1, float[] psf_sigma2, float[] psf_b, float[] psf_p0, float[] psf_beta, float[] psf_sigmap, float[] psf_width, float[] psf_psfcounts, float[] psf_sigma1_2g, float[] psf_sigma2_2g, float[] psf_b_2g, float[] psfCounts, int[] psf_nprof, float[][] psf_mean_nmgy, float[][] psf_med_nmgy, float[][] psf_sig_nmgy, float[] gain, float[] dark_variance, float score, float[] aterm, float[] kterm, float[] kdot, double[] ref_tai, double[] tai, int psp_status, int photo_status, int[] image_status, int[] calib_status, int[] nstars_offset, float[] field_offset, float[] nMgyPerCount, float[] nMgyPerCount_Ivar, int ifield, double mu_start, double mu_end, double nu_start, double nu_end, int ifindx, int nbalkans) {
        this.fieldID = fieldID;
        this.skyVersion = skyVersion;
        this.run = run;
        this.rerun = rerun;
        this.camcol = camcol;
        this.field = field;
        this.nTotal = nTotal;
        this.nObjects = nObjects;
        this.nChild = nChild;
        this.ngals = ngals;
        this.nStars = nStars;
        this.nCR = nCR;
        this.nBrightObj = nBrightObj;
        this.nFaintObj = nFaintObj;
        this.quality = quality;
        this.mjd = mjd;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.ff = ff;
        this.dRow0 = dRow0;
        this.dRow1 = dRow1;
        this.dRow2 = dRow2;
        this.dRow3 = dRow3;
        this.dCol0 = dCol0;
        this.dCol1 = dCol1;
        this.dCol2 = dCol2;
        this.dCol3 = dCol3;
        this.csRow = csRow;
        this.csCol = csCol;
        this.ccRow = ccRow;
        this.ccCol = ccCol;
        this.riCut = riCut;
        this.airmass = airmass;
        this.muErr = muErr;
        this.nuErr = nuErr;
        this.pixScale = pixScale;
        this.ra = ra;
        this.dec = dec;
        this.CX = CX;
        this.CY = CY;
        this.CZ = CZ;
        this.raMin = raMin;
        this.raMax = raMax;
        this.decMin = decMin;
        this.decMax = decMax;
        this.primaryArea = primaryArea;
        this.rowOffset = rowOffset;
        this.colOffset = colOffset;
        this.saturation_level = saturation_level;
        this.neff_psf = neff_psf;
        this.sky_psp = sky_psp;
        this.sky_frames = sky_frames;
        this.sky_frames_sub = sky_frames_sub;
        this.sigPix = sigPix;
        this.dev_ap_correction = dev_ap_correction;
        this.dev_ap_correctionerr = dev_ap_correctionerr;
        this.exp_ap_correction = exp_ap_correction;
        this.exp_ap_correctionerr = exp_ap_correctionerr;
        this.dev_model_ap_correction = dev_model_ap_correction;
        this.dev_model_ap_correctionerr = dev_model_ap_correctionerr;
        this.exp_model_ap_correction = exp_model_ap_correction;
        this.exp_model_ap_correctionerr = exp_model_ap_correctionerr;
        this.median_fibercolor = median_fibercolor;
        this.median_psfcolor = median_psfcolor;
        this.q = q;
        this.u = u;
        this.sky = sky;
        this.skySig = skySig;
        this.skyErr = skyErr;
        this.skySlope = skySlope;
        this.lbias = lbias;
        this.rbias = rbias;
        this.psf_nstar = psf_nstar;
        this.psf_ap_correctionerr = psf_ap_correctionerr;
        this.psf_sigma1 = psf_sigma1;
        this.psf_sigma2 = psf_sigma2;
        this.psf_b = psf_b;
        this.psf_p0 = psf_p0;
        this.psf_beta = psf_beta;
        this.psf_sigmap = psf_sigmap;
        this.psf_width = psf_width;
        this.psf_psfcounts = psf_psfcounts;
        this.psf_sigma1_2g = psf_sigma1_2g;
        this.psf_sigma2_2g = psf_sigma2_2g;
        this.psf_b_2g = psf_b_2g;
        this.psfCounts = psfCounts;
        this.psf_nprof = psf_nprof;
        this.psf_mean_nmgy = psf_mean_nmgy;
        this.psf_med_nmgy = psf_med_nmgy;
        this.psf_sig_nmgy = psf_sig_nmgy;
        this.gain = gain;
        this.dark_variance = dark_variance;
        this.score = score;
        this.aterm = aterm;
        this.kterm = kterm;
        this.kdot = kdot;
        this.ref_tai = ref_tai;
        this.tai = tai;
        this.psp_status = psp_status;
        this.photo_status = photo_status;
        this.image_status = image_status;
        this.calib_status = calib_status;
        this.nstars_offset = nstars_offset;
        this.field_offset = field_offset;
        this.nMgyPerCount = nMgyPerCount;
        this.nMgyPerCount_Ivar = nMgyPerCount_Ivar;
        this.ifield = ifield;
        this.mu_start = mu_start;
        this.mu_end = mu_end;
        this.nu_start = nu_start;
        this.nu_end = nu_end;
        this.ifindx = ifindx;
        this.nbalkans = nbalkans;
    }

    public PhotoField(Object[] row) {
        FitsUtil f = new FitsUtil();
        try {
            int n = 0;
            this.fieldID = (String) row[n++];
            this.skyVersion = f.uShort(row[n++]);
            this.run = f.toInt(row[n++]);
            this.rerun = (String) row[n++];
            this.camcol = f.toInt(row[n++]);
            this.field = f.toInt(row[n++]);
            this.nTotal = f.toShort(row[n++]);
            this.nObjects = f.toShort(row[n++]);
            this.nChild = f.toShort(row[n++]);
            this.ngals = f.toShort(row[n++]);
            this.nStars = f.toShort(row[n++]);
            this.nCR = f.shortArray(row[n++]);
            this.nBrightObj = f.shortArray(row[n++]);
            this.nFaintObj = f.shortArray(row[n++]);
            this.quality = f.toInt(row[n++]);
            this.mjd = f.doubleArray(row[n++]);
            this.a = f.floatArray(row[n++]);
            this.b = f.floatArray(row[n++]);
            this.c = f.floatArray(row[n++]);
            this.d = f.floatArray(row[n++]);
            this.e = f.floatArray(row[n++]);
            this.ff = f.floatArray(row[n++]);
            this.dRow0 = f.floatArray(row[n++]);
            this.dRow1 = f.floatArray(row[n++]);
            this.dRow2 = f.floatArray(row[n++]);
            this.dRow3 = f.floatArray(row[n++]);
            this.dCol0 = f.floatArray(row[n++]);
            this.dCol1 = f.floatArray(row[n++]);
            this.dCol2 = f.floatArray(row[n++]);
            this.dCol3 = f.floatArray(row[n++]);
            this.csRow = f.floatArray(row[n++]);
            this.csCol = f.floatArray(row[n++]);
            this.ccRow = f.floatArray(row[n++]);
            this.ccCol = f.floatArray(row[n++]);
            this.riCut = f.floatArray(row[n++]);
            this.airmass = f.floatArray(row[n++]);
            this.muErr = f.floatArray(row[n++]);
            this.nuErr = f.floatArray(row[n++]);
            this.pixScale = f.floatArray(row[n++]);
            this.ra = f.toDouble(row[n++]);
            this.dec = f.toDouble(row[n++]);
            this.CX = f.toDouble(row[n++]);
            this.CY = f.toDouble(row[n++]);
            this.CZ = f.toDouble(row[n++]);
            this.raMin = f.toDouble(row[n++]);
            this.raMax = f.toDouble(row[n++]);
            this.decMin = f.toDouble(row[n++]);
            this.decMax = f.toDouble(row[n++]);
            this.primaryArea = f.toDouble(row[n++]);
            this.rowOffset = f.floatArray(row[n++]);
            this.colOffset = f.floatArray(row[n++]);
            this.saturation_level = f.intArray(row[n++]);
            this.neff_psf = f.floatArray(row[n++]);
            this.sky_psp = f.floatArray(row[n++]);
            this.sky_frames = f.floatArray(row[n++]);
            this.sky_frames_sub = f.floatArray(row[n++]);
            this.sigPix = f.floatArray(row[n++]);
            this.dev_ap_correction = f.floatArray(row[n++]);
            this.dev_ap_correctionerr = f.floatArray(row[n++]);
            this.exp_ap_correction = f.floatArray(row[n++]);
            this.exp_ap_correctionerr = f.floatArray(row[n++]);
            this.dev_model_ap_correction = f.floatArray(row[n++]);
            this.dev_model_ap_correctionerr = f.floatArray(row[n++]);
            this.exp_model_ap_correction = f.floatArray(row[n++]);
            this.exp_model_ap_correctionerr = f.floatArray(row[n++]);
            this.median_fibercolor = f.floatArray(row[n++]);
            this.median_psfcolor = f.floatArray(row[n++]);
            this.q = f.floatArray(row[n++]);
            this.u = f.floatArray(row[n++]);
            this.sky = f.floatArray(row[n++]);
            this.skySig = f.floatArray(row[n++]);
            this.skyErr = f.floatArray(row[n++]);
            this.skySlope = f.floatArray(row[n++]);
            this.lbias = f.floatArray(row[n++]);
            this.rbias = f.floatArray(row[n++]);
            this.psf_nstar = f.intArray(row[n++]);
            this.psf_ap_correctionerr = f.floatArray(row[n++]);
            this.psf_sigma1 = f.floatArray(row[n++]);
            this.psf_sigma2 = f.floatArray(row[n++]);
            this.psf_b = f.floatArray(row[n++]);
            this.psf_p0 = f.floatArray(row[n++]);
            this.psf_beta = f.floatArray(row[n++]);
            this.psf_sigmap = f.floatArray(row[n++]);
            this.psf_width = f.floatArray(row[n++]);
            this.psf_psfcounts = f.floatArray(row[n++]);
            this.psf_sigma1_2g = f.floatArray(row[n++]);
            this.psf_sigma2_2g = f.floatArray(row[n++]);
            this.psf_b_2g = f.floatArray(row[n++]);
            this.psfCounts = f.floatArray(row[n++]);
            this.psf_nprof = f.intArray(row[n++]);
            n++;    //        this.psf_mean_nmgy = ;
            n++;   //        this.psf_med_nmgy = ;
            n++;   //        this.psf_sig_nmgy = ;
            this.gain = f.floatArray(row[n++]);
            this.dark_variance = f.floatArray(row[n++]);
            this.score = f.toFloat(row[n++]);
            this.aterm = f.floatArray(row[n++]);
            this.kterm = f.floatArray(row[n++]);
            this.kdot = f.floatArray(row[n++]);
            this.ref_tai = f.doubleArray(row[n++]);
            this.tai = f.doubleArray(row[n++]);
            this.psp_status = f.toInt(row[n++]);
            this.photo_status = f.toInt(row[n++]);
            this.image_status = f.intArray(row[n++]);
            this.calib_status = f.intArray(row[n++]);
            this.nstars_offset = f.intArray(row[n++]);
            this.field_offset = f.floatArray(row[n++]);
            this.nMgyPerCount = f.floatArray(row[n++]);
            this.nMgyPerCount_Ivar = f.floatArray(row[n++]);
            this.ifield = f.toInt(row[n++]);
            this.mu_start = f.toDouble(row[n++]);
            this.mu_end = f.toDouble(row[n++]);
            this.nu_start = f.toDouble(row[n++]);
            this.nu_end = f.toDouble(row[n++]);
            this.ifindx = f.toInt(row[n++]);
            this.nbalkans = f.toInt(row[n++]);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    public String getFieldID() {
        return fieldID;
    }

    public void setFieldID(String fieldID) {
        this.fieldID = fieldID;
    }

    public short getSkyVersion() {
        return skyVersion;
    }

    public void setSkyVersion(short skyVersion) {
        this.skyVersion = skyVersion;
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

    public int getField() {
        return field;
    }

    public void setField(int field) {
        this.field = field;
    }

    public short getnTotal() {
        return nTotal;
    }

    public void setnTotal(short nTotal) {
        this.nTotal = nTotal;
    }

    public short getnObjects() {
        return nObjects;
    }

    public void setnObjects(short nObjects) {
        this.nObjects = nObjects;
    }

    public short getnChild() {
        return nChild;
    }

    public void setnChild(short nChild) {
        this.nChild = nChild;
    }

    public short getNgals() {
        return ngals;
    }

    public void setNgals(short ngals) {
        this.ngals = ngals;
    }

    public short getnStars() {
        return nStars;
    }

    public void setnStars(short nStars) {
        this.nStars = nStars;
    }

    public short[] getnCR() {
        return nCR;
    }

    public void setnCR(short[] nCR) {
        this.nCR = nCR;
    }

    public short[] getnBrightObj() {
        return nBrightObj;
    }

    public void setnBrightObj(short[] nBrightObj) {
        this.nBrightObj = nBrightObj;
    }

    public short[] getnFaintObj() {
        return nFaintObj;
    }

    public void setnFaintObj(short[] nFaintObj) {
        this.nFaintObj = nFaintObj;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public double[] getMjd() {
        return mjd;
    }

    public void setMjd(double[] mjd) {
        this.mjd = mjd;
    }

    public float[] getA() {
        return a;
    }

    public void setA(float[] a) {
        this.a = a;
    }

    public float[] getB() {
        return b;
    }

    public void setB(float[] b) {
        this.b = b;
    }

    public float[] getC() {
        return c;
    }

    public void setC(float[] c) {
        this.c = c;
    }

    public float[] getD() {
        return d;
    }

    public void setD(float[] d) {
        this.d = d;
    }

    public float[] getE() {
        return e;
    }

    public void setE(float[] e) {
        this.e = e;
    }

    public float[] getFf() {
        return ff;
    }

    public void setFf(float[] ff) {
        this.ff = ff;
    }

    public float[] getdRow0() {
        return dRow0;
    }

    public void setdRow0(float[] dRow0) {
        this.dRow0 = dRow0;
    }

    public float[] getdRow1() {
        return dRow1;
    }

    public void setdRow1(float[] dRow1) {
        this.dRow1 = dRow1;
    }

    public float[] getdRow2() {
        return dRow2;
    }

    public void setdRow2(float[] dRow2) {
        this.dRow2 = dRow2;
    }

    public float[] getdRow3() {
        return dRow3;
    }

    public void setdRow3(float[] dRow3) {
        this.dRow3 = dRow3;
    }

    public float[] getdCol0() {
        return dCol0;
    }

    public void setdCol0(float[] dCol0) {
        this.dCol0 = dCol0;
    }

    public float[] getdCol1() {
        return dCol1;
    }

    public void setdCol1(float[] dCol1) {
        this.dCol1 = dCol1;
    }

    public float[] getdCol2() {
        return dCol2;
    }

    public void setdCol2(float[] dCol2) {
        this.dCol2 = dCol2;
    }

    public float[] getdCol3() {
        return dCol3;
    }

    public void setdCol3(float[] dCol3) {
        this.dCol3 = dCol3;
    }

    public float[] getCsRow() {
        return csRow;
    }

    public void setCsRow(float[] csRow) {
        this.csRow = csRow;
    }

    public float[] getCsCol() {
        return csCol;
    }

    public void setCsCol(float[] csCol) {
        this.csCol = csCol;
    }

    public float[] getCcRow() {
        return ccRow;
    }

    public void setCcRow(float[] ccRow) {
        this.ccRow = ccRow;
    }

    public float[] getCcCol() {
        return ccCol;
    }

    public void setCcCol(float[] ccCol) {
        this.ccCol = ccCol;
    }

    public float[] getRiCut() {
        return riCut;
    }

    public void setRiCut(float[] riCut) {
        this.riCut = riCut;
    }

    public float[] getAirmass() {
        return airmass;
    }

    public void setAirmass(float[] airmass) {
        this.airmass = airmass;
    }

    public float[] getMuErr() {
        return muErr;
    }

    public void setMuErr(float[] muErr) {
        this.muErr = muErr;
    }

    public float[] getNuErr() {
        return nuErr;
    }

    public void setNuErr(float[] nuErr) {
        this.nuErr = nuErr;
    }

    public float[] getPixScale() {
        return pixScale;
    }

    public void setPixScale(float[] pixScale) {
        this.pixScale = pixScale;
    }

    public double getRa() {
        return ra;
    }

    public void setRa(double ra) {
        this.ra = ra;
    }

    public double getDec() {
        return dec;
    }

    public void setDec(double dec) {
        this.dec = dec;
    }

    public double getCX() {
        return CX;
    }

    public void setCX(double CX) {
        this.CX = CX;
    }

    public double getCY() {
        return CY;
    }

    public void setCY(double CY) {
        this.CY = CY;
    }

    public double getCZ() {
        return CZ;
    }

    public void setCZ(double CZ) {
        this.CZ = CZ;
    }

    public double getRaMin() {
        return raMin;
    }

    public void setRaMin(double raMin) {
        this.raMin = raMin;
    }

    public double getRaMax() {
        return raMax;
    }

    public void setRaMax(double raMax) {
        this.raMax = raMax;
    }

    public double getDecMin() {
        return decMin;
    }

    public void setDecMin(double decMin) {
        this.decMin = decMin;
    }

    public double getDecMax() {
        return decMax;
    }

    public void setDecMax(double decMax) {
        this.decMax = decMax;
    }

    public double getPrimaryArea() {
        return primaryArea;
    }

    public void setPrimaryArea(double primaryArea) {
        this.primaryArea = primaryArea;
    }

    public float[] getRowOffset() {
        return rowOffset;
    }

    public void setRowOffset(float[] rowOffset) {
        this.rowOffset = rowOffset;
    }

    public float[] getColOffset() {
        return colOffset;
    }

    public void setColOffset(float[] colOffset) {
        this.colOffset = colOffset;
    }

    public int[] getSaturation_level() {
        return saturation_level;
    }

    public void setSaturation_level(int[] saturation_level) {
        this.saturation_level = saturation_level;
    }

    public float[] getNeff_psf() {
        return neff_psf;
    }

    public void setNeff_psf(float[] neff_psf) {
        this.neff_psf = neff_psf;
    }

    public float[] getSky_psp() {
        return sky_psp;
    }

    public void setSky_psp(float[] sky_psp) {
        this.sky_psp = sky_psp;
    }

    public float[] getSky_frames() {
        return sky_frames;
    }

    public void setSky_frames(float[] sky_frames) {
        this.sky_frames = sky_frames;
    }

    public float[] getSky_frames_sub() {
        return sky_frames_sub;
    }

    public void setSky_frames_sub(float[] sky_frames_sub) {
        this.sky_frames_sub = sky_frames_sub;
    }

    public float[] getSigPix() {
        return sigPix;
    }

    public void setSigPix(float[] sigPix) {
        this.sigPix = sigPix;
    }

    public float[] getDev_ap_correction() {
        return dev_ap_correction;
    }

    public void setDev_ap_correction(float[] dev_ap_correction) {
        this.dev_ap_correction = dev_ap_correction;
    }

    public float[] getDev_ap_correctionerr() {
        return dev_ap_correctionerr;
    }

    public void setDev_ap_correctionerr(float[] dev_ap_correctionerr) {
        this.dev_ap_correctionerr = dev_ap_correctionerr;
    }

    public float[] getExp_ap_correction() {
        return exp_ap_correction;
    }

    public void setExp_ap_correction(float[] exp_ap_correction) {
        this.exp_ap_correction = exp_ap_correction;
    }

    public float[] getExp_ap_correctionerr() {
        return exp_ap_correctionerr;
    }

    public void setExp_ap_correctionerr(float[] exp_ap_correctionerr) {
        this.exp_ap_correctionerr = exp_ap_correctionerr;
    }

    public float[] getDev_model_ap_correction() {
        return dev_model_ap_correction;
    }

    public void setDev_model_ap_correction(float[] dev_model_ap_correction) {
        this.dev_model_ap_correction = dev_model_ap_correction;
    }

    public float[] getDev_model_ap_correctionerr() {
        return dev_model_ap_correctionerr;
    }

    public void setDev_model_ap_correctionerr(float[] dev_model_ap_correctionerr) {
        this.dev_model_ap_correctionerr = dev_model_ap_correctionerr;
    }

    public float[] getExp_model_ap_correction() {
        return exp_model_ap_correction;
    }

    public void setExp_model_ap_correction(float[] exp_model_ap_correction) {
        this.exp_model_ap_correction = exp_model_ap_correction;
    }

    public float[] getExp_model_ap_correctionerr() {
        return exp_model_ap_correctionerr;
    }

    public void setExp_model_ap_correctionerr(float[] exp_model_ap_correctionerr) {
        this.exp_model_ap_correctionerr = exp_model_ap_correctionerr;
    }

    public float[] getMedian_fibercolor() {
        return median_fibercolor;
    }

    public void setMedian_fibercolor(float[] median_fibercolor) {
        this.median_fibercolor = median_fibercolor;
    }

    public float[] getMedian_psfcolor() {
        return median_psfcolor;
    }

    public void setMedian_psfcolor(float[] median_psfcolor) {
        this.median_psfcolor = median_psfcolor;
    }

    public float[] getQ() {
        return q;
    }

    public void setQ(float[] q) {
        this.q = q;
    }

    public float[] getU() {
        return u;
    }

    public void setU(float[] u) {
        this.u = u;
    }

    public float[] getSky() {
        return sky;
    }

    public void setSky(float[] sky) {
        this.sky = sky;
    }

    public float[] getSkySig() {
        return skySig;
    }

    public void setSkySig(float[] skySig) {
        this.skySig = skySig;
    }

    public float[] getSkyErr() {
        return skyErr;
    }

    public void setSkyErr(float[] skyErr) {
        this.skyErr = skyErr;
    }

    public float[] getSkySlope() {
        return skySlope;
    }

    public void setSkySlope(float[] skySlope) {
        this.skySlope = skySlope;
    }

    public float[] getLbias() {
        return lbias;
    }

    public void setLbias(float[] lbias) {
        this.lbias = lbias;
    }

    public float[] getRbias() {
        return rbias;
    }

    public void setRbias(float[] rbias) {
        this.rbias = rbias;
    }

    public int[] getPsf_nstar() {
        return psf_nstar;
    }

    public void setPsf_nstar(int[] psf_nstar) {
        this.psf_nstar = psf_nstar;
    }

    public float[] getPsf_ap_correctionerr() {
        return psf_ap_correctionerr;
    }

    public void setPsf_ap_correctionerr(float[] psf_ap_correctionerr) {
        this.psf_ap_correctionerr = psf_ap_correctionerr;
    }

    public float[] getPsf_sigma1() {
        return psf_sigma1;
    }

    public void setPsf_sigma1(float[] psf_sigma1) {
        this.psf_sigma1 = psf_sigma1;
    }

    public float[] getPsf_sigma2() {
        return psf_sigma2;
    }

    public void setPsf_sigma2(float[] psf_sigma2) {
        this.psf_sigma2 = psf_sigma2;
    }

    public float[] getPsf_b() {
        return psf_b;
    }

    public void setPsf_b(float[] psf_b) {
        this.psf_b = psf_b;
    }

    public float[] getPsf_p0() {
        return psf_p0;
    }

    public void setPsf_p0(float[] psf_p0) {
        this.psf_p0 = psf_p0;
    }

    public float[] getPsf_beta() {
        return psf_beta;
    }

    public void setPsf_beta(float[] psf_beta) {
        this.psf_beta = psf_beta;
    }

    public float[] getPsf_sigmap() {
        return psf_sigmap;
    }

    public void setPsf_sigmap(float[] psf_sigmap) {
        this.psf_sigmap = psf_sigmap;
    }

    public float[] getPsf_width() {
        return psf_width;
    }

    public void setPsf_width(float[] psf_width) {
        this.psf_width = psf_width;
    }

    public float[] getPsf_psfcounts() {
        return psf_psfcounts;
    }

    public void setPsf_psfcounts(float[] psf_psfcounts) {
        this.psf_psfcounts = psf_psfcounts;
    }

    public float[] getPsf_sigma1_2g() {
        return psf_sigma1_2g;
    }

    public void setPsf_sigma1_2g(float[] psf_sigma1_2g) {
        this.psf_sigma1_2g = psf_sigma1_2g;
    }

    public float[] getPsf_sigma2_2g() {
        return psf_sigma2_2g;
    }

    public void setPsf_sigma2_2g(float[] psf_sigma2_2g) {
        this.psf_sigma2_2g = psf_sigma2_2g;
    }

    public float[] getPsf_b_2g() {
        return psf_b_2g;
    }

    public void setPsf_b_2g(float[] psf_b_2g) {
        this.psf_b_2g = psf_b_2g;
    }

    public float[] getPsfCounts() {
        return psfCounts;
    }

    public void setPsfCounts(float[] psfCounts) {
        this.psfCounts = psfCounts;
    }

    public int[] getPsf_nprof() {
        return psf_nprof;
    }

    public void setPsf_nprof(int[] psf_nprof) {
        this.psf_nprof = psf_nprof;
    }

    public float[][] getPsf_mean_nmgy() {
        return psf_mean_nmgy;
    }

    public void setPsf_mean_nmgy(float[][] psf_mean_nmgy) {
        this.psf_mean_nmgy = psf_mean_nmgy;
    }

    public float[][] getPsf_med_nmgy() {
        return psf_med_nmgy;
    }

    public void setPsf_med_nmgy(float[][] psf_med_nmgy) {
        this.psf_med_nmgy = psf_med_nmgy;
    }

    public float[][] getPsf_sig_nmgy() {
        return psf_sig_nmgy;
    }

    public void setPsf_sig_nmgy(float[][] psf_sig_nmgy) {
        this.psf_sig_nmgy = psf_sig_nmgy;
    }

    public float[] getGain() {
        return gain;
    }

    public void setGain(float[] gain) {
        this.gain = gain;
    }

    public float[] getDark_variance() {
        return dark_variance;
    }

    public void setDark_variance(float[] dark_variance) {
        this.dark_variance = dark_variance;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public float[] getAterm() {
        return aterm;
    }

    public void setAterm(float[] aterm) {
        this.aterm = aterm;
    }

    public float[] getKterm() {
        return kterm;
    }

    public void setKterm(float[] kterm) {
        this.kterm = kterm;
    }

    public float[] getKdot() {
        return kdot;
    }

    public void setKdot(float[] kdot) {
        this.kdot = kdot;
    }

    public double[] getRef_tai() {
        return ref_tai;
    }

    public void setRef_tai(double[] ref_tai) {
        this.ref_tai = ref_tai;
    }

    public double[] getTai() {
        return tai;
    }

    public void setTai(double[] tai) {
        this.tai = tai;
    }

    public int getPsp_status() {
        return psp_status;
    }

    public void setPsp_status(int psp_status) {
        this.psp_status = psp_status;
    }

    public int getPhoto_status() {
        return photo_status;
    }

    public void setPhoto_status(int photo_status) {
        this.photo_status = photo_status;
    }

    public int[] getImage_status() {
        return image_status;
    }

    public void setImage_status(int[] image_status) {
        this.image_status = image_status;
    }

    public int[] getCalib_status() {
        return calib_status;
    }

    public void setCalib_status(int[] calib_status) {
        this.calib_status = calib_status;
    }

    public int[] getNstars_offset() {
        return nstars_offset;
    }

    public void setNstars_offset(int[] nstars_offset) {
        this.nstars_offset = nstars_offset;
    }

    public float[] getField_offset() {
        return field_offset;
    }

    public void setField_offset(float[] field_offset) {
        this.field_offset = field_offset;
    }

    public float[] getnMgyPerCount() {
        return nMgyPerCount;
    }

    public void setnMgyPerCount(float[] nMgyPerCount) {
        this.nMgyPerCount = nMgyPerCount;
    }

    public float[] getnMgyPerCount_Ivar() {
        return nMgyPerCount_Ivar;
    }

    public void setnMgyPerCount_Ivar(float[] nMgyPerCount_Ivar) {
        this.nMgyPerCount_Ivar = nMgyPerCount_Ivar;
    }

    public int getIfield() {
        return ifield;
    }

    public void setIfield(int ifield) {
        this.ifield = ifield;
    }

    public double getMu_start() {
        return mu_start;
    }

    public void setMu_start(double mu_start) {
        this.mu_start = mu_start;
    }

    public double getMu_end() {
        return mu_end;
    }

    public void setMu_end(double mu_end) {
        this.mu_end = mu_end;
    }

    public double getNu_start() {
        return nu_start;
    }

    public void setNu_start(double nu_start) {
        this.nu_start = nu_start;
    }

    public double getNu_end() {
        return nu_end;
    }

    public void setNu_end(double nu_end) {
        this.nu_end = nu_end;
    }

    public int getIfindx() {
        return ifindx;
    }

    public void setIfindx(int ifindx) {
        this.ifindx = ifindx;
    }

    public int getNbalkans() {
        return nbalkans;
    }

    public void setNbalkans(int nbalkans) {
        this.nbalkans = nbalkans;
    }
}
