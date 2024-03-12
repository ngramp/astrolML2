package photoobj;

import util.FitsUtil;

/**
 * Created by Graham Perry on 13/07/16.
 *
 * @author Graham Perry
 *         https://data.sdss.org/datamodel/files/BOSS_PHOTOOBJ/photoRunAll.html
 */
public class PhotoRun {
    private short skyversion;//(int8): 0 = OPDB target, 1 = OPDB best XXX
    private int run;//(int16): Run number
    private String rerun;//(int16): Rerun number !actually a string
    private int mjd;//(int32): MJD of observation ;//(days)
    private String datestring;//(string): Human-readable date of observation
    private int stripe;//(int32): Stripe number
    private String strip;//(string): Strip ;//(N or S)
    private double xBore;//(float64): boresight offset perpendicular to great circle ;//(deg)
    private int field_Ref;//(int32): Starting field number of full run ;//(what MU_REF, MJD_REF refer to)
    private int lastField;//(int32): last field of full run
    private String flavor;//(string): type of drift scan ;//(from apacheraw, bias, calibration, engineering, ignore, and science)
    private int xBin;//(int32): binning amount perpendicular to scan direction ;//(pix)
    private int yBin;//(int32): binning amount in scan direction ;//(pix)
    private int nRow;//(int32): number of rows in output idR ;//(pix)
    private double mjd_Ref;//(float64): MJD at row 0 of reference frame ;//(days)
    private double mu_Ref;//(float64): mu value at row 0 of reference field ;//(deg)
    private int lineStart;//(int32): linestart rate betweeen each ;//(binned) row ;//(microsec)
    private double tracking;//(float64): tracking rate ;//(arcsec)
    private double node;//(float64): node of great circle, that is, its RA on the J2000 equator
    private double incl;//(float64): inclination of great circle relative to J2000 equator
    private String comments;//(string): comments on the run
    private float qterm;//(float32): quadratic term in coarse astrometric solution ;//(arcsec)
    private float maxMuResid;//(float32): maximum residual from great circle in scan direction ;//(arcsec)
    private float maxNuResid;//(float32): maximum residual from great circle perpendicular to scan direction ;//(arcsec)
    private int startField;//(int32): starting field for reduced data
    private int endField;//(int32): ending field for reduced data
    private String photo_version;//(string): photo version used
    private String dervish_version;//(string): dervish version used
    private String astrom_version;//(string): astrom version used
    private String sas_version;//(string): version of SAS used to produce CSV for this run

    public PhotoRun(short skyversion, int run, String rerun, int mjd, String datestring, int stripe, String strip, double xBore, int field_Ref, int lastField, String flavor, int xBin, int yBin, int nRow, double mjd_Ref, double mu_Ref, int lineStart, double tracking, double node, double incl, String comments, float qterm, float maxMuResid, float maxNuResid, int startField, int endField, String photo_version, String dervish_version, String astrom_version, String sas_version) {
        this.skyversion = skyversion;
        this.run = run;
        this.rerun = rerun;
        this.mjd = mjd;
        this.datestring = datestring;
        this.stripe = stripe;
        this.strip = strip;
        this.xBore = xBore;
        this.field_Ref = field_Ref;
        this.lastField = lastField;
        this.flavor = flavor;
        this.xBin = xBin;
        this.yBin = yBin;
        this.nRow = nRow;
        this.mjd_Ref = mjd_Ref;
        this.mu_Ref = mu_Ref;
        this.lineStart = lineStart;
        this.tracking = tracking;
        this.node = node;
        this.incl = incl;
        this.comments = comments;
        this.qterm = qterm;
        this.maxMuResid = maxMuResid;
        this.maxNuResid = maxNuResid;
        this.startField = startField;
        this.endField = endField;
        this.photo_version = photo_version;
        this.dervish_version = dervish_version;
        this.astrom_version = astrom_version;
        this.sas_version = sas_version;
    }

