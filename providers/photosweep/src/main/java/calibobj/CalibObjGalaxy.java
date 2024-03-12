package calibobj;
// Generated 17-Jun-2016 10:09:16 by Hibernate Tools 4.3.1.Final


import model.CosObject;
import util.FitsUtil;

public class CalibObjGalaxy extends CalibObj implements CosObject {

    private float[] petroth50;
    private float[] petroth90;
    private float[] MRrCc;
    private float[] MRrCcPsf;
    private float[] thetaDev;
    private float[] thetaExp;
    private float[] fracdev;
    private float[] starLnl;
    private float[] expLnl;
    private float[] devLnl;
    private float[] psfflux;
    private float[] psffluxIvar;
    private float[] fiberflux;
    private float[] fiberfluxIvar;
    private float[] fiber2flux;
    private float[] fiber2fluxIvar;
    private float[] modelflux;
    private float[] modelfluxIvar;
    private float[] petroflux;
    private float[] petrofluxIvar;
    private float[] devflux;
    private float[] devfluxIvar;
    private float[] expflux;
    private float[] expfluxIvar;
    private float[] aperflux;
    private float[] cmodelfluxClean;
    private float[] cmodelfluxCleanIvar;
    private float[] cmodelfluxCleanVar;
    private float[] cmodelfluxCleanChi2;
    private short[] cmodelCleanNuse;
    private int[] cmodelCleanMjdMaxdiff;
    private float[] cmodelCleanMjdVar;
    private float[] modelfluxClean;
    private float[] modelfluxCleanIvar;
    private float[] modelfluxCleanVar;
    private float[] modelfluxCleanChi2;
    private short[] modelCleanNuse;
    private int[] modelCleanMjdMaxdiff;
    private float[] modelCleanMjdVar;
    private float[] psffluxClean;
    private float[] psffluxCleanIvar;
    private float[] psffluxCleanVar;
    private float[] psffluxCleanChi2;
    private short[] psfCleanNuse;
    private int[] psfCleanMjdMaxdiff;
    private float[] psfCleanMjdVar;

    public CalibObjGalaxy() {
        super();
    }


