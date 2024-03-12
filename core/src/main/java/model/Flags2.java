package model;

/**
 * Created by Graham Perry on 05/09/16.
 *
 * @author Graham Perry
 */
public class Flags2 {
    public static String deblendedAsMovingDesc = "The object has the MOVED flag set, and was deblended on the assumption that it was moving.";
    public static String nodeblendMovingDesc = "The object has the MOVED flag set, but was not deblended as a moving object.";
    public static String tooFewDetectionsDesc = "The object has the MOVED flag set, but has too few detection to be deblended as moving.";
    public static String badMovingFitDesc = "The fit to the object as a moving object is too bad to be believed.";
    public static String stationaryDesc = "A moving objects velocity is consistent with zero.";
    public static String peaksTooCloseDesc = "Peaks in object were too close (set only in parent objects).";
    public static String binnedCenterDesc = "When centroiding the object the object’s size is larger than the (PSF) filter used to smooth the image.";
    public static String localEdgeDesc = "The object’s center in some band was too close to the edge of the frame to extract a profile.";
    public static String badCountsErrorDesc = "An object containing interpolated pixels had too few good pixels to form a reliable estimate of its error.";
    public static String badMovingFitChildDesc = "A putative moving child’s velocity fit was too poor, so it was discarded, and the parent was not deblended as moving.";
    public static String deblendUnassignedFluxDesc = "After deblending, the fraction of flux assigned to none of the children was too large (this flux is then shared out as described elsewhere).";
    public static String saturCenterDesc = "An object’s center is very close to at least one saturated pixel; the object may well be causing the saturation.";
    public static String interpCenterDesc = "An object’s center is very close to at least one interpolated pixel.";
    public static String deblendedAtEdgeDesc = "An object so close to the edge of the frame that it would not ordinarily be deblended has been deblended anyway. Only set for objects large enough to be EDGE in all fields/strips.";
    public static String deblendNopeakDesc = "A child had no detected peak in a given band, but we centroided it anyway and set the BINNED1.";
    public static String psfFluxInterpDesc = "The fraction of light actually detected (as opposed to guessed at by the interpolator) was less than some number (currently 80%) of the total.";
    public static String tooFewGoodDetectionsDesc = "A child of this object had too few good detections to be deblended as moving.";
    public static String centerOffAimageDesc = "At least one peak’s center lay off the atlas image in some band. This can happen when the object’s being deblended as moving, or if the astrometry is badly confused.";
    public static String deblendDegenerateDesc = "At least one potential child has been pruned because its template was too similar to some other child’s template.";
    public static String brightestGalaxyChildDesc = "This is the brightest child galaxy in a blend.";
    public static String canonicalBandDesc = "This band was the canonical band. This is the band used to measure the Petrosian radius used to calculate the Petrosian counts in each band, and to define the model used to calculate model colors; it has no effect upon the coordinate system used for the OBJC center.";
    public static String amomentUnweightedDesc = "‘Adaptive’ moments are actually unweighted.";
    public static String amomentShiftDesc = "Object’s center moved too far while determining adaptive moments. In this case, the M_e1 and M_e2 give the (row, column) shift, not the object’s shape.";
    public static String amomentMaxiterDesc = "Too many iterations while determining adaptive moments.";
    public static String maybeCrDesc = "This object may be a cosmic ray. This bit can get set in the cores of bright stars, and is quite likely to be set for the cores of saturated stars.";
    public static String maybeEghostDesc = "Object appears in the right place to be an electronics ghost.";
    public static String notcheckedCenterDesc = "Center of object lies in a NOTCHECKED region. The object is almost certainly bogus.";
    public static String hasSaturDnDesc = "This object is saturated in this band and the bleed trail doesn’t touch the edge of the frame, we we’ve made an attempt to add up all the flux in the bleed trails, and to include it in the object’s photometry.";
    public static String deblendPeepholeDesc = "The deblend was modified by the optimizer.";
    private final int[] flags2;
    private int deblendedAsMoving	= 1 << 0;
    private int nodeblendMoving	= 1 << 1;
    private int tooFewDetections	= 1 << 2;
    private int badMovingFit	= 1 << 3;
    private int stationary	= 1 << 4;
    private int peaksTooClose	= 1 << 5;
    private int binnedCenter	= 1 << 6;
    private int localEdge	= 1 << 7;
    private int badCountsError	= 1 << 8;
    private int badMovingFitChild	= 1 << 9;
    private int deblendUnassignedFlux	= 1 << 10;
    private int saturCenter	= 1 << 11;
    private int interpCenter	= 1 << 12;
    private int deblendedAtEdge	= 1 << 13;
    private int deblendNopeak	= 1 << 14;
    private int psfFluxInterp	= 1 << 15;
    private int tooFewGoodDetections	= 1 << 16;
    private int centerOffAimage	= 1 << 17;
    private int deblendDegenerate	= 1 << 18;
    private int brightestGalaxyChild	= 1 << 19;
    private int canonicalBand	= 1 << 20;
    private int amomentUnweighted	= 1 << 21;
    private int amomentShift	= 1 << 22;
    private int amomentMaxiter	= 1 << 23;
    private int maybeCr	= 1 << 24;
    private int maybeEghost	= 1 << 25;
    private int notcheckedCenter	= 1 << 26;
    private int hasSaturDn	= 1 << 27;
    private int deblendPeephole	= 1 << 28;
    public Flags2(int[] flags2){
        this.flags2 = flags2;
    }

