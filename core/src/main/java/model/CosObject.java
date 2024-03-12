package model;

/**
 * Created by Graham Perry on 13/07/16.
 *
 * @author Graham Perry
 */
public interface CosObject {
    String getObjid();
    double getRa();
    double getDec();
    float getSize();
    ObjCType getObjType();
    CalibStatus getCalibStatus();
    ResolveStatus getResolveStatus();
    Flags1 getFlags1();
    Flags2 getFlags2();
}
