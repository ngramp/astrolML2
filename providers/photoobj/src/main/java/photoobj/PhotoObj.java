package photoobj;

import model.*;
import util.FitsUtil;

/**
 * created by graham perry on 13/07/16.
 *
 * @author graham perry
 *         https://data.sdss.org/datamodel/files/BOSS_PHOTOOBJ/RERUN/RUN/CAMCOL/photoObj.html
 */
public class PhotoObj implements CosObject {
    private String objid;//String;//    unique sdss identifier composed from [skyversion,rerun,run,camcol,field,obj].
    private String parentid;//String;//    object identifier of parent (if object deblended) or bright detection (if object has one), else 0
    private String fieldid;//String;//    field identifier composed of [run, camcol, field, rerun, skyversion].
    private short skyversion;//int8;//    layer of catalog (currently only one layer, 0)
    private short mode;//int8;//    1: primary, 2: secondary, 3: other
    private short clean;//int32;//    clean photometry flag for point sources (1=clean, 0=unclean).
    private short run;//int32;//run number
    private String rerun;//String;//rerun number
    private short camcol;//int32;//camera column
    private short field;//int32;//field number
    private short id;//int32;//the object id within a field. usually changes between reruns of the same field
    private short parent;//int32;//parent id, if there is one
    private short nchild;//int32;//number of children if this is a composite object that has been deblended. bright (in a flags sense) objects also have nchild == 1, the non-bright sibling.
    private int objc_type;//int16;//type classification of the object (star, galaxy, cosmic ray, etc.)
    private float objc_prob_psf;//float32;//probability that the object is a star. currently 0 if type == 3 (galaxy), 1 if type == 6 (star).
    private int objc_flags;//int32;//photo object attribute flags
    private int objc_flags2;//int32;//second set of photo object attribute flags
    private float objc_rowc;//float32;//row center position (r-band coordinates) (pix)
    private float objc_rowcerr;//float32;//row center position error (r-band coordinates) (pix)
    private float objc_colc;//float32;//column center position (r-band coordinates) (pix)
    private float objc_colcerr;//float32;//column center position error (r-band coordinates) (pix)
    private float rowvdeg;//float32;//row-component of object's velocity (deg/day)
    private float rowvdegerr;//float32;//row-component of object's velocity error (deg/day)
    private float colvdeg;//float32;//column-component of object's velocity;//(deg/day)
    private float colvdegerr;//float32;//column-component of object's velocity error (deg/day)
    private float[] rowc;//float32[5];//row center (pix)
    private float[] rowcerr;//float32[5];//error row center error (pix)
    private float[] colc;//float32[5];//column center (pix)
    private float[] colcerr;//float32[5];//column center error (pix)
    private float[] petrotheta;//float32[5];//petrosian radius (arcsec)
    private float[] petrothetaerr;//float32[5];//petrosian radius error (arcsec)
    private float[] petroth50;//float32[5];//radius containing 50% of petrosian flux (arcsec)
    private float[] petroth50err;//float32[5];//error in radius with 50% of petrosian flux error (arcsec)
    private float[] petroth90;//float32[5];//radius containing 90% of petrosian flux (arcsec)
    private float[] petroth90err;//float32[5];//error in radius with 90% of petrosian flux error (arcsec)
    private float[] q;//float32[5];//stokes q parameter
    private float[] qerr;//float32[5];//stokes q parameter error
    private float[] u;//float32[5];//stokes u parameter
    private float[] uerr;//float32[5];//stokes u parameter error
    private float[] m_e1;//float32[5];//adaptive e1 shape measure (pixel coordinates)
    private float[] m_e2;//float32[5];//adaptive e2 shape measure (pixel coordinates)
    private float[] m_e1e1err;//float32[5];//covariance in e1/e1 shape measure (pixel coordinates)
    private float[] m_e1e2err;//float32[5];//covariance in e1/e2 shape measure (pixel coordinates)
    private float[] m_e2e2err;//float32[5];//covariance in e2/e2 shape measure (pixel coordinates)
    private float[] m_rr_cc;//float32[5];//adaptive ( + ) (pixel coordinates)
    private float[] m_rr_ccerr;//float32[5];//error in adaptive ( + ) (pixel coordinates)
    private float[] m_cr4;//float32[5];//adaptive fourth moment of object (pixel coordinates)
    private float[] m_e1_psf;//float32[5];//adaptive e1 for psf (pixel coordinates)
    private float[] m_e2_psf;//float32[5];//adaptive e2 for psf (pixel coordinates)
    private float[] m_rr_cc_psf;//float32[5];//adaptive ( + ) for psf (pixel coordinates)
    private float[] m_cr4_psf;//float32[5];//adaptive fourth moment for psf (pixel coordinates)
    private float[] theta_dev;//float32[5];//de vaucouleurs fit major-axis scale radius, same as half-light or effective radius (arcsec)
    private float[] theta_deverr;//float32[5];//error in de vaucouleurs fit major-axis scale radius (arcsec)
    private float[] ab_dev;//float32[5];//de vaucouleurs fit b/a axis ratio
    private float[] ab_deverr;//float32[5];//de vaucouleurs fit b/a axis ratio error
    private float[] theta_exp;//float32[5];//exponential fit major-axis scale radius, here defined to be the same as half-light or effective radius (arcsec)
    private float[] theta_experr;//float32[5];//error in exponential fit major-axis scale radius (arcsec)
    private float[] ab_exp;//float32[5];//exponential fit b/a axis ratio
    private float[] ab_experr;//float32[5];//exponential fit b/a axis ratio error
    private float[] fracdev;//float32[5];//weight of dev component in dev+exp model
    private int[] flags;//int32[5];//object detection flags per band
    private int[] flags2;//int32[5];//second set of detection flags per band
    private int[] type;//int32[5];//object type classification per band
    private float[] prob_psf;//float32[5];//probability that the object is a star in each band
    private int[] nprof;//int32[5];//number of used profile bins
    private float[][] profmean_nmgy;//float32[15,5];//calibrated mean flux in each photo annulus
    private float[][] proferr_nmgy;//float32[15,5];//error in calibrated mean flux in each photo annulus
    private float star_lnl;//float32;//star ln(likelihood)
    private float exp_lnl;//float32;//exponential disk fit ln(likelihood)
    private float dev_lnl;//float32;//de vaucouleurs fit ln(likelihood)
    private int[] psp_status;//int32[5];//psp output status (xxx should this be in cas?)
    private float[] pixscale;//float32[5];//pixel scale, arcsec (xxx should this be in cas?)
    private double ra;//float64;//j2000 right ascension (from r-band, or best other band if r-band if too faint or saturated in r) (deg)
    private double dec;//float64;//j2000 declination (from r-band, or best other band if r-band if too faint or saturated in r) (deg)
    private double cx;//float64;//unit vector for ra+dec
    private double cy;//float64;//unit vector for ra+dec
    private double cz;//float64;//unit vector for ra+dec
    private double raerr;//float64;//error in ra (* cos(dec), that is, proper units) (arcsec)
    private double decerr;//float64;//error in dec (arcsec)
    private double l;//float64;//galactic longitude (deg)
    private double b;//float64;//galactic latitude (deg)
    private float[] offsetra;//float32[5];//filter position ra minus final ra (* cos(dec), that is, proper units) (arcsec)
    private float[] offsetdec;//float32[5];//filter position dec minus final dec (arcsec)
    private float[] psf_fwhm;//float32[5];//psf fwhm (arcsec)
    private int mjd;//int32;//date of observation (days)
    private float[] airmass;//float32[5];//airmass at time of observation
    private float[] phi_offset;//float32[5];//degrees to add to ccd-aligned angle to convert to e of n (deg)
    private float[] phi_dev_deg;//float32[5];//deg --/u de vaucouleurs fit position angle (+n thru e) (deg)
    private float[] phi_exp_deg;//float32;//exponential fit position angle (+n thru e) (deg)
    private float[] extinction;//float32[5];//galactic extinction (sfd) (mag)
    private float[] skyflux;//float32[5];//sky flux at the center of object (allowing for siblings if blended). (nanomaggies)
    private float[] skyflux_ivar;//float32[5];//sky flux inverse variance (nanomaggies)
    private float[] psfflux;//float32[5];//psf flux (nanomaggies)
    private float[] psffluxivar;//float32[5];//psf flux inverse variance (nanomaggies)
    private float[] psfmag;//float32[5];//psf magnitude (mag)
    private float[] psfmagerr;//float32[5];//psf magnitude error (mag)
    private float[] fiberflux;//float32[5];//flux in 3 arcsec diameter fiber radius (nanomaggies)
    private float[] fiberflux_ivar;//float32[5];//inverse variance in flux in 3 arcsec diameter fiber radius (nanomaggies)
    private float[] fibermag;//float32[5];//magnitude in 3 arcsec diameter fiber radius (mag)
    private float[] fibermagerr;//float32[5];//error in magnitude in 3 arcsec diameter fiber radius (mag)
    private float[] fiber2flux;//float32[5];//flux in 2 arcsec diameter fiber radius (nanomaggies)
    private float[] fiber2flux_ivar;//float32[5];//inverse variance in flux in 2 arcsec diameter fiber radius (nanomaggies)
    private float[] fiber2mag;//float32[5];//magnitude in 2 arcsec diameter fiber radius (mag)
    private float[] fiber2magerr;//float32[5];//error in magnitude in 2 arcsec diameter fiber radius (mag)
    private float[] cmodelflux;//float32[5];//dev+exp flux; result of fitting coefficients in linear combination of models, with structural parameters fixed based on individual fits (nanomaggies)
    private float[] cmodelflux_ivar;//float32[5];//inverse variance in dev+exp flux fit (nanomaggies)
    private float[] cmodelmag;//float32[5];//dev+exp magnitude; see cmodelflux (mag)
    private float[] cmodelmagerr;//float32[5];//dev+exp magnitude error (mag)
    private float[] modelflux;//float32[5];//better of dev/exp flux fit; for these measurements, the structural parameters used in every band, are the r-band values, and only the amplitude is varied (nanomaggies)
    private float[] modelflux_ivar;//float32[5];//inverse variance in better of dev/exp flux fit (nanomaggies)
    private float[] modelmag;//float32[5];//better of dev/exp magnitude fit; see modelflux (mag)
    private float[] modelmagerr;//float32[5];//error in better of dev/exp magnitude fit (mag)
    private float[] petroflux;//float32[5];//petrosian flux (nanomaggies)
    private float[] petroflux_ivar;//float32[5];//petrosian flux inverse variance (nanomaggies)
    private float[] petromag;//float32[5];//petrosian magnitude (mag)
    private float[] petromagerr;//float32[5];//petrosian magnitude error (mag)
    private float[] devflux;//float32[5];//nanomaggies (de)
    private float[] devflux_ivar;//float32[5];//nanomaggies^{-2} (de)
    private float[] devmag;//float32[5];//mag (de)
    private float[] devmagerr;//float32[5];//mag (de)
    private float[] expflux;//float32[5];//exponential fit flux (nanomaggies)
    private float[] expflux_ivar;//float32[5];//exponential fit flux inverse variance (nanomaggies)
    private float[] expmag;//float32[5];//exponential fit magnitude (mag)
    private float[] expmagerr;//float32[5];//exponential fit magnitude error (mag)
    private float[] aperflux;//float32[8,5];//aperture flux within [0.223, 0.670, 1.024, 1.745, 2.972, 4.584, 7.359, 11.306] arcsec in each band (nanomaggies)
    private float[] aperflux_ivar;//float32[8,5];//inverse variance of aperture flux values (1/nanomaggies2)
    private int[] cloudcam;//int32[5];//cloud camera status for observation
    private int[] calib_status;//int32[5];//calibration status of object
    private float[] nmgypercount;//float32[5];//nanomaggies per count (nmgy)
    private float[] nmgypercount_ivar;//float32[5];//inverse variance on nanomaggies per count (nmgy)
    private double[] tai;//float64[5];//time of observation (tai) in each filter (sec)
    private int resolve_status;//int32;//resolve status of object
    private int thing_id;//int32;//unique identifier from global resolve
    private int ifield;//int32;//the 0-indexed position of the field within window_flist.fits.
    private int balkan_id;//int32;//what balkan object is in from window
    private int nobserve;//int32;//number of observations of this position on the sky
    private int ndetect;//int32;//number of detections of this object
    private int nedge;//int32;//number of observations of this object near an edge (not included in nobserve or ndetect)
    private float score;//float32;//quality of field (0-1)