    public CalibObjGalaxy(short run, String rerun, short camcol, short field, short id, int objcType, int objcFlags, int objcFlags2, float[] rowc,
                          float[] colc, float[] petroth50, float[] petroth90, float[] MRrCc, float[] MRrCcPsf, float[] thetaDev,
                          float[] thetaExp, float[] fracdev, int[] flags, int[] flags2, float[] starLnl, float[] expLnl,
                          float[] devLnl, int[] pspStatus, double ra, double dec, float[] psfFwhm, float[] extinction,
                          float[] psfflux, float[] psffluxIvar, float[] fiberflux, float[] fiberfluxIvar, float[] fiber2flux,
                          float[] fiber2fluxIvar, float[] modelflux, float[] modelfluxIvar, float[] petroflux, float[] petrofluxIvar,
                          float[] devflux, float[] devfluxIvar, float[] expflux, float[] expfluxIvar, float[] aperflux,
                          int[] calibStatus, float[] nmgypercount, int resolveStatus, int thingId, int ifield,
                          int balkanId, int ndetect, float[] cmodelfluxClean, float[] cmodelfluxCleanIvar,
                          float[] cmodelfluxCleanVar, float[] cmodelfluxCleanChi2, short[] cmodelCleanNuse,
                          int[] cmodelCleanMjdMaxdiff, float[] cmodelCleanMjdVar, float[] modelfluxClean,
                          float[] modelfluxCleanIvar, float[] modelfluxCleanVar, float[] modelfluxCleanChi2, short[] modelCleanNuse,
                          int[] modelCleanMjdMaxdiff, float[] modelCleanMjdVar, float[] psffluxClean, float[] psffluxCleanIvar,
                          float[] psffluxCleanVar, float[] psffluxCleanChi2, short[] psfCleanNuse, int[] psfCleanMjdMaxdiff,
                          float[] psfCleanMjdVar) {

        super();
        this.run = run;     // RUN $1 Short I
        this.rerun = rerun;   // RERUN $2 String 3A
        this.camcol = camcol;    // CAMCOL $3 Short B true
        this.field = field;    // FIELD $4 Short I
        this.id = id;    // ID $5 Short I row index
        this.objcType = objcType;
        this.objcFlags = objcFlags;
        this.objcFlags2 = objcFlags2;
        this.rowc = rowc;
        this.colc = colc;
        this.petroth50 = petroth50;
        this.petroth90 = petroth90;
        this.MRrCc = MRrCc;
        this.MRrCcPsf = MRrCcPsf;
        this.thetaDev = thetaDev;
        this.thetaExp = thetaExp;
        this.fracdev = fracdev;
        this.flags1 = flags;
        this.flags2 = flags2;
        this.starLnl = starLnl;
        this.expLnl = expLnl;
        this.devLnl = devLnl;
        this.pspStatus = pspStatus;
        this.ra = ra;
        this.dec = dec;
        this.psfFwhm = psfFwhm;
        this.extinction = extinction;
        this.psfflux = psfflux;
        this.psffluxIvar = psffluxIvar;
        this.fiberflux = fiberflux;
        this.fiberfluxIvar = fiberfluxIvar;
        this.fiber2flux = fiber2flux;
        this.fiber2fluxIvar = fiber2fluxIvar;
        this.modelflux = modelflux;
        this.modelfluxIvar = modelfluxIvar;
        this.petroflux = petroflux;
        this.petrofluxIvar = petrofluxIvar;
        this.devflux = devflux;
        this.devfluxIvar = devfluxIvar;
        this.expflux = expflux;
        this.expfluxIvar = expfluxIvar;
        this.aperflux = aperflux;
        this.calibStatus = calibStatus;
        this.nmgypercount = nmgypercount;
        this.resolveStatus = resolveStatus;
        this.thingId = thingId;
        this.ifield = ifield;
        this.balkanId = balkanId;
        this.ndetect = ndetect;
        this.cmodelfluxClean = cmodelfluxClean;
        this.cmodelfluxCleanIvar = cmodelfluxCleanIvar;
        this.cmodelfluxCleanVar = cmodelfluxCleanVar;
        this.cmodelfluxCleanChi2 = cmodelfluxCleanChi2;
        this.cmodelCleanNuse = cmodelCleanNuse;
        this.cmodelCleanMjdMaxdiff = cmodelCleanMjdMaxdiff;
        this.cmodelCleanMjdVar = cmodelCleanMjdVar;
        this.modelfluxClean = modelfluxClean;
        this.modelfluxCleanIvar = modelfluxCleanIvar;
        this.modelfluxCleanVar = modelfluxCleanVar;
        this.modelfluxCleanChi2 = modelfluxCleanChi2;
        this.modelCleanNuse = modelCleanNuse;
        this.modelCleanMjdMaxdiff = modelCleanMjdMaxdiff;
        this.modelCleanMjdVar = modelCleanMjdVar;
        this.psffluxClean = psffluxClean;
        this.psffluxCleanIvar = psffluxCleanIvar;
        this.psffluxCleanVar = psffluxCleanVar;
        this.psffluxCleanChi2 = psffluxCleanChi2;
        this.psfCleanNuse = psfCleanNuse;
        this.psfCleanMjdMaxdiff = psfCleanMjdMaxdiff;
        this.psfCleanMjdVar = psfCleanMjdVar;
    }

    public CalibObjGalaxy(Object[] row) {
        super();
        setRows(row);

    }

    public float[] getPetroth50() {
        return this.petroth50;
    }

    public void setPetroth50(float[] petroth50) {
        this.petroth50 = petroth50;
    }

    public float[] getPetroth90() {
        return this.petroth90;
    }

    public void setPetroth90(float[] petroth90) {
        this.petroth90 = petroth90;
    }

    public float[] getMRrCc() {
        return this.MRrCc;
    }

    public void setMRrCc(float[] MRrCc) {
        this.MRrCc = MRrCc;
    }

    public float[] getMRrCcPsf() {
        return this.MRrCcPsf;
    }

    public void setMRrCcPsf(float[] MRrCcPsf) {
        this.MRrCcPsf = MRrCcPsf;
    }

    public float[] getThetaDev() {
        return this.thetaDev;
    }

    public void setThetaDev(float[] thetaDev) {
        this.thetaDev = thetaDev;
    }

    public float[] getThetaExp() {
        return this.thetaExp;
    }


    public void setThetaExp(float[] thetaExp) {
        this.thetaExp = thetaExp;
    }

    public float[] getFracdev() {
        return this.fracdev;
    }

