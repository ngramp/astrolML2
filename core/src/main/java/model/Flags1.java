package model;

/**
 * Created by Graham Perry on 05/09/16.
 *
 * @author Graham Perry
 */
public class Flags1 {
    public static String canonicalCenterDesc = "The quantities (psf counts, model fits and likelihoods) that are usually determined at an object’s center as determined band-by-band were in fact determined at the canonical center (suitably transformed). This is due to the object being to close to the edge to extract a profile at the local center, and OBJECT1_EDGE is also set.";
    public static String brightDesc = "Indicates that the object was detected as a bright object. Since these are typically remeasured as faint objects, most users can ignore BRIGHT objects.";
    public static String edgeDesc = "Object is too close to edge of frame in this band.";
    public static String blendedDesc = "Object was determined to be a blend. The flag is set if: more than one peak is detected within an object in a single band together; distinct peaks are found when merging different colors of one object together; or distinct peaks result when merging different objects together.";
    public static String childDesc = "Object is a child, created by the deblender.";
    public static String peakcenterDesc = "Given center is position of peak pixel, as attempts to determine a better centroid failed.";
    public static String nodeblendDesc = "Although this object was marked as a blend, no deblending was attempted.";
    public static String noprofileDesc = "Frames couldn’t extract a radial profile.";
    public static String nopetroDesc = "No Petrosian radius or other Petrosian quantities could be measured.";
    public static String manypetroDesc = "Object has more than one possible Petrosian radius.";
    public static String nopetroBigDesc = "The Petrosian ratio has not fallen to the value at which the Petrosian radius is defined at the outermost point of the extracted radial profile. NOPETRO is set, and the Petrosian radius is set to the outermost point in the profile.";
    public static String deblendTooManyPeaksDesc = "The object had the OBJECT1_DEBLEND flag set, but it contained too many candidate children to be fully deblended. This flag is only set in the parent, i.e. the object with too many peaks.";
    public static String crDesc = "Object contains at least one pixel which was contaminated by a cosmic ray. The OBJECT1_INTERP flag is also set. This flag does not mean that this object is a cosmic ray; rather it means that a cosmic ray has been removed.";
    public static String manyr50Desc = "More than one radius was found to contain 50% of the Petrosian flux. (For this to happen part of the radial profile must be negative).";
    public static String manyr90Desc = "More than one radius was found to contain 90% of the Petrosian flux. (For this to happen part of the radial profile must be negative).";
    public static String badRadialDesc = "Measured profile includes points with a S/N <= 0. In practice this flag is essentially meaningless.";
    public static String incompleteProfileDesc = "A circle, centered on the object, of radius the canonical Petrosian radius extends beyond the edge of the frame. The radial profile is still measured from those parts of the object that do lie on the frame.";
    public static String interpDesc = "The object contains interpolated pixels (e.g. cosmic rays or bad columns).";
    public static String saturDesc = "The object contains saturated pixels; INTERP is also set.";
    public static String notcheckedDesc = "Object includes pixels that were not checked for peaks, for example the unsmoothed edges of frames, and the cores of subtracted or saturated stars.";
    public static String subtractedDesc = "Object (presumably a star) had wings subtracted.";
    public static String nostokesDesc = "Object has no measured Stokes parameters.";
    public static String badskyDesc = "The estimated sky level is so bad that the central value of the radial profile is crazily negative; this is usually the result of the subtraction of the wings of bright stars failing.";
    public static String petrofaintDesc = "At least one candidate Petrosian radius occurred at an unacceptably low surface brightness.";
    public static String tooLargeDesc = "The object is (as it says) too large. Either the object is still detectable at the outermost point of the extracted radial profile (a radius of approximately 260 arcsec), or when attempting to deblend an object, at least one child is larger than half a frame (in either row or column).";
    public static String deblendedAsPsfDesc = "When deblending an object, in this band this child was treated as a PSF.";
    public static String deblendPrunedDesc = "When solving for the weights to be assigned to each child the deblender encountered a nearly singular matrix, and therefore deleted at least one of them.";
    public static String ellipfaintDesc = "No isophotal fits were performed.";
    public static String binned1Desc = "The object was detected in an unbinned image.";
    public static String binned2Desc = "The object was detected in a 2×2 binned image after all unbinned detections have been replaced by the background level.";
    public static String binned4Desc = "The object was detected in a 4×4 binned image. The objects detected in the 2×2 binned image are not removed before doing this.";
    public static String movedDesc = "The object appears to have moved during the exposure. Such objects are candidates to be deblended as moving objects.";
    private final int[] flags1;
    private int canonicalCenter = 1 << 0;
    private int bright = 1 << 1;
    private int edge = 1 << 2;
    private int blended = 1 << 3;
    private int child = 1 << 4;
    private int peakcenter = 1 << 5;
    private int nodeblend = 1 << 6;
    private int noprofile = 1 << 7;
    private int nopetro = 1 << 8;
    private int manypetro = 1 << 9;
    private int nopetroBig = 1 << 10;
    private int deblendTooManyPeaks = 1 << 11;
    private int cr = 1 << 12;
    private int manyr50 = 1 << 13;
    private int manyr90 = 1 << 14;
    private int badRadial = 1 << 15;
    private int incompleteProfile = 1 << 16;
    private int interp = 1 << 17;
    private int satur = 1 << 18;
    private int notchecked = 1 << 19;
    private int subtracted = 1 << 20;
    private int nostokes = 1 << 21;
    private int badsky = 1 << 22;
    private int petrofaint = 1 << 23;
    private int tooLarge = 1 << 24;
    private int deblendedAsPsf = 1 << 25;
    private int deblendPruned = 1 << 26;
    private int ellipfaint = 1 << 27;
    private int binned1 = 1 << 28;
    private int binned2 = 1 << 29;
    private int binned4 = 1 << 30;
    private int moved = 1 << 31;