    public Flags2(int flags2){
        this.flags2 = new int[1];
        this.flags2[0] = flags2;
    }

    public boolean isDeblendedAsMoving() {
        return (flags2[0] & deblendedAsMoving) != 0; 
    }

    public boolean isNodeblendMoving() {
        return (flags2[0] & nodeblendMoving) != 0; 
    }

    public boolean isTooFewDetections() {
        return (flags2[0] & tooFewDetections) != 0; 
    }

    public boolean isBadMovingFit() {
        return (flags2[0] & badMovingFit) != 0; 
    }

    public boolean isStationary() {
        return (flags2[0] & stationary) != 0; 
    }

    public boolean isPeaksTooClose() {
        return (flags2[0] & peaksTooClose) != 0; 
    }

    public boolean isBinnedCenter() {
        return (flags2[0] & binnedCenter) != 0; 
    }

    public boolean isLocalEdge() {
        return (flags2[0] & localEdge) != 0; 
    }

    public boolean isBadCountsError() {
        return (flags2[0] & badCountsError) != 0; 
    }

    public boolean isBadMovingFitChild() {
        return (flags2[0] & badMovingFitChild) != 0; 
    }

    public boolean isDeblendUnassignedFlux() {
        return (flags2[0] & deblendUnassignedFlux) != 0; 
    }

    public boolean isSaturCenter() {
        return (flags2[0] & saturCenter) != 0; 
    }

    public boolean isInterpCenter() {
        return (flags2[0] & interpCenter) != 0; 
    }

    public boolean isDeblendedAtEdge() {
        return (flags2[0] & deblendedAtEdge) != 0; 
    }

    public boolean isDeblendNopeak() {
        return (flags2[0] & deblendNopeak) != 0; 
    }

    public boolean isPsfFluxInterp() {
        return (flags2[0] & psfFluxInterp) != 0; 
    }

    public boolean isTooFewGoodDetections() {
        return (flags2[0] & tooFewGoodDetections) != 0; 
    }

    public boolean isCenterOffAimage() {
        return (flags2[0] & centerOffAimage) != 0; 
    }

    public boolean isDeblendDegenerate() {
        return (flags2[0] & deblendDegenerate) != 0; 
    }

    public boolean isBrightestGalaxyChild() {
        return (flags2[0] & brightestGalaxyChild) != 0; 
    }

    public boolean isCanonicalBand() {
        return (flags2[0] & canonicalBand) != 0; 
    }