    public PhotoObj(String objid, String parentid, String fieldid, short skyversion, short mode, short clean, short run, String rerun, short camcol, short field, short id, short parent, short nchild, int objc_type, float objc_prob_psf, int objc_flags, int objc_flags2, float objc_rowc, float objc_rowcerr, float objc_colc, float objc_colcerr, float rowvdeg, float rowvdegerr, float colvdeg, float colvdegerr, float[] rowc, float[] rowcerr, float[] colc, float[] colcerr, float[] petrotheta, float[] petrothetaerr, float[] petroth50, float[] petroth50err, float[] petroth90, float[] petroth90err, float[] q, float[] qerr, float[] u, float[] uerr, float[] m_e1, float[] m_e2, float[] m_e1e1err, float[] m_e1e2err, float[] m_e2e2err, float[] m_rr_cc, float[] m_rr_ccerr, float[] m_cr4, float[] m_e1_psf, float[] m_e2_psf, float[] m_rr_cc_psf, float[] m_cr4_psf, float[] theta_dev, float[] theta_deverr, float[] ab_dev, float[] ab_deverr, float[] theta_exp, float[] theta_experr, float[] ab_exp, float[] ab_experr, float[] fracdev, int[] flags, int[] flags2, int[] type, float[] prob_psf, int[] nprof, float[][] profmean_nmgy, float[][] proferr_nmgy, float star_lnl, float exp_lnl, float dev_lnl, int[] psp_status, float[] pixscale, double ra, double dec, double cx, double cy, double cz, double raerr, double decerr, double l, double b, float[] offsetra, float[] offsetdec, float[] psf_fwhm, int mjd, float[] airmass, float[] phi_offset, float[] phi_dev_deg, float[] phi_exp_deg, float[] extinction, float[] skyflux, float[] skyflux_ivar, float[] psfflux, float[] psffluxivar, float[] psfmag, float[] psfmagerr, float[] fiberflux, float[] fiberflux_ivar, float[] fibermag, float[] fibermagerr, float[] fiber2flux, float[] fiber2flux_ivar, float[] fiber2mag, float[] fiber2magerr, float[] cmodelflux, float[] cmodelflux_ivar, float[] cmodelmag, float[] cmodelmagerr, float[] modelflux, float[] modelflux_ivar, float[] modelmag, float[] modelmagerr, float[] petroflux, float[] petroflux_ivar, float[] petromag, float[] petromagerr, float[] devflux, float[] devflux_ivar, float[] devmag, float[] devmagerr, float[] expflux, float[] expflux_ivar, float[] expmag, float[] expmagerr, float[] aperflux, float[] aperflux_ivar, int[] cloudcam, int[] calib_status, float[] nmgypercount, float[] nmgypercount_ivar, double[] tai, int resolve_status, int thing_id, int ifield, int balkan_id, int nobserve, int ndetect, int nedge, float score) {
        this.objid = objid;
        this.parentid = parentid;
        this.fieldid = fieldid;
        this.skyversion = skyversion;
        this.mode = mode;
        this.clean = clean;
        this.run = run;
        this.rerun = rerun;
        this.camcol = camcol;
        this.field = field;
        this.id = id;
        this.parent = parent;
        this.nchild = nchild;
        this.objc_type = objc_type;
        this.objc_prob_psf = objc_prob_psf;
        this.objc_flags = objc_flags;
        this.objc_flags2 = objc_flags2;
        this.objc_rowc = objc_rowc;
        this.objc_rowcerr = objc_rowcerr;
        this.objc_colc = objc_colc;
        this.objc_colcerr = objc_colcerr;
        this.rowvdeg = rowvdeg;
        this.rowvdegerr = rowvdegerr;
        this.colvdeg = colvdeg;
        this.colvdegerr = colvdegerr;
        this.rowc = rowc;
        this.rowcerr = rowcerr;
        this.colc = colc;
        this.colcerr = colcerr;
        this.petrotheta = petrotheta;
        this.petrothetaerr = petrothetaerr;
        this.petroth50 = petroth50;
        this.petroth50err = petroth50err;
        this.petroth90 = petroth90;
        this.petroth90err = petroth90err;
        this.q = q;
        this.qerr = qerr;
        this.u = u;
        this.uerr = uerr;
        this.m_e1 = m_e1;
        this.m_e2 = m_e2;
        this.m_e1e1err = m_e1e1err;
        this.m_e1e2err = m_e1e2err;
        this.m_e2e2err = m_e2e2err;
        this.m_rr_cc = m_rr_cc;
        this.m_rr_ccerr = m_rr_ccerr;
        this.m_cr4 = m_cr4;
        this.m_e1_psf = m_e1_psf;
        this.m_e2_psf = m_e2_psf;
        this.m_rr_cc_psf = m_rr_cc_psf;
        this.m_cr4_psf = m_cr4_psf;
        this.theta_dev = theta_dev;
        this.theta_deverr = theta_deverr;
        this.ab_dev = ab_dev;
        this.ab_deverr = ab_deverr;
        this.theta_exp = theta_exp;
        this.theta_experr = theta_experr;
        this.ab_exp = ab_exp;
        this.ab_experr = ab_experr;
        this.fracdev = fracdev;
        this.flags = flags;
        this.flags2 = flags2;
        this.type = type;
        this.prob_psf = prob_psf;
        this.nprof = nprof;
        this.profmean_nmgy = profmean_nmgy;
        this.proferr_nmgy = proferr_nmgy;
        this.star_lnl = star_lnl;
        this.exp_lnl = exp_lnl;
        this.dev_lnl = dev_lnl;
        this.psp_status = psp_status;
        this.pixscale = pixscale;
        this.ra = ra;
        this.dec = dec;
        this.cx = cx;
        this.cy = cy;
        this.cz = cz;
        this.raerr = raerr;
        this.decerr = decerr;
        this.l = l;
        this.b = b;
        this.offsetra = offsetra;
        this.offsetdec = offsetdec;
        this.psf_fwhm = psf_fwhm;
        this.mjd = mjd;
        this.airmass = airmass;
        this.phi_offset = phi_offset;
        this.phi_dev_deg = phi_dev_deg;
        this.phi_exp_deg = phi_exp_deg;
        this.extinction = extinction;
        this.skyflux = skyflux;
        this.skyflux_ivar = skyflux_ivar;
        this.psfflux = psfflux;
        this.psffluxivar = psffluxivar;
        this.psfmag = psfmag;
        this.psfmagerr = psfmagerr;
        this.fiberflux = fiberflux;
        this.fiberflux_ivar = fiberflux_ivar;
        this.fibermag = fibermag;
        this.fibermagerr = fibermagerr;
        this.fiber2flux = fiber2flux;
        this.fiber2flux_ivar = fiber2flux_ivar;
        this.fiber2mag = fiber2mag;
        this.fiber2magerr = fiber2magerr;
        this.cmodelflux = cmodelflux;
        this.cmodelflux_ivar = cmodelflux_ivar;
        this.cmodelmag = cmodelmag;
        this.cmodelmagerr = cmodelmagerr;
        this.modelflux = modelflux;
        this.modelflux_ivar = modelflux_ivar;
        this.modelmag = modelmag;
        this.modelmagerr = modelmagerr;
        this.petroflux = petroflux;
        this.petroflux_ivar = petroflux_ivar;
        this.petromag = petromag;
        this.petromagerr = petromagerr;
        this.devflux = devflux;
        this.devflux_ivar = devflux_ivar;
        this.devmag = devmag;
        this.devmagerr = devmagerr;
        this.expflux = expflux;
        this.expflux_ivar = expflux_ivar;
        this.expmag = expmag;
        this.expmagerr = expmagerr;
        this.aperflux = aperflux;
        this.aperflux_ivar = aperflux_ivar;
        this.cloudcam = cloudcam;
        this.calib_status = calib_status;
        this.nmgypercount = nmgypercount;
        this.nmgypercount_ivar = nmgypercount_ivar;
        this.tai = tai;
        this.resolve_status = resolve_status;
        this.thing_id = thing_id;
        this.ifield = ifield;
        this.balkan_id = balkan_id;
        this.nobserve = nobserve;
        this.ndetect = ndetect;
        this.nedge = nedge;
        this.score = score;
    }