    public Flags1(int[] flags1) {
        this.flags1 = flags1;
    }

    public Flags1(int flags1) {
        this.flags1 = new int[1];
        this.flags1[0] = flags1;
    }

    public boolean isCanonicalCenter_u() {
        return (flags1[Filter.u.index()] & canonicalCenter) != 0;
    }

    public boolean isBright_u() {
        return (flags1[Filter.u.index()] & bright) != 0;
    }

    public boolean isEdge_u() {
        return (flags1[Filter.u.index()] & edge) != 0;
    }

    public boolean isBlended_u() {
        return (flags1[Filter.u.index()] & blended) != 0;
    }

    public boolean isChild_u() {
        return (flags1[Filter.u.index()] & child) != 0;
    }

    public boolean isPeakcenter_u() {
        return (flags1[Filter.u.index()] & peakcenter) != 0;
    }

    public boolean isNodeblend_u() {
        return (flags1[Filter.u.index()] & nodeblend) != 0;
    }

    public boolean isNoprofile_u() {
        return (flags1[Filter.u.index()] & noprofile) != 0;
    }

    public boolean isNopetro_u() {
        return (flags1[Filter.u.index()] & nopetro) != 0;
    }

    public boolean isManypetro_u() {
        return (flags1[Filter.u.index()] & manypetro) != 0;
    }

    public boolean isNopetroBig_u() {
        return (flags1[Filter.u.index()] & nopetroBig) != 0;
    }

    public boolean isDeblendTooManyPeaks_u() {
        return (flags1[Filter.u.index()] & deblendTooManyPeaks) != 0;
    }

    public boolean isCr_u() {
        return (flags1[Filter.u.index()] & cr) != 0;
    }

    public boolean isManyr50_u() {
        return (flags1[Filter.u.index()] & manyr50) != 0;
    }

    public boolean isManyr90_u() {
        return (flags1[Filter.u.index()] & manyr90) != 0;
    }

    public boolean isBadRadial_u() {
        return (flags1[Filter.u.index()] & badRadial) != 0;
    }

