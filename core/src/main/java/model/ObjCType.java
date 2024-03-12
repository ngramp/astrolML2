package model;

/**
 * Object types within the SDSS data
 * Created by Graham Perry on 15/07/16.
 *
 * @author Graham Perry
 */
public enum ObjCType {
    UNK(0, "Unknown object"),
    CR(1, "Cosmic Ray"),
    DEFECT(2, "Defect"),
    GALAXY(3, "Galaxy"),
    GHOST(4, "Ghost"),
    KNOWNOBJ(5, "Known object"),
    STAR(6, "Star"),
    TRAIL(7, "Trail"),
    SKY(8, "Sky");

    private final int code;
    private final String text;

    ObjCType(int code, String text) {
        this.code = code;
        this.text = text;
    }

    public int code() {
        return code;
    }

    public String text() {
        return text;
    }
}
