package model;

/**
 * Created by Graham Perry on 11/08/16.
 *
 * @author Graham Perry
 */
public class Sample implements CosObject{
    private String name;
    private double ra;
    private double dec;
    private float scale;

    public Sample(String name, double ra, double dec, float scale) {
        this.name = name;
        this.ra = ra;
        this.dec = dec;
        this.scale = scale;

    }

    @Override
    public String getObjid() {
        return name;
    }

    @Override
    public double getRa() {
        return ra;
    }

    @Override
    public double getDec() {
        return dec;
    }

    @Override
    public float getSize() {
        return scale;
    }

    @Override
    public ObjCType getObjType() {
        return null;
    }

    @Override
    public CalibStatus getCalibStatus() {
        return null;
    }

    @Override
    public ResolveStatus getResolveStatus() {
        return null;
    }

    @Override
    public Flags1 getFlags1() {
        return null;
    }

    @Override
    public Flags2 getFlags2() {
        return null;
    }
}