    public boolean isIncompleteProfile_u() {
        return (flags1[Filter.u.index()] & incompleteProfile) != 0;
    }

    public boolean isInterp_u() {
        return (flags1[Filter.u.index()] & interp) != 0;
    }

    public boolean isSatur_u() {
        return (flags1[Filter.u.index()] & satur) != 0;
    }

    public boolean isNotchecked_u() {
        return (flags1[Filter.u.index()] & notchecked) != 0;
    }

    public boolean isSubtracted_u() {
        return (flags1[Filter.u.index()] & subtracted) != 0;
    }

    public boolean isNostokes_u() {
        return (flags1[Filter.u.index()] & nostokes) != 0;
    }

    public boolean isBadsky_u() {
        return (flags1[Filter.u.index()] & badsky) != 0;
    }

    public boolean isPetrofaint_u() {
        return (flags1[Filter.u.index()] & petrofaint) != 0;
    }

    public boolean isTooLarge_u() {
        return (flags1[Filter.u.index()] & tooLarge) != 0;
    }

    public boolean isDeblendedAsPsf_u() {
        return (flags1[Filter.u.index()] & deblendedAsPsf) != 0;
    }

    public boolean isDeblendPruned_u() {
        return (flags1[Filter.u.index()] & deblendPruned) != 0;
    }

    public boolean isEllipfaint_u() {
        return (flags1[Filter.u.index()] & ellipfaint) != 0;
    }

    public boolean isBinned1_u() {
        return (flags1[Filter.u.index()] & binned1) != 0;
    }

    public boolean isBinned2_u() {
        return (flags1[Filter.u.index()] & binned2) != 0;
    }

    public boolean isBinned4_u() {
        return (flags1[Filter.u.index()] & binned4) != 0;
    }

    public boolean isMoved_u() {
        return (flags1[Filter.u.index()] & moved) != 0;
    }

    public boolean isCanonicalCenter_g() {
        return (flags1[Filter.g.index()] & canonicalCenter) != 0;
    }

    public boolean isBright_g() {
        return (flags1[Filter.g.index()] & bright) != 0;
    }

    public boolean isEdge_g() {
        return (flags1[Filter.g.index()] & edge) != 0;
    }

    public boolean isBlended_g() {
        return (flags1[Filter.g.index()] & blended) != 0;
    }

    public boolean isChild_g() {
        return (flags1[Filter.g.index()] & child) != 0;
    }

    public boolean isPeakcenter_g() {
        return (flags1[Filter.g.index()] & peakcenter) != 0;
    }

    public boolean isNodeblend_g() {
        return (flags1[Filter.g.index()] & nodeblend) != 0;
    }

    public boolean isNoprofile_g() {
        return (flags1[Filter.g.index()] & noprofile) != 0;
    }

    public boolean isNopetro_g() {
        return (flags1[Filter.g.index()] & nopetro) != 0;
    }

    public boolean isManypetro_g() {
        return (flags1[Filter.g.index()] & manypetro) != 0;
    }

    public boolean isNopetroBig_g() {
        return (flags1[Filter.g.index()] & nopetroBig) != 0;
    }

    public boolean isDeblendTooManyPeaks_g() {
        return (flags1[Filter.g.index()] & deblendTooManyPeaks) != 0;
    }

    public boolean isCr_g() {
        return (flags1[Filter.g.index()] & cr) != 0;
    }

    public boolean isManyr50_g() {
        return (flags1[Filter.g.index()] & manyr50) != 0;
    }

    public boolean isManyr90_g() {
        return (flags1[Filter.g.index()] & manyr90) != 0;
    }

    public boolean isBadRadial_g() {
        return (flags1[Filter.g.index()] & badRadial) != 0;
    }

    public boolean isIncompleteProfile_g() {
        return (flags1[Filter.g.index()] & incompleteProfile) != 0;
    }

    public boolean isInterp_g() {
        return (flags1[Filter.g.index()] & interp) != 0;
    }

