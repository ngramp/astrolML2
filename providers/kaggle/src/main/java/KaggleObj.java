import model.*;

/**
 * Created by Graham Perry on 19/08/16_
 *
 * The first column in each solution is labeled GalaxyID;
 * this is a randomly-generated ID that only allows you to
 * match the probability distributions with the images.
 * The next 37 columns are all floating point numbers
 * between 0 and 1 inclusive. These represent the morphology
 * (or shape) of the galaxy in 37 different categories as
 * identified by crowdsourced volunteer classifications as
 * part of the Galaxy Zoo 2 project. These morphologies are
 * related to probabilities for each category; a high number
 * (close to 1) indicates that many users identified this
 * morphology category for the galaxy with a high level of
 * confidence. Low numbers for a category (close to 0)
 * indicate the feature is likely not present.
 *
 * @author Graham Perry
 */
public class KaggleObj implements CosObject{
    private int GalaxyID;
    private float Class1_1;
    private float Class1_2;
    private float Class1_3;
    private float Class2_1;
    private float Class2_2;
    private float Class3_1;
    private float Class3_2;
    private float Class4_1;
    private float Class4_2;
    private float Class5_1;
    private float Class5_2;
    private float Class5_3;
    private float Class5_4;
    private float Class6_1;
    private float Class6_2;
    private float Class7_1;
    private float Class7_2;
    private float Class7_3;
    private float Class8_1;
    private float Class8_2;
    private float Class8_3;
    private float Class8_4;
    private float Class8_5;
    private float Class8_6;
    private float Class8_7;
    private float Class9_1;
    private float Class9_2;
    private float Class9_3;
    private float Class10_1;
    private float Class10_2;
    private float Class10_3;
    private float Class11_1;
    private float Class11_2;
    private float Class11_3;
    private float Class11_4;
    private float Class11_5;
    private float Class11_6;

    public KaggleObj(int galaxyID, float class1_1, float class1_2, float class1_3, float class2_1, float class2_2, float class3_1, float class3_2, float class4_1, float class4_2, float class5_1, float class5_2, float class5_3, float class5_4, float class6_1, float class6_2, float class7_1, float class7_2, float class7_3, float class8_1, float class8_2, float class8_3, float class8_4, float class8_5, float class8_6, float class8_7, float class9_1, float class9_2, float class9_3, float class10_1, float class10_2, float class10_3, float class11_1, float class11_2, float class11_3, float class11_4, float class11_5, float class11_6) {
        GalaxyID = galaxyID;
        Class1_1 = class1_1;
        Class1_2 = class1_2;
        Class1_3 = class1_3;
        Class2_1 = class2_1;
        Class2_2 = class2_2;
        Class3_1 = class3_1;
        Class3_2 = class3_2;
        Class4_1 = class4_1;
        Class4_2 = class4_2;
        Class5_1 = class5_1;
        Class5_2 = class5_2;
        Class5_3 = class5_3;
        Class5_4 = class5_4;
        Class6_1 = class6_1;
        Class6_2 = class6_2;
        Class7_1 = class7_1;
        Class7_2 = class7_2;
        Class7_3 = class7_3;
        Class8_1 = class8_1;
        Class8_2 = class8_2;
        Class8_3 = class8_3;
        Class8_4 = class8_4;
        Class8_5 = class8_5;
        Class8_6 = class8_6;
        Class8_7 = class8_7;
        Class9_1 = class9_1;
        Class9_2 = class9_2;
        Class9_3 = class9_3;
        Class10_1 = class10_1;
        Class10_2 = class10_2;
        Class10_3 = class10_3;
        Class11_1 = class11_1;
        Class11_2 = class11_2;
        Class11_3 = class11_3;
        Class11_4 = class11_4;
        Class11_5 = class11_5;
        Class11_6 = class11_6;
    }
    public KaggleObj(String[] galaxy){
        int n = 0;
        GalaxyID = Integer.parseInt(galaxy[n++]);
        Class1_1 = Float.parseFloat(galaxy[n++]);
        Class1_2 = Float.parseFloat(galaxy[n++]);
        Class1_3 = Float.parseFloat(galaxy[n++]);
        Class2_1 = Float.parseFloat(galaxy[n++]);
        Class2_2 = Float.parseFloat(galaxy[n++]);
        Class3_1 = Float.parseFloat(galaxy[n++]);
        Class3_2 = Float.parseFloat(galaxy[n++]);
        Class4_1 = Float.parseFloat(galaxy[n++]);
        Class4_2 = Float.parseFloat(galaxy[n++]);
        Class5_1 = Float.parseFloat(galaxy[n++]);
        Class5_2 = Float.parseFloat(galaxy[n++]);
        Class5_3 = Float.parseFloat(galaxy[n++]);
        Class5_4 = Float.parseFloat(galaxy[n++]);
        Class6_1 = Float.parseFloat(galaxy[n++]);
        Class6_2 = Float.parseFloat(galaxy[n++]);
        Class7_1 = Float.parseFloat(galaxy[n++]);
        Class7_2 = Float.parseFloat(galaxy[n++]);
        Class7_3 = Float.parseFloat(galaxy[n++]);
        Class8_1 = Float.parseFloat(galaxy[n++]);
        Class8_2 = Float.parseFloat(galaxy[n++]);
        Class8_3 = Float.parseFloat(galaxy[n++]);
        Class8_4 = Float.parseFloat(galaxy[n++]);
        Class8_5 = Float.parseFloat(galaxy[n++]);
        Class8_6 = Float.parseFloat(galaxy[n++]);
        Class8_7 = Float.parseFloat(galaxy[n++]);
        Class9_1 = Float.parseFloat(galaxy[n++]);
        Class9_2 = Float.parseFloat(galaxy[n++]);
        Class9_3 = Float.parseFloat(galaxy[n++]);
        Class10_1 = Float.parseFloat(galaxy[n++]);
        Class10_2 = Float.parseFloat(galaxy[n++]);
        Class10_3 = Float.parseFloat(galaxy[n++]);
        Class11_1 = Float.parseFloat(galaxy[n++]);
        Class11_2 = Float.parseFloat(galaxy[n++]);
        Class11_3 = Float.parseFloat(galaxy[n++]);
        Class11_4 = Float.parseFloat(galaxy[n++]);
        Class11_5 = Float.parseFloat(galaxy[n++]);
        Class11_6 = Float.parseFloat(galaxy[n++]);
    }

