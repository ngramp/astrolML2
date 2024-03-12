package model;

/**
 * Created by Graham Perry on 05/09/16.
 *
 * @author Graham Perry
 */
public class ResolveStatus {
    public static String runPrimaryDesc = "primary within the objects own run (but not necessarily for the survey as a whole)";
    public static String runRampDesc = "in what would be the overlap area of a field, but with no neighboring field";
    public static String runOverlaponlyDesc = "only appears in the overlap between two fields";
    public static String runIgnoreDesc = "bright or parent object that should be ignored";
    public static String runEdgeDesc = "near lowest or highest column";
    public static String runDuplicateDesc = "duplicate measurement of same pixels in two different fields";
    public static String surveyPrimaryDesc = "Primary observation within the full survey, where it appears in the primary observation of this part of the sky";
    public static String surveyBestDesc = "Best observation within the full survey, but it does not appear in the primary observation of this part of the sky";
    public static String surveySecondaryDesc = "Repeat (independent) observation of an object that has a different primary or best observation";
    public static String surveyBadfieldDesc = "In field with score=0";
    public static String surveyEdgeDesc = "Not kept as secondary because it is RUN_RAMP or RUN_EDGE object";
    private final int runPrimary	= 1 << 0;
    private final int runRamp	= 1 << 1;
    private final int runOverlaponly	= 1 << 2;
    private final int runIgnore	= 1 << 3;
    private final int runEdge	= 1 << 4;
    private final int runDuplicate	= 1 << 5;
    private final int surveyPrimary	= 1 << 8;
    private final int surveyBest	= 1 << 9;
    private final int surveySecondary	= 1 << 10;
    private final int surveyBadfield	= 1 << 11;
    private final int surveyEdge	= 1 << 12;
    private final int resolveStatus;
    public ResolveStatus(int resolveStatus){
        this.resolveStatus = resolveStatus;
    }

    public boolean isRunPrimary() {
        return (resolveStatus & runPrimary) != 0;
    }

    public boolean isRunRamp() {
        return (resolveStatus & runRamp) != 0;
    }

    public boolean isRunOverlaponly() {
        return (resolveStatus & runOverlaponly) != 0;
    }

    public boolean isRunIgnore() {
        return (resolveStatus & runIgnore) != 0;
    }

    public boolean isRunEdge() {
        return (resolveStatus & runEdge) != 0;
    }

    public boolean isRunDuplicate() {
        return (resolveStatus & runDuplicate) != 0;
    }

    public boolean isSurveyPrimary() {
        return (resolveStatus & surveyPrimary) != 0;
    }

    public boolean isSurveyBest() {
        return (resolveStatus & surveyBest) != 0;
    }

    public boolean isSurveySecondary() {
        return (resolveStatus & surveySecondary) != 0;
    }

    public boolean isSurveyBadfield() {
        return (resolveStatus & surveyBadfield) != 0;
    }

    public boolean isSurveyEdge() {
        return (resolveStatus & surveyEdge) != 0;
    }
}