    public boolean isSatur_g() {
        return (flags1[Filter.g.index()] & satur) != 0;
    }

    public boolean isNotchecked_g() {
        return (flags1[Filter.g.index()] & notchecked) != 0;
    }

    public boolean isSubtracted_g() {
        return (flags1[Filter.g.index()] & subtracted) != 0;
    }

    public boolean isNostokes_g() {
        return (flags1[Filter.g.index()] & nostokes) != 0;
    }

    public boolean isBadsky_g() {
        return (flags1[Filter.g.index()] & badsky) != 0;
    }

    public boolean isPetrofaint_g() {
        return (flags1[Filter.g.index()] & petrofaint) != 0;
    }

    public boolean isTooLarge_g() {
        return (flags1[Filter.g.index()] & tooLarge) != 0;
    }

    public boolean isDeblendedAsPsf_g() {
        return (flags1[Filter.g.index()] & deblendedAsPsf) != 0;
    }

    public boolean isDeblendPruned_g() {
        return (flags1[Filter.g.index()] & deblendPruned) != 0;
    }

    public boolean isEllipfaint_g() {
        return (flags1[Filter.g.index()] & ellipfaint) != 0;
    }

    public boolean isBinned1_g() {
        return (flags1[Filter.g.index()] & binned1) != 0;
    }

    public boolean isBinned2_g() {
        return (flags1[Filter.g.index()] & binned2) != 0;
    }

    public boolean isBinned4_g() {
        return (flags1[Filter.g.index()] & binned4) != 0;
    }

    public boolean isMoved_g() {
        return (flags1[Filter.g.index()] & moved) != 0;
    }

    public boolean isCanonicalCenter_r() {
        return (flags1[Filter.r.index()] & canonicalCenter) != 0;
    }

    public boolean isBright_r() {
        return (flags1[Filter.r.index()] & bright) != 0;
    }

    public boolean isEdge_r() {
        return (flags1[Filter.r.index()] & edge) != 0;
    }

    public boolean isBlended_r() {
        return (flags1[Filter.r.index()] & blended) != 0;
    }

    public boolean isChild_r() {
        return (flags1[Filter.r.index()] & child) != 0;
    }

    public boolean isPeakcenter_r() {
        return (flags1[Filter.r.index()] & peakcenter) != 0;
    }

    public boolean isNodeblend_r() {
        return (flags1[Filter.r.index()] & nodeblend) != 0;
    }

    public boolean isNoprofile_r() {
        return (flags1[Filter.r.index()] & noprofile) != 0;
    }

    public boolean isNopetro_r() {
        return (flags1[Filter.r.index()] & nopetro) != 0;
    }

    public boolean isManypetro_r() {
        return (flags1[Filter.r.index()] & manypetro) != 0;
    }

    public boolean isNopetroBig_r() {
        return (flags1[Filter.r.index()] & nopetroBig) != 0;
    }

    public boolean isDeblendTooManyPeaks_r() {
        return (flags1[Filter.r.index()] & deblendTooManyPeaks) != 0;
    }

    public boolean isCr_r() {
        return (flags1[Filter.r.index()] & cr) != 0;
    }

    public boolean isManyr50_r() {
        return (flags1[Filter.r.index()] & manyr50) != 0;
    }

    public boolean isManyr90_r() {
        return (flags1[Filter.r.index()] & manyr90) != 0;
    }

    public boolean isBadRadial_r() {
        return (flags1[Filter.r.index()] & badRadial) != 0;
    }

    public boolean isIncompleteProfile_r() {
        return (flags1[Filter.r.index()] & incompleteProfile) != 0;
    }

    public boolean isInterp_r() {
        return (flags1[Filter.r.index()] & interp) != 0;
    }

    public boolean isSatur_r() {
        return (flags1[Filter.r.index()] & satur) != 0;
    }

    public boolean isNotchecked_r() {
        return (flags1[Filter.r.index()] & notchecked) != 0;
    }

