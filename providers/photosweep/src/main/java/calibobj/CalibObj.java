package calibobj;


import model.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by Graham Perry on 19/06/16.
 *
 * @author Graham Perry
 */
@Entity
@Table(name = "CalibObj", schema = "public")
public abstract class CalibObj implements Serializable, CosObject {
    @Id
    @Column(name = "run", nullable = false)
    short run;
    @Id
    @Column(name = "rerun", nullable = false)
    String rerun;
    @Id
    @Column(name = "camcol", nullable = false)
    short camcol;
    @Id
    @Column(name = "field", nullable = false)
    short field;
    @Id
    @Column(name = "id", nullable = false)
    short id;

    double ra;
    double dec;
    int objcType;
    int objcFlags;
    int objcFlags2;
    float objcRowc;
    float[] rowc;
    float[] colc;
    int[] flags1;
    int[] flags2;
    int[] pspStatus;
    float[] psfFwhm;
    float[] extinction;
    int[] calibStatus;
    float[] nmgypercount;
    int resolveStatus;
    int thingId;
    int ifield;
    int balkanId;
    int ndetect;

    public abstract float getSize();

    public short getId() {
        return id;
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


    public int getObjcFlags() {
        return this.objcFlags;
    }


    public int getObjcFlags2() {
        return this.objcFlags2;
    }


    public float getObjcRowc() {
        return this.objcRowc;
    }



    public float[] getRowc() {
        return this.rowc;
    }


    public float[] getColc() {
        return this.colc;
    }




    public int[] getPspStatus() {
        return this.pspStatus;
    }


    public double getRa() {
        return this.ra;
    }


    public double getDec() {
        return this.dec;
    }


    public float[] getPsfFwhm() {
        return this.psfFwhm;
    }


    public float[] getExtinction() {
        return this.extinction;
    }



    public float[] getNmgypercount() {
        return this.nmgypercount;
    }


    public int getThingId() {
        return this.thingId;
    }


    public int getIfield() {
        return this.ifield;
    }

    public int getBalkanId() {
        return this.balkanId;
    }

    public int getNdetect() {
        return this.ndetect;
    }



    @Override
    public ObjCType getObjType() {
        ObjCType ret = ObjCType.UNK;
        for (ObjCType t : ObjCType.values()){
            if(t.code() == objcType){
                ret = t;
            }
        }
        return ret;
    }

    @Override
    public CalibStatus getCalibStatus() {
        return new CalibStatus(calibStatus);
    }

    @Override
    public ResolveStatus getResolveStatus() {
        return new ResolveStatus(resolveStatus);
    }

    @Override
    public Flags1 getFlags1() {
        return new Flags1(flags1);
    }

    @Override
    public Flags2 getFlags2() {
        return new Flags2(flags2);
    }
    public boolean equals(Object other) {
        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof CalibObj))
            return false;
        CalibObj castOther = (CalibObj) other;

        return (this.getRun() == castOther.getRun())
                && ((this.getRerun() == castOther.getRerun()) || (this.getRerun() != null
                && castOther.getRerun() != null && this.getRerun().equals(castOther.getRerun())))
                && (this.getCamcol() == castOther.getCamcol()) && (this.getField() == castOther.getField())
                && (this.getId() == castOther.getId());
    }

    public int hashCode() {
        int result = 17;

        result = 37 * result + this.getRun();
        result = 37 * result + (getRerun() == null ? 0 : this.getRerun().hashCode());
        result = 37 * result + this.getCamcol();
        result = 37 * result + this.getField();
        result = 37 * result + this.getId();
        return result;
    }
}