    public boolean isAmomentUnweighted() {
        return (flags2[0] & amomentUnweighted) != 0; 
    }

    public boolean isAmomentShift() {
        return (flags2[0] & amomentShift) != 0; 
    }

    public boolean isAmomentMaxiter() {
        return (flags2[0] & amomentMaxiter) != 0; 
    }

    public boolean isMaybeCr() {
        return (flags2[0] & maybeCr) != 0; 
    }

    public boolean isMaybeEghost() {
        return (flags2[0] & maybeEghost) != 0; 
    }

    public boolean isNotcheckedCenter() {
        return (flags2[0] & notcheckedCenter) != 0; 
    }

    public boolean isHasSaturDn() {
        return (flags2[0] & hasSaturDn) != 0; 
    }

    public boolean isDeblendPeephole() {
        return (flags2[0] & deblendPeephole) != 0; 
    }


    public boolean isDeblendedAsMoving_u() {
        return (flags2[Filter.u.index()] & deblendedAsMoving) != 0;
    }

    public boolean isNodeblendMoving_u() {
        return (flags2[Filter.u.index()] & nodeblendMoving) != 0;
    }

    public boolean isTooFewDetections_u() {
        return (flags2[Filter.u.index()] & tooFewDetections) != 0;
    }

    public boolean isBadMovingFit_u() {
        return (flags2[Filter.u.index()] & badMovingFit) != 0;
    }

    public boolean isStationary_u() {
        return (flags2[Filter.u.index()] & stationary) != 0;
    }

    public boolean isPeaksTooClose_u() {
        return (flags2[Filter.u.index()] & peaksTooClose) != 0;
    }

    public boolean isBinnedCenter_u() {
        return (flags2[Filter.u.index()] & binnedCenter) != 0;
    }

    public boolean isLocalEdge_u() {
        return (flags2[Filter.u.index()] & localEdge) != 0;
    }

    public boolean isBadCountsError_u() {
        return (flags2[Filter.u.index()] & badCountsError) != 0;
    }

    public boolean isBadMovingFitChild_u() {
        return (flags2[Filter.u.index()] & badMovingFitChild) != 0;
    }

    public boolean isDeblendUnassignedFlux_u() {
        return (flags2[Filter.u.index()] & deblendUnassignedFlux) != 0;
    }

    public boolean isSaturCenter_u() {
        return (flags2[Filter.u.index()] & saturCenter) != 0;
    }

    public boolean isInterpCenter_u() {
        return (flags2[Filter.u.index()] & interpCenter) != 0;
    }

    public boolean isDeblendedAtEdge_u() {
        return (flags2[Filter.u.index()] & deblendedAtEdge) != 0;
    }

    public boolean isDeblendNopeak_u() {
        return (flags2[Filter.u.index()] & deblendNopeak) != 0;
    }

    public boolean isPsfFluxInterp_u() {
        return (flags2[Filter.u.index()] & psfFluxInterp) != 0;
    }

    public boolean isTooFewGoodDetections_u() {
        return (flags2[Filter.u.index()] & tooFewGoodDetections) != 0;
    }

    public boolean isCenterOffAimage_u() {
        return (flags2[Filter.u.index()] & centerOffAimage) != 0;
    }

    public boolean isDeblendDegenerate_u() {
        return (flags2[Filter.u.index()] & deblendDegenerate) != 0;
    }

    public boolean isBrightestGalaxyChild_u() {
        return (flags2[Filter.u.index()] & brightestGalaxyChild) != 0;
    }

    public boolean isCanonicalBand_u() {
        return (flags2[Filter.u.index()] & canonicalBand) != 0;
    }

    public boolean isAmomentUnweighted_u() {
        return (flags2[Filter.u.index()] & amomentUnweighted) != 0;
    }

    public boolean isAmomentShift_u() {
        return (flags2[Filter.u.index()] & amomentShift) != 0;
    }

    public boolean isAmomentMaxiter_u() {
        return (flags2[Filter.u.index()] & amomentMaxiter) != 0;
    }