    public boolean isSubtracted_r() {
        return (flags1[Filter.r.index()] & subtracted) != 0;
    }

    public boolean isNostokes_r() {
        return (flags1[Filter.r.index()] & nostokes) != 0;
    }

    public boolean isBadsky_r() {
        return (flags1[Filter.r.index()] & badsky) != 0;
    }

    public boolean isPetrofaint_r() {
        return (flags1[Filter.r.index()] & petrofaint) != 0;
    }

    public boolean isTooLarge_r() {
        return (flags1[Filter.r.index()] & tooLarge) != 0;
    }

    public boolean isDeblendedAsPsf_r() {
        return (flags1[Filter.r.index()] & deblendedAsPsf) != 0;
    }

    public boolean isDeblendPruned_r() {
        return (flags1[Filter.r.index()] & deblendPruned) != 0;
    }

    public boolean isEllipfaint_r() {
        return (flags1[Filter.r.index()] & ellipfaint) != 0;
    }

    public boolean isBinned1_r() {
        return (flags1[Filter.r.index()] & binned1) != 0;
    }

    public boolean isBinned2_r() {
        return (flags1[Filter.r.index()] & binned2) != 0;
    }

    public boolean isBinned4_r() {
        return (flags1[Filter.r.index()] & binned4) != 0;
    }

    public boolean isMoved_r() {
        return (flags1[Filter.r.index()] & moved) != 0;
    }

    public boolean isCanonicalCenter_i() {
        return (flags1[Filter.i.index()] & canonicalCenter) != 0;
    }

    public boolean isBright_i() {
        return (flags1[Filter.i.index()] & bright) != 0;
    }

    public boolean isEdge_i() {
        return (flags1[Filter.i.index()] & edge) != 0;
    }

    public boolean isBlended_i() {
        return (flags1[Filter.i.index()] & blended) != 0;
    }

    public boolean isChild_i() {
        return (flags1[Filter.i.index()] & child) != 0;
    }

    public boolean isPeakcenter_i() {
        return (flags1[Filter.i.index()] & peakcenter) != 0;
    }

    public boolean isNodeblend_i() {
        return (flags1[Filter.i.index()] & nodeblend) != 0;
    }

    public boolean isNoprofile_i() {
        return (flags1[Filter.i.index()] & noprofile) != 0;
    }

    public boolean isNopetro_i() {
        return (flags1[Filter.i.index()] & nopetro) != 0;
    }

    public boolean isManypetro_i() {
        return (flags1[Filter.i.index()] & manypetro) != 0;
    }

    public boolean isNopetroBig_i() {
        return (flags1[Filter.i.index()] & nopetroBig) != 0;
    }

    public boolean isDeblendTooManyPeaks_i() {
        return (flags1[Filter.i.index()] & deblendTooManyPeaks) != 0;
    }

    public boolean isCr_i() {
        return (flags1[Filter.i.index()] & cr) != 0;
    }

    public boolean isManyr50_i() {
        return (flags1[Filter.i.index()] & manyr50) != 0;
    }

    public boolean isManyr90_i() {
        return (flags1[Filter.i.index()] & manyr90) != 0;
    }

    public boolean isBadRadial_i() {
        return (flags1[Filter.i.index()] & badRadial) != 0;
    }

    public boolean isIncompleteProfile_i() {
        return (flags1[Filter.i.index()] & incompleteProfile) != 0;
    }

    public boolean isInterp_i() {
        return (flags1[Filter.i.index()] & interp) != 0;
    }

    public boolean isSatur_i() {
        return (flags1[Filter.i.index()] & satur) != 0;
    }

    public boolean isNotchecked_i() {
        return (flags1[Filter.i.index()] & notchecked) != 0;
    }

    public boolean isSubtracted_i() {
        return (flags1[Filter.i.index()] & subtracted) != 0;
    }

    public boolean isNostokes_i() {
        return (flags1[Filter.i.index()] & nostokes) != 0;
    }

