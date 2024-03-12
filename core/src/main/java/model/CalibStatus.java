package model;

/**
 * Created by Graham Perry on 05/09/16.
 *
 * @author Graham Perry
 */
public class CalibStatus {
    public static String photometricDesc = "Photometric observations";
    public static String unphotOverlapDesc = "Unphotometric observations, calibrated based on overlaps with clear, ubercalibrated data; done on a field-by-field basis. Use with caution.";
    public static String unphotExtrapClearDesc = "Extrapolate the solution from the clear part of a night (that was ubercalibrated) to the cloudy part. Not recommended for use.";
    public static String unphotExtrapCloudyDesc = "Extrapolate the solution from a cloudy part of the night (where there is overlap) to a region of no overlap. Not recommended for use.";
    public static String unphotDisjointDesc = "Data is disjoint from the rest of the survey (even though conditions may be photometric), the calibration is suspect. Not recommended for use.";
    public static String incrementCalibDesc = "Incrementally calibrated by considering overlaps with ubercalibrated data";
    public static String reserved2Desc = "reserved for future use";
    public static String reserved3Desc = "reserved for future use";
    public static String ptClearDesc = "(INTERNAL USE ONLY in DR8 and later) PT calibration for clear data";
    public static String ptCloudyDesc = "(INTERNAL USE ONLY in DR8 and later) PT calibration for cloudy data";
    public static String defaultCalDesc = "(INTERNAL USE ONLY in DR8 and later) a default calibration used";
    public static String noUbercalDesc = "(INTERNAL USE ONLY in DR8 and later) not uber-calibrated";
    private final int photometric = 1 << 0;
    private final int unphotOverlap = 1 << 1;
    private final int unphotExtrapClear = 1 << 2;
    private final int unphotExtrapCloudy = 1 << 3;
    private final int unphotDisjoint = 1 << 4;
    private final int incrementCalib = 1 << 5;
    private final int reserved2 = 1 << 6;
    private final int reserved3 = 1 << 7;
    private final int ptClear = 1 << 8;
    private final int ptCloudy = 1 << 9;
    private final int defaultCal = 1 << 10;
    private final int noUbercal = 1 << 11;
    private final int[] calibStatus;
    public CalibStatus(int[] CalibStatus){
        this.calibStatus = CalibStatus;
    }

    public CalibStatus(int CalibStatus){
        this.calibStatus = new int[1];
        this.calibStatus[0] = CalibStatus;
    }

    public boolean isPhotometric() {
        return (calibStatus[0] & photometric) != 0;
    }

    public boolean isUnphotOverlap() {
        return (calibStatus[0] & unphotOverlap) != 0;
    }

    public boolean isUnphotExtrapClear() {
        return (calibStatus[0] & unphotExtrapClear) != 0;
    }

    public boolean isUnphotExtrapCloudy() {
        return (calibStatus[0] & unphotExtrapCloudy) != 0;
    }

    public boolean isUnphotDisjoint() {
        return (calibStatus[0] & unphotDisjoint) != 0;
    }

    public boolean isIncrementCalib() {
        return (calibStatus[0] & incrementCalib) != 0;
    }

    public boolean isReserved2() {
        return (calibStatus[0] & reserved2) != 0;
    }

    public boolean isReserved3() {
        return (calibStatus[0] & reserved3) != 0;
    }

    public boolean isPtClear() {
        return (calibStatus[0] & ptClear) != 0;
    }

    public boolean isPtCloudy() {
        return (calibStatus[0] & ptCloudy) != 0;
    }

    public boolean isDefaultCal() {
        return (calibStatus[0] & defaultCal) != 0;
    }

    public boolean isNoUbercal() {
        return (calibStatus[0] & noUbercal) != 0;
    }

    public boolean isPhotometric_u() {
        return (calibStatus[Filter.u.index()] & photometric) != 0;
    }

    public boolean isUnphotOverlap_u() {
        return (calibStatus[Filter.u.index()] & unphotOverlap) != 0;
    }

    public boolean isUnphotExtrapClear_u() {
        return (calibStatus[Filter.u.index()] & unphotExtrapClear) != 0;
    }

    public boolean isUnphotExtrapCloudy_u() {
        return (calibStatus[Filter.u.index()] & unphotExtrapCloudy) != 0;
    }

    public boolean isUnphotDisjoint_u() {
        return (calibStatus[Filter.u.index()] & unphotDisjoint) != 0;
    }

    public boolean isIncrementCalib_u() {
        return (calibStatus[Filter.u.index()] & incrementCalib) != 0;
    }

    public boolean isReserved2_u() {
        return (calibStatus[Filter.u.index()] & reserved2) != 0;
    }

    public boolean isReserved3_u() {
        return (calibStatus[Filter.u.index()] & reserved3) != 0;
    }

    public boolean isPtClear_u() {
        return (calibStatus[Filter.u.index()] & ptClear) != 0;
    }

    public boolean isPtCloudy_u() {
        return (calibStatus[Filter.u.index()] & ptCloudy) != 0;
    }

    public boolean isDefaultCal_u() {
        return (calibStatus[Filter.u.index()] & defaultCal) != 0;
    }

    public boolean isNoUbercal_u() {
        return (calibStatus[Filter.u.index()] & noUbercal) != 0;
    }



    public boolean isPhotometric_g() {
        return (calibStatus[Filter.g.index()] & photometric) != 0;
    }

    public boolean isUnphotOverlap_g() {
        return (calibStatus[Filter.g.index()] & unphotOverlap) != 0;
    }

    public boolean isUnphotExtrapClear_g() {
        return (calibStatus[Filter.g.index()] & unphotExtrapClear) != 0;
    }

    public boolean isUnphotExtrapCloudy_g() {
        return (calibStatus[Filter.g.index()] & unphotExtrapCloudy) != 0;
    }