    public boolean isMaybeCr_u() {
        return (flags2[Filter.u.index()] & maybeCr) != 0;
    }

    public boolean isMaybeEghost_u() {
        return (flags2[Filter.u.index()] & maybeEghost) != 0;
    }

    public boolean isNotcheckedCenter_u() {
        return (flags2[Filter.u.index()] & notcheckedCenter) != 0;
    }

    public boolean isHasSaturDn_u() {
        return (flags2[Filter.u.index()] & hasSaturDn) != 0;
    }

    public boolean isDeblendPeephole_u() {
        return (flags2[Filter.u.index()] & deblendPeephole) != 0;
    }

    public boolean isDeblendedAsMoving_g() {
        return (flags2[Filter.g.index()] & deblendedAsMoving) != 0;
    }

    public boolean isNodeblendMoving_g() {
        return (flags2[Filter.g.index()] & nodeblendMoving) != 0;
    }

    public boolean isTooFewDetections_g() {
        return (flags2[Filter.g.index()] & tooFewDetections) != 0;
    }

    public boolean isBadMovingFit_g() {
        return (flags2[Filter.g.index()] & badMovingFit) != 0;
    }

    public boolean isStationary_g() {
        return (flags2[Filter.g.index()] & stationary) != 0;
    }

    public boolean isPeaksTooClose_g() {
        return (flags2[Filter.g.index()] & peaksTooClose) != 0;
    }

    public boolean isBinnedCenter_g() {
        return (flags2[Filter.g.index()] & binnedCenter) != 0;
    }

    public boolean isLocalEdge_g() {
        return (flags2[Filter.g.index()] & localEdge) != 0;
    }

    public boolean isBadCountsError_g() {
        return (flags2[Filter.g.index()] & badCountsError) != 0;
    }

    public boolean isBadMovingFitChild_g() {
        return (flags2[Filter.g.index()] & badMovingFitChild) != 0;
    }

    public boolean isDeblendUnassignedFlux_g() {
        return (flags2[Filter.g.index()] & deblendUnassignedFlux) != 0;
    }

    public boolean isSaturCenter_g() {
        return (flags2[Filter.g.index()] & saturCenter) != 0;
    }

    public boolean isInterpCenter_g() {
        return (flags2[Filter.g.index()] & interpCenter) != 0;
    }

    public boolean isDeblendedAtEdge_g() {
        return (flags2[Filter.g.index()] & deblendedAtEdge) != 0;
    }

    public boolean isDeblendNopeak_g() {
        return (flags2[Filter.g.index()] & deblendNopeak) != 0;
    }

    public boolean isPsfFluxInterp_g() {
        return (flags2[Filter.g.index()] & psfFluxInterp) != 0;
    }

    public boolean isTooFewGoodDetections_g() {
        return (flags2[Filter.g.index()] & tooFewGoodDetections) != 0;
    }

    public boolean isCenterOffAimage_g() {
        return (flags2[Filter.g.index()] & centerOffAimage) != 0;
    }

    public boolean isDeblendDegenerate_g() {
        return (flags2[Filter.g.index()] & deblendDegenerate) != 0;
    }

    public boolean isBrightestGalaxyChild_g() {
        return (flags2[Filter.g.index()] & brightestGalaxyChild) != 0;
    }

    public boolean isCanonicalBand_g() {
        return (flags2[Filter.g.index()] & canonicalBand) != 0;
    }

    public boolean isAmomentUnweighted_g() {
        return (flags2[Filter.g.index()] & amomentUnweighted) != 0;
    }

    public boolean isAmomentShift_g() {
        return (flags2[Filter.g.index()] & amomentShift) != 0;
    }

    public boolean isAmomentMaxiter_g() {
        return (flags2[Filter.g.index()] & amomentMaxiter) != 0;
    }

    public boolean isMaybeCr_g() {
        return (flags2[Filter.g.index()] & maybeCr) != 0;
    }