    public int getGalaxyID() {
        return GalaxyID;
    }

    public float getClass1_1() {
        return Class1_1;
    }

    public float getClass1_2() {
        return Class1_2;
    }

    public float getClass1_3() {
        return Class1_3;
    }

    public float getClass2_1() {
        return Class2_1;
    }

    public float getClass2_2() {
        return Class2_2;
    }

    public float getClass3_1() {
        return Class3_1;
    }

    public float getClass3_2() {
        return Class3_2;
    }

    public float getClass4_1() {
        return Class4_1;
    }

    public float getClass4_2() {
        return Class4_2;
    }

    public float getClass5_1() {
        return Class5_1;
    }

    public float getClass5_2() {
        return Class5_2;
    }

    public float getClass5_3() {
        return Class5_3;
    }

    public float getClass5_4() {
        return Class5_4;
    }

    public float getClass6_1() {
        return Class6_1;
    }

    public float getClass6_2() {
        return Class6_2;
    }

    public float getClass7_1() {
        return Class7_1;
    }

    public float getClass7_2() {
        return Class7_2;
    }

    public float getClass7_3() {
        return Class7_3;
    }

    public float getClass8_1() {
        return Class8_1;
    }

    public float getClass8_2() {
        return Class8_2;
    }

    public float getClass8_3() {
        return Class8_3;
    }

    public float getClass8_4() {
        return Class8_4;
    }

    public float getClass8_5() {
        return Class8_5;
    }

    public float getClass8_6() {
        return Class8_6;
    }

    public float getClass8_7() {
        return Class8_7;
    }

    public float getClass9_1() {
        return Class9_1;
    }

    public float getClass9_2() {
        return Class9_2;
    }

    public float getClass9_3() {
        return Class9_3;
    }

    public float getClass10_1() {
        return Class10_1;
    }

    public float getClass10_2() {
        return Class10_2;
    }

    public float getClass10_3() {
        return Class10_3;
    }

    public float getClass11_1() {
        return Class11_1;
    }

    public float getClass11_2() {
        return Class11_2;
    }

    public float getClass11_3() {
        return Class11_3;
    }

    public float getClass11_4() {
        return Class11_4;
    }

    public float getClass11_5() {
        return Class11_5;
    }

    public float getClass11_6() {
        return Class11_6;
    }

    @Override
    public String getObjid() {
        return Integer.toString(getGalaxyID());
    }

    @Override
    public double getRa() {
        return 0;
    }

    @Override
    public double getDec() {
        return 0;
    }

    @Override
    public float getSize() {
        return 0;
    }

    @Override
    public ObjCType getObjType() {
        return ObjCType.GALAXY;
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