    public void setFracdev(float[] fracdev) {
        this.fracdev = fracdev;
    }


    public float[] getStarLnl() {
        return this.starLnl;
    }

    public void setStarLnl(float[] starLnl) {
        this.starLnl = starLnl;
    }

    public float[] getExpLnl() {
        return this.expLnl;
    }

    public void setExpLnl(float[] expLnl) {
        this.expLnl = expLnl;
    }

    public float[] getDevLnl() {
        return this.devLnl;
    }

    public void setDevLnl(float[] devLnl) {
        this.devLnl = devLnl;
    }


    public float[] getPsfflux() {
        return this.psfflux;
    }

    public void setPsfflux(float[] psfflux) {
        this.psfflux = psfflux;
    }

    public float[] getPsffluxIvar() {
        return this.psffluxIvar;
    }

    public void setPsffluxIvar(float[] psffluxIvar) {
        this.psffluxIvar = psffluxIvar;
    }

    public float[] getFiberflux() {
        return this.fiberflux;
    }

    public void setFiberflux(float[] fiberflux) {
        this.fiberflux = fiberflux;
    }

    public float[] getFiberfluxIvar() {
        return this.fiberfluxIvar;
    }

    public void setFiberfluxIvar(float[] fiberfluxIvar) {
        this.fiberfluxIvar = fiberfluxIvar;
    }

    public float[] getFiber2flux() {
        return this.fiber2flux;
    }

    public void setFiber2flux(float[] fiber2flux) {
        this.fiber2flux = fiber2flux;
    }

    public float[] getFiber2fluxIvar() {
        return this.fiber2fluxIvar;
    }

    public void setFiber2fluxIvar(float[] fiber2fluxIvar) {
        this.fiber2fluxIvar = fiber2fluxIvar;
    }

    public float[] getModelflux() {
        return this.modelflux;
    }

    public void setModelflux(float[] modelflux) {
        this.modelflux = modelflux;
    }

    public float[] getModelfluxIvar() {
        return this.modelfluxIvar;
    }

    public void setModelfluxIvar(float[] modelfluxIvar) {
        this.modelfluxIvar = modelfluxIvar;
    }

    public float[] getPetroflux() {
        return this.petroflux;
    }

    public void setPetroflux(float[] petroflux) {
        this.petroflux = petroflux;
    }

    public float[] getPetrofluxIvar() {
        return this.petrofluxIvar;
    }

    public void setPetrofluxIvar(float[] petrofluxIvar) {
        this.petrofluxIvar = petrofluxIvar;
    }

    public float[] getDevflux() {
        return this.devflux;
    }

    public void setDevflux(float[] devflux) {
        this.devflux = devflux;
    }

    public float[] getDevfluxIvar() {
        return this.devfluxIvar;
    }

    public void setDevfluxIvar(float[] devfluxIvar) {
        this.devfluxIvar = devfluxIvar;
    }

    public float[] getExpflux() {
        return this.expflux;
    }

    public void setExpflux(float[] expflux) {
        this.expflux = expflux;
    }

    public float[] getExpfluxIvar() {
        return this.expfluxIvar;
    }

    public void setExpfluxIvar(float[] expfluxIvar) {
        this.expfluxIvar = expfluxIvar;
    }

    public float[] getAperflux() {
        return this.aperflux;
    }

    public void setAperflux(float[] aperflux) {
        this.aperflux = aperflux;
    }


    public float[] getCmodelfluxClean() {
        return this.cmodelfluxClean;
    }

    public void setCmodelfluxClean(float[] cmodelfluxClean) {
        this.cmodelfluxClean = cmodelfluxClean;
    }

    public float[] getCmodelfluxCleanIvar() {
        return this.cmodelfluxCleanIvar;
    }

    public void setCmodelfluxCleanIvar(float[] cmodelfluxCleanIvar) {
        this.cmodelfluxCleanIvar = cmodelfluxCleanIvar;
    }

    public float[] getCmodelfluxCleanVar() {
        return this.cmodelfluxCleanVar;
    }

    public void setCmodelfluxCleanVar(float[] cmodelfluxCleanVar) {
        this.cmodelfluxCleanVar = cmodelfluxCleanVar;
    }

    public float[] getCmodelfluxCleanChi2() {
        return this.cmodelfluxCleanChi2;
    }