    public boolean isMaybeEghost_g() {
        return (flags2[Filter.g.index()] & maybeEghost) != 0;
    }

    public boolean isNotcheckedCenter_g() {
        return (flags2[Filter.g.index()] & notcheckedCenter) != 0;
    }

    public boolean isHasSaturDn_g() {
        return (flags2[Filter.g.index()] & hasSaturDn) != 0;
    }

    public boolean isDeblendPeephole_g() {
        return (flags2[Filter.g.index()] & deblendPeephole) != 0;
    }
    public boolean isDeblendedAsMoving_r() {
        return (flags2[Filter.r.index()] & deblendedAsMoving) != 0;
    }

    public boolean isNodeblendMoving_r() {
        return (flags2[Filter.r.index()] & nodeblendMoving) != 0;
    }

    public boolean isTooFewDetections_r() {
        return (flags2[Filter.r.index()] & tooFewDetections) != 0;
    }

    public boolean isBadMovingFit_r() {
        return (flags2[Filter.r.index()] & badMovingFit) != 0;
    }

    public boolean isStationary_r() {
        return (flags2[Filter.r.index()] & stationary) != 0;
    }

    public boolean isPeaksTooClose_r() {
        return (flags2[Filter.r.index()] & peaksTooClose) != 0;
    }

    public boolean isBinnedCenter_r() {
        return (flags2[Filter.r.index()] & binnedCenter) != 0;
    }

    public boolean isLocalEdge_r() {
        return (flags2[Filter.r.index()] & localEdge) != 0;
    }

    public boolean isBadCountsError_r() {
        return (flags2[Filter.r.index()] & badCountsError) != 0;
    }

    public boolean isBadMovingFitChild_r() {
        return (flags2[Filter.r.index()] & badMovingFitChild) != 0;
    }

    public boolean isDeblendUnassignedFlux_r() {
        return (flags2[Filter.r.index()] & deblendUnassignedFlux) != 0;
    }

    public boolean isSaturCenter_r() {
        return (flags2[Filter.r.index()] & saturCenter) != 0;
    }

    public boolean isInterpCenter_r() {
        return (flags2[Filter.r.index()] & interpCenter) != 0;
    }

    public boolean isDeblendedAtEdge_r() {
        return (flags2[Filter.r.index()] & deblendedAtEdge) != 0;
    }

    public boolean isDeblendNopeak_r() {
        return (flags2[Filter.r.index()] & deblendNopeak) != 0;
    }

    public boolean isPsfFluxInterp_r() {
        return (flags2[Filter.r.index()] & psfFluxInterp) != 0;
    }

    public boolean isTooFewGoodDetections_r() {
        return (flags2[Filter.r.index()] & tooFewGoodDetections) != 0;
    }

    public boolean isCenterOffAimage_r() {
        return (flags2[Filter.r.index()] & centerOffAimage) != 0;
    }

    public boolean isDeblendDegenerate_r() {
        return (flags2[Filter.r.index()] & deblendDegenerate) != 0;
    }

    public boolean isBrightestGalaxyChild_r() {
        return (flags2[Filter.r.index()] & brightestGalaxyChild) != 0;
    }

    public boolean isCanonicalBand_r() {
        return (flags2[Filter.r.index()] & canonicalBand) != 0;
    }

    public boolean isAmomentUnweighted_r() {
        return (flags2[Filter.r.index()] & amomentUnweighted) != 0;
    }

    public boolean isAmomentShift_r() {
        return (flags2[Filter.r.index()] & amomentShift) != 0;
    }

    public boolean isAmomentMaxiter_r() {
        return (flags2[Filter.r.index()] & amomentMaxiter) != 0;
    }

    public boolean isMaybeCr_r() {
        return (flags2[Filter.r.index()] & maybeCr) != 0;
    }

    public boolean isMaybeEghost_r() {
        return (flags2[Filter.r.index()] & maybeEghost) != 0;
    }