    public PhotoObj(Object[] row) {
        FitsUtil f = new FitsUtil();
        try {
            int n = 0;
            this.objid = (String) row[n++];
            this.parentid = (String) row[n++];
            this.fieldid = (String) row[n++];
            this.skyversion = f.uShort(row[n++]);
            this.mode = f.uShort(row[n++]);
            this.clean = f.uShort(row[n++]);
            this.run = f.toShort(row[n++]);
            this.rerun = (String) row[n++];
            this.camcol = f.uShort(row[n++]);
            this.field = f.toShort(row[n++]);
            this.id = f.toShort(row[n++]);
            this.parent = f.toShort(row[n++]);
            this.nchild = f.toShort(row[n++]);
            this.objc_type = f.toInt(row[n++]);
            this.objc_prob_psf = f.toFloat(row[n++]);
            this.objc_flags = f.toInt(row[n++]);
            this.objc_flags2 = f.toInt(row[n++]);
            this.objc_rowc = f.toFloat(row[n++]);
            this.objc_rowcerr = f.toFloat(row[n++]);
            this.objc_colc = f.toFloat(row[n++]);
            this.objc_colcerr = f.toFloat(row[n++]);
            this.rowvdeg = f.toFloat(row[n++]);
            this.rowvdegerr = f.toFloat(row[n++]);
            this.colvdeg = f.toFloat(row[n++]);
            this.colvdegerr = f.toFloat(row[n++]);
            this.rowc = f.floatArray(row[n++]);
            this.rowcerr = f.floatArray(row[n++]);
            this.colc = f.floatArray(row[n++]);
            this.colcerr = f.floatArray(row[n++]);
            this.petrotheta = f.floatArray(row[n++]);
            this.petrothetaerr = f.floatArray(row[n++]);
            this.petroth50 = f.floatArray(row[n++]);
            this.petroth50err = f.floatArray(row[n++]);
            this.petroth90 = f.floatArray(row[n++]);
            this.petroth90err = f.floatArray(row[n++]);
            this.q = f.floatArray(row[n++]);
            this.qerr = f.floatArray(row[n++]);
            this.u = f.floatArray(row[n++]);
            this.uerr = f.floatArray(row[n++]);
            this.m_e1 = f.floatArray(row[n++]);
            this.m_e2 = f.floatArray(row[n++]);
            this.m_e1e1err = f.floatArray(row[n++]);
            this.m_e1e2err = f.floatArray(row[n++]);
            this.m_e2e2err = f.floatArray(row[n++]);
            this.m_rr_cc = f.floatArray(row[n++]);
            this.m_rr_ccerr = f.floatArray(row[n++]);
            this.m_cr4 = f.floatArray(row[n++]);
            this.m_e1_psf = f.floatArray(row[n++]);
            this.m_e2_psf = f.floatArray(row[n++]);
            this.m_rr_cc_psf = f.floatArray(row[n++]);
            this.m_cr4_psf = f.floatArray(row[n++]);
            this.theta_dev = f.floatArray(row[n++]);
            this.theta_deverr = f.floatArray(row[n++]);
            this.ab_dev = f.floatArray(row[n++]);
            this.ab_deverr = f.floatArray(row[n++]);
            this.theta_exp = f.floatArray(row[n++]);
            this.theta_experr = f.floatArray(row[n++]);
            this.ab_exp = f.floatArray(row[n++]);
            this.ab_experr = f.floatArray(row[n++]);
            this.fracdev = f.floatArray(row[n++]);
            this.flags = f.intArray(row[n++]);
            this.flags2 = f.intArray(row[n++]);
            this.type = f.intArray(row[n++]);
            this.prob_psf = f.floatArray(row[n++]);
            this.nprof = f.intArray(row[n++]);
            n++;//this.profmean_nmgy = ;
            n++;//this.proferr_nmgy = ;
            this.star_lnl = f.toFloat(row[n++]);
            this.exp_lnl = f.toFloat(row[n++]);
            this.dev_lnl = f.toFloat(row[n++]);
            this.psp_status = f.intArray(row[n++]);
            this.pixscale = f.floatArray(row[n++]);
            this.ra = f.toDouble(row[n++]);
            this.dec = f.toDouble(row[n++]);
            this.cx = f.toDouble(row[n++]);
            this.cy = f.toDouble(row[n++]);
            this.cz = f.toDouble(row[n++]);
            this.raerr = f.toDouble(row[n++]);
            this.decerr = f.toDouble(row[n++]);
            this.l = f.toDouble(row[n++]);
            this.b = f.toDouble(row[n++]);
            this.offsetra = f.floatArray(row[n++]);
            this.offsetdec = f.floatArray(row[n++]);
            this.psf_fwhm = f.floatArray(row[n++]);
            this.mjd = f.toInt(row[n++]);
            this.airmass = f.floatArray(row[n++]);
            this.phi_offset = f.floatArray(row[n++]);
            this.phi_dev_deg = f.floatArray(row[n++]);
            this.phi_exp_deg = f.floatArray(row[n++]);
            this.extinction = f.floatArray(row[n++]);
            this.skyflux = f.floatArray(row[n++]);
            this.skyflux_ivar = f.floatArray(row[n++]);
            this.psfflux = f.floatArray(row[n++]);
            this.psffluxivar = f.floatArray(row[n++]);
            this.psfmag = f.floatArray(row[n++]);
            this.psfmagerr = f.floatArray(row[n++]);
            this.fiberflux = f.floatArray(row[n++]);
            this.fiberflux_ivar = f.floatArray(row[n++]);
            this.fibermag = f.floatArray(row[n++]);
            this.fibermagerr = f.floatArray(row[n++]);
            this.fiber2flux = f.floatArray(row[n++]);
            this.fiber2flux_ivar = f.floatArray(row[n++]);
            this.fiber2mag = f.floatArray(row[n++]);
            this.fiber2magerr = f.floatArray(row[n++]);
            this.cmodelflux = f.floatArray(row[n++]);
            this.cmodelflux_ivar = f.floatArray(row[n++]);
            this.cmodelmag = f.floatArray(row[n++]);
            this.cmodelmagerr = f.floatArray(row[n++]);
            this.modelflux = f.floatArray(row[n++]);
            this.modelflux_ivar = f.floatArray(row[n++]);
            this.modelmag = f.floatArray(row[n++]);
            this.modelmagerr = f.floatArray(row[n++]);
            this.petroflux = f.floatArray(row[n++]);
            this.petroflux_ivar = f.floatArray(row[n++]);
            this.petromag = f.floatArray(row[n++]);
            this.petromagerr = f.floatArray(row[n++]);
            this.devflux = f.floatArray(row[n++]);
            this.devflux_ivar = f.floatArray(row[n++]);
            this.devmag = f.floatArray(row[n++]);
            this.devmagerr = f.floatArray(row[n++]);
            this.expflux = f.floatArray(row[n++]);
            this.expflux_ivar = f.floatArray(row[n++]);
            this.expmag = f.floatArray(row[n++]);
            this.expmagerr = f.floatArray(row[n++]);
            n++;//this.aperflux = f.floatArray(row[n++]);
            n++;//this.aperflux_ivar = f.floatArray(row[n++]);
            this.cloudcam = f.intArray(row[n++]);
            this.calib_status = f.intArray(row[n++]);
            this.nmgypercount = f.floatArray(row[n++]);
            this.nmgypercount_ivar = f.floatArray(row[n++]);
            this.tai = f.doubleArray(row[n++]);
            this.resolve_status = f.toInt(row[n++]);
            this.thing_id = f.toInt(row[n++]);
            this.ifield = f.toInt(row[n++]);
            this.balkan_id = f.toInt(row[n++]);
            this.nobserve = f.toInt(row[n++]);
            this.ndetect = f.toInt(row[n++]);
            this.nedge = f.toInt(row[n++]);
            this.score = f.toFloat(row[n++]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public PhotoObj() {

    }

    public String getObjid() {
        return objid;
    }

    public String getParentid() {
        return parentid;
    }

    public String getFieldid() {
        return fieldid;
    }

    public short getSkyversion() {
        return skyversion;
    }

    public short getMode() {
        return mode;
    }

    public short getClean() {
        return clean;
    }

    public short getRun() {
        return run;
    }

    public String getRerun() {
        return rerun;
    }

    public short getCamcol() {
        return camcol;
    }

    public short getField() {
        return field;
    }

    public short getId() {
        return id;
    }

    public short getParent() {
        return parent;
    }

    public short getNchild() {
        return nchild;
    }


    public float getObjc_prob_psf() {
        return objc_prob_psf;
    }

    public int getObjc_flags() {
        return objc_flags;
    }

    public int getObjc_flags2() {
        return objc_flags2;
    }

    public float getObjc_rowc() {
        return objc_rowc;
    }

    public float getObjc_rowcerr() {
        return objc_rowcerr;
    }

    public float getObjc_colc() {
        return objc_colc;
    }

    public float getObjc_colcerr() {
        return objc_colcerr;
    }

    public float getRowvdeg() {
        return rowvdeg;
    }

    public float getRowvdegerr() {
        return rowvdegerr;
    }

    public float getColvdeg() {
        return colvdeg;
    }

    public float getColvdegerr() {
        return colvdegerr;
    }

    public float[] getRowc() {
        return rowc;
    }

    public float[] getRowcerr() {
        return rowcerr;
    }

    public float[] getColc() {
        return colc;
    }

    public float[] getColcerr() {
        return colcerr;
    }

    public float[] getPetrotheta() {
        return petrotheta;
    }

    public float[] getPetrothetaerr() {
        return petrothetaerr;
    }

    public float[] getPetroth50() {
        return petroth50;
    }

    public float[] getPetroth50err() {
        return petroth50err;
    }

    public float[] getPetroth90() {
        return petroth90;
    }

    public float[] getPetroth90err() {
        return petroth90err;
    }

    public float[] getQ() {
        return q;
    }

    public float[] getQerr() {
        return qerr;
    }

    public float[] getU() {
        return u;
    }

    public float[] getUerr() {
        return uerr;
    }

    public float[] getM_e1() {
        return m_e1;
    }

    public float[] getM_e2() {
        return m_e2;
    }

    public float[] getM_e1e1err() {
        return m_e1e1err;
    }

    public float[] getM_e1e2err() {
        return m_e1e2err;
    }

    public float[] getM_e2e2err() {
        return m_e2e2err;
    }

    public float[] getM_rr_cc() {
        return m_rr_cc;
    }

    public float[] getM_rr_ccerr() {
        return m_rr_ccerr;
    }

    public float[] getM_cr4() {
        return m_cr4;
    }

    public float[] getM_e1_psf() {
        return m_e1_psf;
    }

    public float[] getM_e2_psf() {
        return m_e2_psf;
    }

    public float[] getM_rr_cc_psf() {
        return m_rr_cc_psf;
    }

    public float[] getM_cr4_psf() {
        return m_cr4_psf;
    }

    public float[] getTheta_dev() {
        return theta_dev;
    }

    public float[] getTheta_deverr() {
        return theta_deverr;
    }

    public float[] getAb_dev() {
        return ab_dev;
    }

    public float[] getAb_deverr() {
        return ab_deverr;
    }

    public float[] getTheta_exp() {
        return theta_exp;
    }

    public float[] getTheta_experr() {
        return theta_experr;
    }

    public float[] getAb_exp() {
        return ab_exp;
    }

    public float[] getAb_experr() {
        return ab_experr;
    }

    public float[] getFracdev() {
        return fracdev;
    }


    public int[] getType() {
        return type;
    }

    public float[] getProb_psf() {
        return prob_psf;
    }

    public int[] getNprof() {
        return nprof;
    }

    public float[][] getProfmean_nmgy() {
        return profmean_nmgy;
    }

    public float[][] getProferr_nmgy() {
        return proferr_nmgy;
    }

    public float getStar_lnl() {
        return star_lnl;
    }

    public float getExp_lnl() {
        return exp_lnl;
    }

    public float getDev_lnl() {
        return dev_lnl;
    }

    public int[] getPsp_status() {
        return psp_status;
    }

    public float[] getPixscale() {
        return pixscale;
    }

    public double getRa() {
        return ra;
    }

    public double getDec() {
        return dec;
    }


    public double getCx() {
        return cx;
    }

    public double getCy() {
        return cy;
    }

    public double getCz() {
        return cz;
    }

    public double getRaerr() {
        return raerr;
    }

    public double getDecerr() {
        return decerr;
    }

    public double getL() {
        return l;
    }

    public double getB() {
        return b;
    }

    public float[] getOffsetra() {
        return offsetra;
    }

    public float[] getOffsetdec() {
        return offsetdec;
    }

    public float[] getPsf_fwhm() {
        return psf_fwhm;
    }

    public int getMjd() {
        return mjd;
    }

    public float[] getAirmass() {
        return airmass;
    }

    public float[] getPhi_offset() {
        return phi_offset;
    }

    public float[] getPhi_dev_deg() {
        return phi_dev_deg;
    }

    public float[] getPhi_exp_deg() {
        return phi_exp_deg;
    }

    public float[] getExtinction() {
        return extinction;
    }

    public float[] getSkyflux() {
        return skyflux;
    }

    public float[] getSkyflux_ivar() {
        return skyflux_ivar;
    }

    public float[] getPsfflux() {
        return psfflux;
    }

    public float[] getPsffluxivar() {
        return psffluxivar;
    }

    public float[] getPsfmag() {
        return psfmag;
    }

    public float[] getPsfmagerr() {
        return psfmagerr;
    }

    public float[] getFiberflux() {
        return fiberflux;
    }

    public float[] getFiberflux_ivar() {
        return fiberflux_ivar;
    }

    public float[] getFibermag() {
        return fibermag;
    }

    public float[] getFibermagerr() {
        return fibermagerr;
    }

    public float[] getFiber2flux() {
        return fiber2flux;
    }

    public float[] getFiber2flux_ivar() {
        return fiber2flux_ivar;
    }

    public float[] getFiber2mag() {
        return fiber2mag;
    }

    public float[] getFiber2magerr() {
        return fiber2magerr;
    }

    public float[] getCmodelflux() {
        return cmodelflux;
    }

    public float[] getCmodelflux_ivar() {
        return cmodelflux_ivar;
    }

    public float[] getCmodelmag() {
        return cmodelmag;
    }

    public float[] getCmodelmagerr() {
        return cmodelmagerr;
    }

    public float[] getModelflux() {
        return modelflux;
    }

    public float[] getModelflux_ivar() {
        return modelflux_ivar;
    }

    public float[] getModelmag() {
        return modelmag;
    }

    public float[] getModelmagerr() {
        return modelmagerr;
    }

    public float[] getPetroflux() {
        return petroflux;
    }

    public float[] getPetroflux_ivar() {
        return petroflux_ivar;
    }

    public float[] getPetromag() {
        return petromag;
    }

    public float[] getPetromagerr() {
        return petromagerr;
    }

    public float[] getDevflux() {
        return devflux;
    }

    public float[] getDevflux_ivar() {
        return devflux_ivar;
    }

    public float[] getDevmag() {
        return devmag;
    }

    public float[] getDevmagerr() {
        return devmagerr;
    }

    public float[] getExpflux() {
        return expflux;
    }

    public float[] getExpflux_ivar() {
        return expflux_ivar;
    }

    public float[] getExpmag() {
        return expmag;
    }

    public float[] getExpmagerr() {
        return expmagerr;
    }

    public float[] getAperflux() {
        return aperflux;
    }

    public float[] getAperflux_ivar() {
        return aperflux_ivar;
    }

    public int[] getCloudcam() {
        return cloudcam;
    }

    public int[] getCalib_status() {
        return calib_status;
    }

    public float[] getNmgypercount() {
        return nmgypercount;
    }

    public float[] getNmgypercount_ivar() {
        return nmgypercount_ivar;
    }

    public double[] getTai() {
        return tai;
    }


    public int getThing_id() {
        return thing_id;
    }

    public int getIfield() {
        return ifield;
    }

    public int getBalkan_id() {
        return balkan_id;
    }

    public int getNobserve() {
        return nobserve;
    }

    public int getNdetect() {
        return ndetect;
    }

    public int getNedge() {
        return nedge;
    }

    public float getScore() {
        return score;
    }

    @Override
    public float getSize() {
        return getPetroth50()[0];
    }

    @Override
    public ObjCType getObjType() {
        ObjCType ret = ObjCType.UNK;
        for (ObjCType t : ObjCType.values()){
            if(t.code() == objc_type){
                ret = t;
            }
        }
        return ret;
    }

    @Override
    public CalibStatus getCalibStatus() {
        return new CalibStatus(calib_status);
    }

    @Override
    public ResolveStatus getResolveStatus() {
        return new ResolveStatus(resolve_status);
    }

    @Override
    public Flags1 getFlags1() {
        return new Flags1(flags);
    }

    @Override
    public Flags2 getFlags2() {
        return new Flags2(flags2);
    }
}