    public boolean isUnphotDisjoint_g() {
        return (calibStatus[Filter.g.index()] & unphotDisjoint) != 0;
    }

    public boolean isIncrementCalib_g() {
        return (calibStatus[Filter.g.index()] & incrementCalib) != 0;
    }

    public boolean isReserved2_g() {
        return (calibStatus[Filter.g.index()] & reserved2) != 0;
    }

    public boolean isReserved3_g() {
        return (calibStatus[Filter.g.index()] & reserved3) != 0;
    }

    public boolean isPtClear_g() {
        return (calibStatus[Filter.g.index()] & ptClear) != 0;
    }

    public boolean isPtCloudy_g() {
        return (calibStatus[Filter.g.index()] & ptCloudy) != 0;
    }

    public boolean isDefaultCal_g() {
        return (calibStatus[Filter.g.index()] & defaultCal) != 0;
    }

    public boolean isNoUbercal_g() {
        return (calibStatus[Filter.g.index()] & noUbercal) != 0;
    }
    public boolean isPhotometric_r() {
        return (calibStatus[Filter.r.index()] & photometric) != 0;
    }

    public boolean isUnphotOverlap_r() {
        return (calibStatus[Filter.r.index()] & unphotOverlap) != 0;
    }

    public boolean isUnphotExtrapClear_r() {
        return (calibStatus[Filter.r.index()] & unphotExtrapClear) != 0;
    }

    public boolean isUnphotExtrapCloudy_r() {
        return (calibStatus[Filter.r.index()] & unphotExtrapCloudy) != 0;
    }

    public boolean isUnphotDisjoint_r() {
        return (calibStatus[Filter.r.index()] & unphotDisjoint) != 0;
    }

    public boolean isIncrementCalib_r() {
        return (calibStatus[Filter.r.index()] & incrementCalib) != 0;
    }

    public boolean isReserved2_r() {
        return (calibStatus[Filter.r.index()] & reserved2) != 0;
    }

    public boolean isReserved3_r() {
        return (calibStatus[Filter.r.index()] & reserved3) != 0;
    }

    public boolean isPtClear_r() {
        return (calibStatus[Filter.r.index()] & ptClear) != 0;
    }

    public boolean isPtCloudy_r() {
        return (calibStatus[Filter.r.index()] & ptCloudy) != 0;
    }

    public boolean isDefaultCal_r() {
        return (calibStatus[Filter.r.index()] & defaultCal) != 0;
    }

    public boolean isNoUbercal_r() {
        return (calibStatus[Filter.r.index()] & noUbercal) != 0;
    }
    public boolean isPhotometric_i() {
        return (calibStatus[Filter.i.index()] & photometric) != 0;
    }

    public boolean isUnphotOverlap_i() {
        return (calibStatus[Filter.i.index()] & unphotOverlap) != 0;
    }

    public boolean isUnphotExtrapClear_i() {
        return (calibStatus[Filter.i.index()] & unphotExtrapClear) != 0;
    }

    public boolean isUnphotExtrapCloudy_i() {
        return (calibStatus[Filter.i.index()] & unphotExtrapCloudy) != 0;
    }

    public boolean isUnphotDisjoint_i() {
        return (calibStatus[Filter.i.index()] & unphotDisjoint) != 0;
    }

    public boolean isIncrementCalib_i() {
        return (calibStatus[Filter.i.index()] & incrementCalib) != 0;
    }

    public boolean isReserved2_i() {
        return (calibStatus[Filter.i.index()] & reserved2) != 0;
    }

    public boolean isReserved3_i() {
        return (calibStatus[Filter.i.index()] & reserved3) != 0;
    }

    public boolean isPtClear_i() {
        return (calibStatus[Filter.i.index()] & ptClear) != 0;
    }

    public boolean isPtCloudy_i() {
        return (calibStatus[Filter.i.index()] & ptCloudy) != 0;
    }

    public boolean isDefaultCal_i() {
        return (calibStatus[Filter.i.index()] & defaultCal) != 0;
    }

    public boolean isNoUbercal_i() {
        return (calibStatus[Filter.i.index()] & noUbercal) != 0;
    }
    public boolean isPhotometric_z() {
        return (calibStatus[Filter.z.index()] & photometric) != 0;
    }

    public boolean isUnphotOverlap_z() {
        return (calibStatus[Filter.z.index()] & unphotOverlap) != 0;
    }

    public boolean isUnphotExtrapClear_z() {
        return (calibStatus[Filter.z.index()] & unphotExtrapClear) != 0;
    }

    public boolean isUnphotExtrapCloudy_z() {
        return (calibStatus[Filter.z.index()] & unphotExtrapCloudy) != 0;
    }

    public boolean isUnphotDisjoint_z() {
        return (calibStatus[Filter.z.index()] & unphotDisjoint) != 0;
    }

    public boolean isIncrementCalib_z() {
        return (calibStatus[Filter.z.index()] & incrementCalib) != 0;
    }

    public boolean isReserved2_z() {
        return (calibStatus[Filter.z.index()] & reserved2) != 0;
    }

    public boolean isReserved3_z() {
        return (calibStatus[Filter.z.index()] & reserved3) != 0;
    }

    public boolean isPtClear_z() {
        return (calibStatus[Filter.z.index()] & ptClear) != 0;
    }

    public boolean isPtCloudy_z() {
        return (calibStatus[Filter.z.index()] & ptCloudy) != 0;
    }

    public boolean isDefaultCal_z() {
        return (calibStatus[Filter.z.index()] & defaultCal) != 0;
    }

    public boolean isNoUbercal_z() {
        return (calibStatus[Filter.z.index()] & noUbercal) != 0;
    }
}