    public boolean isNotcheckedCenter_r() {
        return (flags2[Filter.r.index()] & notcheckedCenter) != 0;
    }

    public boolean isHasSaturDn_r() {
        return (flags2[Filter.r.index()] & hasSaturDn) != 0;
    }

    public boolean isDeblendPeephole_r() {
        return (flags2[Filter.r.index()] & deblendPeephole) != 0;
    }
    public boolean isDeblendedAsMoving_i() {
        return (flags2[Filter.i.index()] & deblendedAsMoving) != 0;
    }

    public boolean isNodeblendMoving_i() {
        return (flags2[Filter.i.index()] & nodeblendMoving) != 0;
    }

    public boolean isTooFewDetections_i() {
        return (flags2[Filter.i.index()] & tooFewDetections) != 0;
    }

    public boolean isBadMovingFit_i() {
        return (flags2[Filter.i.index()] & badMovingFit) != 0;
    }

    public boolean isStationary_i() {
        return (flags2[Filter.i.index()] & stationary) != 0;
    }

    public boolean isPeaksTooClose_i() {
        return (flags2[Filter.i.index()] & peaksTooClose) != 0;
    }

    public boolean isBinnedCenter_i() {
        return (flags2[Filter.i.index()] & binnedCenter) != 0;
    }

    public boolean isLocalEdge_i() {
        return (flags2[Filter.i.index()] & localEdge) != 0;
    }

    public boolean isBadCountsError_i() {
        return (flags2[Filter.i.index()] & badCountsError) != 0;
    }

    public boolean isBadMovingFitChild_i() {
        return (flags2[Filter.i.index()] & badMovingFitChild) != 0;
    }

    public boolean isDeblendUnassignedFlux_i() {
        return (flags2[Filter.i.index()] & deblendUnassignedFlux) != 0;
    }

    public boolean isSaturCenter_i() {
        return (flags2[Filter.i.index()] & saturCenter) != 0;
    }

    public boolean isInterpCenter_i() {
        return (flags2[Filter.i.index()] & interpCenter) != 0;
    }

    public boolean isDeblendedAtEdge_i() {
        return (flags2[Filter.i.index()] & deblendedAtEdge) != 0;
    }

    public boolean isDeblendNopeak_i() {
        return (flags2[Filter.i.index()] & deblendNopeak) != 0;
    }

    public boolean isPsfFluxInterp_i() {
        return (flags2[Filter.i.index()] & psfFluxInterp) != 0;
    }

    public boolean isTooFewGoodDetections_i() {
        return (flags2[Filter.i.index()] & tooFewGoodDetections) != 0;
    }

    public boolean isCenterOffAimage_i() {
        return (flags2[Filter.i.index()] & centerOffAimage) != 0;
    }

    public boolean isDeblendDegenerate_i() {
        return (flags2[Filter.i.index()] & deblendDegenerate) != 0;
    }

    public boolean isBrightestGalaxyChild_i() {
        return (flags2[Filter.i.index()] & brightestGalaxyChild) != 0;
    }

    public boolean isCanonicalBand_i() {
        return (flags2[Filter.i.index()] & canonicalBand) != 0;
    }

    public boolean isAmomentUnweighted_i() {
        return (flags2[Filter.i.index()] & amomentUnweighted) != 0;
    }

    public boolean isAmomentShift_i() {
        return (flags2[Filter.i.index()] & amomentShift) != 0;
    }

    public boolean isAmomentMaxiter_i() {
        return (flags2[Filter.i.index()] & amomentMaxiter) != 0;
    }

    public boolean isMaybeCr_i() {
        return (flags2[Filter.i.index()] & maybeCr) != 0;
    }

    public boolean isMaybeEghost_i() {
        return (flags2[Filter.i.index()] & maybeEghost) != 0;
    }

    public boolean isNotcheckedCenter_i() {
        return (flags2[Filter.i.index()] & notcheckedCenter) != 0;
    }

    public boolean isHasSaturDn_i() {
        return (flags2[Filter.i.index()] & hasSaturDn) != 0;
    }