    public void setCmodelfluxCleanChi2(float[] cmodelfluxCleanChi2) {
        this.cmodelfluxCleanChi2 = cmodelfluxCleanChi2;
    }

    public short[] getCmodelCleanNuse() {
        return this.cmodelCleanNuse;
    }

    public void setCmodelCleanNuse(short[] cmodelCleanNuse) {
        this.cmodelCleanNuse = cmodelCleanNuse;
    }

    public int[] getCmodelCleanMjdMaxdiff() {
        return this.cmodelCleanMjdMaxdiff;
    }

    public void setCmodelCleanMjdMaxdiff(int[] cmodelCleanMjdMaxdiff) {
        this.cmodelCleanMjdMaxdiff = cmodelCleanMjdMaxdiff;
    }

    public float[] getCmodelCleanMjdVar() {
        return this.cmodelCleanMjdVar;
    }

    public void setCmodelCleanMjdVar(float[] cmodelCleanMjdVar) {
        this.cmodelCleanMjdVar = cmodelCleanMjdVar;
    }

    public float[] getModelfluxClean() {
        return this.modelfluxClean;
    }

    public void setModelfluxClean(float[] modelfluxClean) {
        this.modelfluxClean = modelfluxClean;
    }

    public float[] getModelfluxCleanIvar() {
        return this.modelfluxCleanIvar;
    }

    public void setModelfluxCleanIvar(float[] modelfluxCleanIvar) {
        this.modelfluxCleanIvar = modelfluxCleanIvar;
    }

    public float[] getModelfluxCleanVar() {
        return this.modelfluxCleanVar;
    }

    public void setModelfluxCleanVar(float[] modelfluxCleanVar) {
        this.modelfluxCleanVar = modelfluxCleanVar;
    }

    public float[] getModelfluxCleanChi2() {
        return this.modelfluxCleanChi2;
    }

    public void setModelfluxCleanChi2(float[] modelfluxCleanChi2) {
        this.modelfluxCleanChi2 = modelfluxCleanChi2;
    }

    public short[] getModelCleanNuse() {
        return this.modelCleanNuse;
    }

    public void setModelCleanNuse(short[] modelCleanNuse) {
        this.modelCleanNuse = modelCleanNuse;
    }

    public int[] getModelCleanMjdMaxdiff() {
        return this.modelCleanMjdMaxdiff;
    }

    public void setModelCleanMjdMaxdiff(int[] modelCleanMjdMaxdiff) {
        this.modelCleanMjdMaxdiff = modelCleanMjdMaxdiff;
    }

    public float[] getModelCleanMjdVar() {
        return this.modelCleanMjdVar;
    }

    public void setModelCleanMjdVar(float[] modelCleanMjdVar) {
        this.modelCleanMjdVar = modelCleanMjdVar;
    }

    public float[] getPsffluxClean() {
        return this.psffluxClean;
    }

    public void setPsffluxClean(float[] psffluxClean) {
        this.psffluxClean = psffluxClean;
    }

    public float[] getPsffluxCleanIvar() {
        return this.psffluxCleanIvar;
    }

    public void setPsffluxCleanIvar(float[] psffluxCleanIvar) {
        this.psffluxCleanIvar = psffluxCleanIvar;
    }

    public float[] getPsffluxCleanVar() {
        return this.psffluxCleanVar;
    }

    public void setPsffluxCleanVar(float[] psffluxCleanVar) {
        this.psffluxCleanVar = psffluxCleanVar;
    }

    public float[] getPsffluxCleanChi2() {
        return this.psffluxCleanChi2;
    }

    public void setPsffluxCleanChi2(float[] psffluxCleanChi2) {
        this.psffluxCleanChi2 = psffluxCleanChi2;
    }

    public short[] getPsfCleanNuse() {
        return this.psfCleanNuse;
    }

    public void setPsfCleanNuse(short[] psfCleanNuse) {
        this.psfCleanNuse = psfCleanNuse;
    }

    public int[] getPsfCleanMjdMaxdiff() {
        return this.psfCleanMjdMaxdiff;
    }

    public void setPsfCleanMjdMaxdiff(int[] psfCleanMjdMaxdiff) {
        this.psfCleanMjdMaxdiff = psfCleanMjdMaxdiff;
    }

    public float[] getPsfCleanMjdVar() {
        return this.psfCleanMjdVar;
    }