    public boolean isBadsky_i() {
        return (flags1[Filter.i.index()] & badsky) != 0;
    }

    public boolean isPetrofaint_i() {
        return (flags1[Filter.i.index()] & petrofaint) != 0;
    }

    public boolean isTooLarge_i() {
        return (flags1[Filter.i.index()] & tooLarge) != 0;
    }

    public boolean isDeblendedAsPsf_i() {
        return (flags1[Filter.i.index()] & deblendedAsPsf) != 0;
    }

    public boolean isDeblendPruned_i() {
        return (flags1[Filter.i.index()] & deblendPruned) != 0;
    }

    public boolean isEllipfaint_i() {
        return (flags1[Filter.i.index()] & ellipfaint) != 0;
    }

    public boolean isBinned1_i() {
        return (flags1[Filter.i.index()] & binned1) != 0;
    }

    public boolean isBinned2_i() {
        return (flags1[Filter.i.index()] & binned2) != 0;
    }

    public boolean isBinned4_i() {
        return (flags1[Filter.i.index()] & binned4) != 0;
    }

    public boolean isMoved_i() {
        return (flags1[Filter.i.index()] & moved) != 0;
    }

    public boolean isCanonicalCenter_z() {
        return (flags1[Filter.z.index()] & canonicalCenter) != 0;
    }

    public boolean isBright_z() {
        return (flags1[Filter.z.index()] & bright) != 0;
    }

    public boolean isEdge_z() {
        return (flags1[Filter.z.index()] & edge) != 0;
    }

    public boolean isBlended_z() {
        return (flags1[Filter.z.index()] & blended) != 0;
    }

    public boolean isChild_z() {
        return (flags1[Filter.z.index()] & child) != 0;
    }

    public boolean isPeakcenter_z() {
        return (flags1[Filter.z.index()] & peakcenter) != 0;
    }

    public boolean isNodeblend_z() {
        return (flags1[Filter.z.index()] & nodeblend) != 0;
    }

    public boolean isNoprofile_z() {
        return (flags1[Filter.z.index()] & noprofile) != 0;
    }

    public boolean isNopetro_z() {
        return (flags1[Filter.z.index()] & nopetro) != 0;
    }

    public boolean isManypetro_z() {
        return (flags1[Filter.z.index()] & manypetro) != 0;
    }

    public boolean isNopetroBig_z() {
        return (flags1[Filter.z.index()] & nopetroBig) != 0;
    }

    public boolean isDeblendTooManyPeaks_z() {
        return (flags1[Filter.z.index()] & deblendTooManyPeaks) != 0;
    }

    public boolean isCr_z() {
        return (flags1[Filter.z.index()] & cr) != 0;
    }

    public boolean isManyr50_z() {
        return (flags1[Filter.z.index()] & manyr50) != 0;
    }

    public boolean isManyr90_z() {
        return (flags1[Filter.z.index()] & manyr90) != 0;
    }

    public boolean isBadRadial_z() {
        return (flags1[Filter.z.index()] & badRadial) != 0;
    }

    public boolean isIncompleteProfile_z() {
        return (flags1[Filter.z.index()] & incompleteProfile) != 0;
    }

    public boolean isInterp_z() {
        return (flags1[Filter.z.index()] & interp) != 0;
    }

    public boolean isSatur_z() {
        return (flags1[Filter.z.index()] & satur) != 0;
    }

    public boolean isNotchecked_z() {
        return (flags1[Filter.z.index()] & notchecked) != 0;
    }

    public boolean isSubtracted_z() {
        return (flags1[Filter.z.index()] & subtracted) != 0;
    }

    public boolean isNostokes_z() {
        return (flags1[Filter.z.index()] & nostokes) != 0;
    }

    public boolean isBadsky_z() {
        return (flags1[Filter.z.index()] & badsky) != 0;
    }

    public boolean isPetrofaint_z() {
        return (flags1[Filter.z.index()] & petrofaint) != 0;
    }