    public PhotoRun(Object[] row) {
        FitsUtil f = new FitsUtil();
        try {
            int n = 0;
            this.skyversion = f.uShort(row[n++]);
            this.run = f.toInt(row[n++]);
            this.rerun = (String) row[n++];
            this.mjd = f.toInt(row[n++]);
            this.datestring = (String) row[n++];
            this.stripe = f.toInt(row[n++]);
            this.strip = (String) row[n++];
            this.xBore = f.toDouble(row[n++]);
            this.field_Ref = f.toInt(row[n++]);
            this.lastField = f.toInt(row[n++]);
            this.flavor = (String) row[n++];
            this.xBin = f.toInt(row[n++]);
            this.yBin = f.toInt(row[n++]);
            this.nRow = f.toInt(row[n++]);
            this.mjd_Ref = f.toDouble(row[n++]);
            this.mu_Ref = f.toDouble(row[n++]);
            this.lineStart = f.toInt(row[n++]);
            this.tracking = f.toDouble(row[n++]);
            this.node = f.toDouble(row[n++]);
            this.incl = f.toDouble(row[n++]);
            this.comments = (String) row[n++];
            this.qterm = f.toFloat(row[n++]);
            this.maxMuResid = f.toFloat(row[n++]);
            this.maxNuResid = f.toFloat(row[n++]);
            this.startField = f.toInt(row[n++]);
            this.endField = f.toInt(row[n++]);
            this.photo_version = (String) row[n++];
            this.dervish_version = (String) row[n++];
            this.astrom_version = (String) row[n++];
            this.sas_version = (String) row[n++];
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public short getSkyversion() {
        return skyversion;
    }

    public void setSkyversion(short skyversion) {
        this.skyversion = skyversion;
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

    public int getMjd() {
        return mjd;
    }

    public void setMjd(int mjd) {
        this.mjd = mjd;
    }

    public String getDatestring() {
        return datestring;
    }

    public void setDatestring(String datestring) {
        this.datestring = datestring;
    }

    public int getStripe() {
        return stripe;
    }

    public void setStripe(int stripe) {
        this.stripe = stripe;
    }

    public String getStrip() {
        return strip;
    }

    public void setStrip(String strip) {
        this.strip = strip;
    }

    public double getxBore() {
        return xBore;
    }

    public void setxBore(double xBore) {
        this.xBore = xBore;
    }

    public int getField_Ref() {
        return field_Ref;
    }

    public void setField_Ref(int field_Ref) {
        this.field_Ref = field_Ref;
    }

    public int getLastField() {
        return lastField;
    }

    public void setLastField(int lastField) {
        this.lastField = lastField;
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public int getxBin() {
        return xBin;
    }

    public void setxBin(int xBin) {
        this.xBin = xBin;
    }

    public int getyBin() {
        return yBin;
    }

    public void setyBin(int yBin) {
        this.yBin = yBin;
    }

    public int getnRow() {
        return nRow;
    }

    public void setnRow(int nRow) {
        this.nRow = nRow;
    }

    public double getMjd_Ref() {
        return mjd_Ref;
    }

    public void setMjd_Ref(double mjd_Ref) {
        this.mjd_Ref = mjd_Ref;
    }

    public double getMu_Ref() {
        return mu_Ref;
    }

    public void setMu_Ref(double mu_Ref) {
        this.mu_Ref = mu_Ref;
    }

    public int getLineStart() {
        return lineStart;
    }

    public void setLineStart(int lineStart) {
        this.lineStart = lineStart;
    }

    public double getTracking() {
        return tracking;
    }

    public void setTracking(double tracking) {
        this.tracking = tracking;
    }

    public double getNode() {
        return node;
    }

    public void setNode(double node) {
        this.node = node;
    }

    public double getIncl() {
        return incl;
    }

    public void setIncl(double incl) {
        this.incl = incl;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public float getQterm() {
        return qterm;
    }

    public void setQterm(float qterm) {
        this.qterm = qterm;
    }

    public float getMaxMuResid() {
        return maxMuResid;
    }

    public void setMaxMuResid(float maxMuResid) {
        this.maxMuResid = maxMuResid;
    }

    public float getMaxNuResid() {
        return maxNuResid;
    }

    public void setMaxNuResid(float maxNuResid) {
        this.maxNuResid = maxNuResid;
    }

    public int getStartField() {
        return startField;
    }

    public void setStartField(int startField) {
        this.startField = startField;
    }

    public int getEndField() {
        return endField;
    }

    public void setEndField(int endField) {
        this.endField = endField;
    }

    public String getPhoto_version() {
        return photo_version;
    }

    public void setPhoto_version(String photo_version) {
        this.photo_version = photo_version;
    }

    public String getDervish_version() {
        return dervish_version;
    }

    public void setDervish_version(String dervish_version) {
        this.dervish_version = dervish_version;
    }

    public String getAstrom_version() {
        return astrom_version;
    }

    public void setAstrom_version(String astrom_version) {
        this.astrom_version = astrom_version;
    }

    public String getSas_version() {
        return sas_version;
    }

    public void setSas_version(String sas_version) {
        this.sas_version = sas_version;
    }
}