    public void setPsfCleanMjdVar(float[] psfCleanMjdVar) {
        this.psfCleanMjdVar = psfCleanMjdVar;
    }

    private void setRows(Object[] row) {
        FitsUtil f = new FitsUtil();
        int n = 0;
        this.run = f.toShort(row[n++]);     // RUN $1 Short I
        this.rerun = (String) row[n++];   // RERUN $2 String 3A
        this.camcol = f.uShort(row[n++]);    // CAMCOL $3 Short B true
        this.field = f.toShort(row[n++]);    // FIELD $4 Short I
        this.id = f.toShort(row[n++]);    // ID $5 Short I row index
        this.objcType = f.toInt(row[n++]);  // OBJC_TYPE $6 Integer J
        this.objcFlags = f.toInt(row[n++]); // OBJC_FLAGS $7 Integer J
        this.objcFlags2 = f.toInt(row[n++]); // OBJC_FLAGS2 $8 Integer J
        this.rowc = f.floatArray(row[n++]);    // ROWC $9 float[] 5 5E
        this.colc = f.floatArray(row[n++]);    // COLC $10 float[] 5 5E
        this.petroth50 = f.floatArray(row[n++]);  // PETROTH50 $11 float[] 5 5E
        this.petroth90 = f.floatArray(row[n++]);  // PETROTH90 $12 float[] 5 5E
        this.MRrCc = f.floatArray(row[n++]); // M_RR_CC $13 float[] 5 5E
        this.MRrCcPsf = f.floatArray(row[n++]);  // M_RR_CC_PSF $14 float[] 5 5E
        this.thetaDev = f.floatArray(row[n++]);  // THETA_DEV $15 float[] 5 5E
        this.thetaExp = f.floatArray(row[n++]);  // THETA_EXP $16 float[] 5 5E
        this.fracdev = f.floatArray(row[n++]);  // FRACDEV $17 float[] 5 5E
        this.flags1 = f.intArray(row[n++]);  // FLAGS $18 int[] 5 5J
        this.flags2 = f.intArray(row[n++]);  // FLAGS2 $19 int[] 5 5J
        this.starLnl = f.floatArray(row[n++]);  // STAR_LNL $20 float[] 5 5E
        this.expLnl = f.floatArray(row[n++]);  // EXP_LNL $21 float[] 5 5E
        this.devLnl = f.floatArray(row[n++]);  // DEV_LNL $22 float[] 5 5E
        this.pspStatus = f.intArray(row[n++]);// PSP_STATUS $23 int[] 5 5J
        this.ra = f.toDouble(row[n++]);  // RA $24 Double D
        this.dec = f.toDouble(row[n++]);  // DEC $25 Double D
        this.psfFwhm = f.floatArray(row[n++]);  // PSF_FWHM $26 float[] 5 5E
        this.extinction = f.floatArray(row[n++]);  // EXTINCTION $27 float[] 5 5E
        this.psfflux = f.floatArray(row[n++]);  // PSFFLUX $28 float[] 5 5E
        this.psffluxIvar = f.floatArray(row[n++]);  // PSFFLUX_IVAR $29 float[] 5 5E
        this.fiberflux = f.floatArray(row[n++]);  // FIBERFLUX $30 float[] 5 5E
        this.fiberfluxIvar = f.floatArray(row[n++]);  // FIBERFLUX_IVAR $31 float[] 5 5E
        this.fiber2flux = f.floatArray(row[n++]);  // FIBER2FLUX $32 float[] 5 5E
        this.fiber2fluxIvar = f.floatArray(row[n++]);  // FIBER2FLUX_IVAR $33 float[] 5 5E
        this.modelflux = f.floatArray(row[n++]);  // MODELFLUX $34 float[] 5 5E
        this.modelfluxIvar = f.floatArray(row[n++]);  // MODELFLUX_IVAR $35 float[] 5 5E
        this.petroflux = f.floatArray(row[n++]);  // PETROFLUX $36 float[] 5 5E
        this.petrofluxIvar = f.floatArray(row[n++]);  // PETROFLUX_IVAR $37 float[] 5 5E
        this.devflux = f.floatArray(row[n++]);  // DEVFLUX $38 float[] 5 5E
        this.devfluxIvar = f.floatArray(row[n++]);  // DEVFLUX_IVAR $39 float[] 5 5E
        this.expflux = f.floatArray(row[n++]);  // EXPFLUX $40 float[] 5 5E
        this.expfluxIvar = f.floatArray(row[n++]);  // EXPFLUX_IVAR $41 float[] 5 5E
        n++;//this.aperflux = f.floatArray(row[n++]);  // APERFLUX $42 float[] 40 40
        this.calibStatus = f.intArray(row[n++]);  // CALIB_STATUS $43 int[] 5 5J
        this.nmgypercount = f.floatArray(row[n++]);  // NMGYPERCOUNT $44 float[] 5 5E
        this.resolveStatus = f.toInt(row[n++]);  // RESOLVE_STATUS $45 Integer J
        this.thingId = f.toInt(row[n++]);  // THING_ID $46 Integer J
        this.ifield = f.toInt(row[n++]);  // IFIELD $47 Integer J
        this.balkanId = f.toInt(row[n++]);  // BALKAN_ID $48 Integer J
        this.ndetect = f.toInt(row[n++]);  // NDETECT $49 Integer J
        this.cmodelfluxClean = f.floatArray(row[n++]);// CMODELFLUX_CLEAN $50 float[] 5 5E
        this.cmodelfluxCleanIvar = f.floatArray(row[n++]);// CMODELFLUX_CLEAN_IVAR $51 float[] 5 5E
        this.cmodelfluxCleanVar = f.floatArray(row[n++]);// CMODELFLUX_CLEAN_VAR $52 float[] 5 5E
        this.cmodelfluxCleanChi2 = f.floatArray(row[n++]);// CMODELFLUX_CLEAN_CHI2 $53 float[] 5 5E
        this.cmodelCleanNuse = f.shortArray(row[n++]);// CMODEL_CLEAN_NUSE $54 short[] 5 5I
        this.cmodelCleanMjdMaxdiff = f.intArray(row[n++]);// CMODEL_CLEAN_MJD_MAXDIFF $55 int[] 5 5J
        this.cmodelCleanMjdVar = f.floatArray(row[n++]);// CMODEL_CLEAN_MJD_VAR $56 float[] 5 5E
        this.modelfluxClean = f.floatArray(row[n++]);// MODELFLUX_CLEAN $57 float[] 5 5E
        this.modelfluxCleanIvar = f.floatArray(row[n++]);// MODELFLUX_CLEAN_IVAR $58 float[] 5 5E
        this.modelfluxCleanVar = f.floatArray(row[n++]);// MODELFLUX_CLEAN_VAR $59 float[] 5 5E
        this.modelfluxCleanChi2 = f.floatArray(row[n++]);// MODELFLUX_CLEAN_CHI2 $60 float[] 5 5E
        this.modelCleanNuse = f.shortArray(row[n++]);// MODEL_CLEAN_NUSE $61 short[] 5 5I
        this.modelCleanMjdMaxdiff = f.intArray(row[n++]);// CMODEL_CLEAN_MJD_MAXDIFF $55 int[] 5 5J
        this.modelCleanMjdVar = f.floatArray(row[n++]);// CMODEL_CLEAN_MJD_VAR $56 float[] 5 5E
        this.psffluxClean = f.floatArray(row[n++]);// MODELFLUX_CLEAN $57 float[] 5 5E
        this.psffluxCleanIvar = f.floatArray(row[n++]);// MODELFLUX_CLEAN_IVAR $58 float[] 5 5E
        this.psffluxCleanVar = f.floatArray(row[n++]);// MODELFLUX_CLEAN_VAR $59 float[] 5 5E
        this.psffluxCleanChi2 = f.floatArray(row[n++]);// MODELFLUX_CLEAN_CHI2 $60 float[] 5 5E
        this.psfCleanNuse = f.shortArray(row[n++]);// MODEL_CLEAN_NUSE $61 short[] 5 5I
        this.psfCleanMjdMaxdiff = f.intArray(row[n++]);// CMODEL_CLEAN_MJD_MAXDIFF $55 int[] 5 5J
        this.psfCleanMjdVar = f.floatArray(row[n++]);// CMODEL_CLEAN_MJD_VAR $56 float[] 5 5E
    }

    @Override
    public String getObjid() {
        //TODO: convert to proper format
        return "" + run + rerun + camcol + field + objcType + id;
    }

    @Override
    public float getSize() {
        return getPetroth50()[0];
    }

}