    public boolean isTooLarge_z() {
        return (flags1[Filter.z.index()] & tooLarge) != 0;
    }

    public boolean isDeblendedAsPsf_z() {
        return (flags1[Filter.z.index()] & deblendedAsPsf) != 0;
    }

    public boolean isDeblendPruned_z() {
        return (flags1[Filter.z.index()] & deblendPruned) != 0;
    }

    public boolean isEllipfaint_z() {
        return (flags1[Filter.z.index()] & ellipfaint) != 0;
    }

    public boolean isBinned1_z() {
        return (flags1[Filter.z.index()] & binned1) != 0;
    }

    public boolean isBinned2_z() {
        return (flags1[Filter.z.index()] & binned2) != 0;
    }

    public boolean isBinned4_z() {
        return (flags1[Filter.z.index()] & binned4) != 0;
    }

    public boolean isMoved_z() {
        return (flags1[Filter.z.index()] & moved) != 0;
    }

    public boolean isCanonicalCenter() {
        return (flags1[0] & canonicalCenter) != 0;
    }

    public boolean isBright() {
        return (flags1[0] & bright) != 0;
    }

    public boolean isEdge() {
        return (flags1[0] & edge) != 0;
    }

    public boolean isBlended() {
        return (flags1[0] & blended) != 0;
    }

    public boolean isChild() {
        return (flags1[0] & child) != 0;
    }

    public boolean isPeakcenter() {
        return (flags1[0] & peakcenter) != 0;
    }

    public boolean isNodeblend() {
        return (flags1[0] & nodeblend) != 0;
    }

    public boolean isNoprofile() {
        return (flags1[0] & noprofile) != 0;
    }

    public boolean isNopetro() {
        return (flags1[0] & nopetro) != 0;
    }

    public boolean isManypetro() {
        return (flags1[0] & manypetro) != 0;
    }

    public boolean isNopetroBig() {
        return (flags1[0] & nopetroBig) != 0;
    }

    public boolean isDeblendTooManyPeaks() {
        return (flags1[0] & deblendTooManyPeaks) != 0;
    }

    public boolean isCr() {
        return (flags1[0] & cr) != 0;
    }

    public boolean isManyr50() {
        return (flags1[0] & manyr50) != 0;
    }

    public boolean isManyr90() {
        return (flags1[0] & manyr90) != 0;
    }

    public boolean isBadRadial() {
        return (flags1[0] & badRadial) != 0;
    }

    public boolean isIncompleteProfile() {
        return (flags1[0] & incompleteProfile) != 0;
    }

    public boolean isInterp() {
        return (flags1[0] & interp) != 0;
    }

    public boolean isSatur() {
        return (flags1[0] & satur) != 0;
    }

    public boolean isNotchecked() {
        return (flags1[0] & notchecked) != 0;
    }

    public boolean isSubtracted() {
        return (flags1[0] & subtracted) != 0;
    }

    public boolean isNostokes() {
        return (flags1[0] & nostokes) != 0;
    }

    public boolean isBadsky() {
        return (flags1[0] & badsky) != 0;
    }

    public boolean isPetrofaint() {
        return (flags1[0] & petrofaint) != 0;
    }

    public boolean isTooLarge() {
        return (flags1[0] & tooLarge) != 0;
    }

    public boolean isDeblendedAsPsf() {
        return (flags1[0] & deblendedAsPsf) != 0;
    }

    public boolean isDeblendPruned() {
        return (flags1[0] & deblendPruned) != 0;
    }

    public boolean isEllipfaint() {
        return (flags1[0] & ellipfaint) != 0;
    }

    public boolean isBinned1() {
        return (flags1[0] & binned1) != 0;
    }

    public boolean isBinned2() {
        return (flags1[0] & binned2) != 0;
    }

    public boolean isBinned4() {
        return (flags1[0] & binned4) != 0;
    }

    public boolean isMoved() {
        return (flags1[0] & moved) != 0;
    }
}