    public boolean isDeblendPeephole_i() {
        return (flags2[Filter.i.index()] & deblendPeephole) != 0;
    }
    public boolean isDeblendedAsMoving_z() {
        return (flags2[Filter.z.index()] & deblendedAsMoving) != 0;
    }

    public boolean isNodeblendMoving_z() {
        return (flags2[Filter.z.index()] & nodeblendMoving) != 0;
    }

    public boolean isTooFewDetections_z() {
        return (flags2[Filter.z.index()] & tooFewDetections) != 0;
    }

    public boolean isBadMovingFit_z() {
        return (flags2[Filter.z.index()] & badMovingFit) != 0;
    }

    public boolean isStationary_z() {
        return (flags2[Filter.z.index()] & stationary) != 0;
    }

    public boolean isPeaksTooClose_z() {
        return (flags2[Filter.z.index()] & peaksTooClose) != 0;
    }

    public boolean isBinnedCenter_z() {
        return (flags2[Filter.z.index()] & binnedCenter) != 0;
    }

    public boolean isLocalEdge_z() {
        return (flags2[Filter.z.index()] & localEdge) != 0;
    }

    public boolean isBadCountsError_z() {
        return (flags2[Filter.z.index()] & badCountsError) != 0;
    }

    public boolean isBadMovingFitChild_z() {
        return (flags2[Filter.z.index()] & badMovingFitChild) != 0;
    }

    public boolean isDeblendUnassignedFlux_z() {
        return (flags2[Filter.z.index()] & deblendUnassignedFlux) != 0;
    }

    public boolean isSaturCenter_z() {
        return (flags2[Filter.z.index()] & saturCenter) != 0;
    }

    public boolean isInterpCenter_z() {
        return (flags2[Filter.z.index()] & interpCenter) != 0;
    }

    public boolean isDeblendedAtEdge_z() {
        return (flags2[Filter.z.index()] & deblendedAtEdge) != 0;
    }

    public boolean isDeblendNopeak_z() {
        return (flags2[Filter.z.index()] & deblendNopeak) != 0;
    }

    public boolean isPsfFluxInterp_z() {
        return (flags2[Filter.z.index()] & psfFluxInterp) != 0;
    }

    public boolean isTooFewGoodDetections_z() {
        return (flags2[Filter.z.index()] & tooFewGoodDetections) != 0;
    }

    public boolean isCenterOffAimage_z() {
        return (flags2[Filter.z.index()] & centerOffAimage) != 0;
    }

    public boolean isDeblendDegenerate_z() {
        return (flags2[Filter.z.index()] & deblendDegenerate) != 0;
    }

    public boolean isBrightestGalaxyChild_z() {
        return (flags2[Filter.z.index()] & brightestGalaxyChild) != 0;
    }

    public boolean isCanonicalBand_z() {
        return (flags2[Filter.z.index()] & canonicalBand) != 0;
    }

    public boolean isAmomentUnweighted_z() {
        return (flags2[Filter.z.index()] & amomentUnweighted) != 0;
    }

    public boolean isAmomentShift_z() {
        return (flags2[Filter.z.index()] & amomentShift) != 0;
    }

    public boolean isAmomentMaxiter_z() {
        return (flags2[Filter.z.index()] & amomentMaxiter) != 0;
    }

    public boolean isMaybeCr_z() {
        return (flags2[Filter.z.index()] & maybeCr) != 0;
    }

    public boolean isMaybeEghost_z() {
        return (flags2[Filter.z.index()] & maybeEghost) != 0;
    }

    public boolean isNotcheckedCenter_z() {
        return (flags2[Filter.z.index()] & notcheckedCenter) != 0;
    }

    public boolean isHasSaturDn_z() {
        return (flags2[Filter.z.index()] & hasSaturDn) != 0;
    }

    public boolean isDeblendPeephole_z() {
        return (flags2[Filter.z.index()] & deblendPeephole) != 0;
    }
}
