package calibobj;
// Generated 17-Jun-2016 10:09:16 by Hibernate Tools 4.3.1.Final


import model.ObjCType;
import util.FitsUtil;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Datasweepindex generated by hbm2java
 */
@Entity
@Table(name = "PhotoSweepCamcol", schema = "public")
public class PhotoSweepCamcol {
    @Id
    @Column(name = "run", nullable = false)
    private int run;
    @Id
    @Column(name = "rerun", nullable = false)
    private String rerun;
    @Id
    @Column(name = "camcol", nullable = false)
    private int camcol;
    @Id
    @Column(name = "field", nullable = false)
    private int field;
    @Id
    @Column(name = "objcType", nullable = false)
    private int objcType;

    private double ra;
    private double dec;
    private int istart;
    private int iend;
    private int nprimary;

    public PhotoSweepCamcol() {
    }

    public PhotoSweepCamcol(int run, String rerun, int camcol, int field, int objcType, double ra, double dec, int istart, int iend, int nprimary) {
        this.run = run;
        this.rerun = rerun;
        this.camcol = camcol;
        this.field = field;
        this.objcType = objcType;
        this.ra = ra;
        this.dec = dec;
        this.istart = istart;
        this.iend = iend;
        this.nprimary = nprimary;
    }

    private PhotoSweepCamcol(Object[] row) {
        FitsUtil f = new FitsUtil();
        this.run = f.toInt(row[0]);
        this.rerun = f.toString(row[1]);
        this.camcol = f.toInt(row[2]);
        this.field = f.toInt(row[3]);
        this.objcType = ObjCType.UNK.code(); //!!
        this.ra = f.toDouble(row[4]);//ra
        this.dec = f.toDouble(row[5]); //dec
        this.istart = f.toInt(row[6]);//istart
        this.iend = f.toInt(row[7]);//iend
        this.nprimary = f.toInt(row[8]);//nprimary
    }


    public PhotoSweepCamcol(Object[] row, int objcType) {
        FitsUtil f = new FitsUtil();
        this.run = f.toInt(row[0]);
        this.rerun = f.toString(row[1]);
        this.camcol = f.toInt(row[2]);
        this.field = f.toInt(row[3]);
        this.objcType = objcType;
        this.ra = f.toDouble(row[4]);//ra
        this.dec = f.toDouble(row[5]); //dec
        this.istart = f.toInt(row[6]);//istart
        this.iend = f.toInt(row[7]);//iend
        this.nprimary = f.toInt(row[8]);//nprimary
    }

    public int getRun() {
        return run;
    }

    public void setRun(int run) {
        this.run = run;
    }

    public String getRerun() {
        return rerun;
    }

    public void setRerun(String rerun) {
        this.rerun = rerun;
    }

    public int getCamcol() {
        return camcol;
    }

    public void setCamcol(int camcol) {
        this.camcol = camcol;
    }

    public int getField() {
        return field;
    }

    public void setField(int field) {
        this.field = field;
    }

    public double getRa() {
        return this.ra;
    }

    public void setRa(double ra) {
        this.ra = ra;
    }


    public double getDec() {
        return this.dec;
    }

    public void setDec(double dec) {
        this.dec = dec;
    }


    public int getIstart() {
        return this.istart;
    }

    public void setIstart(int istart) {
        this.istart = istart;
    }


    public int getIend() {
        return this.iend;
    }

    public void setIend(int iend) {
        this.iend = iend;
    }


    public int getNprimary() {
        return this.nprimary;
    }

    public void setNprimary(int nprimary) {
        this.nprimary = nprimary;
    }

    public int getObjcType() {
        return objcType;
    }

    public void setObjcType(int objcType) {
        this.objcType = objcType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PhotoSweepCamcol that = (PhotoSweepCamcol) o;

        if (getRun() != that.getRun()) return false;
        if (getCamcol() != that.getCamcol()) return false;
        if (getField() != that.getField()) return false;
        if (getObjcType() != that.getObjcType()) return false;
        return getRerun().equals(that.getRerun());

    }

    public int hashCode() {
        int result = 17;

        result = 37 * result + this.getRun();
        result = 37 * result + (getRerun() == null ? 0 : this.getRerun().hashCode());
        result = 37 * result + this.getCamcol();
        result = 37 * result + this.getField();
        result = 37 * result + this.getObjcType();
        return result;
    }
}
