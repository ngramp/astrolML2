package model;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.prefs.Preferences;

/**
 * Created by Graham Perry on 05/09/16.
 *
 * @author Graham Perry
 */
public class Filters {
    private Preferences prefs;
    private Map<String,Boolean> resolveFilters;
    private Map<String,Boolean> flags1Filters;
    private Map<String,Boolean> flags2Filters;
    private Map<String,Boolean> objTypeFilters;
    private Map<String,Boolean> calibFilters;

    private void getProperties(Class clazz, Map<String,Boolean> map){
        for (Field field : clazz.getDeclaredFields()) {
            if((field.getType() != String.class) && !(field.getName().toLowerCase().equals(clazz.getSimpleName().toLowerCase()))){
                Boolean pref = getPref(field.getName());
                map.put(field.getName(),pref);
            }

        }
    }
    public Filters(Preferences prefs){
        this.prefs = prefs;
        resolveFilters = new HashMap<>();
        getProperties(ResolveStatus.class,resolveFilters);
        flags1Filters = new HashMap<>();
        getProperties(Flags1.class,flags1Filters);
        flags2Filters = new HashMap<>();
        getProperties(Flags2.class,flags2Filters);
        calibFilters = new HashMap<>();
        getProperties(CalibStatus.class,calibFilters);
        objTypeFilters = new HashMap<>();
        for(ObjCType type: ObjCType.values()){
            objTypeFilters.put(type.text(),getPref(type.text()));
        }

    }
    private Boolean getPref(String filter){
        if (prefs.getBoolean(filter + "undef",true)){
            return null;
        }
        else{
            return prefs.getBoolean(filter,true);
        }
    }

    public Map<String,Boolean> getResolveFilters(){
        return resolveFilters;
    }

    public Map<String, Boolean> getFlags1Filters() {
        return flags1Filters;
    }

    public Map<String, Boolean> getFlags2Filters() {
        return flags2Filters;
    }

    public Map<String, Boolean> getObjTypeFilters() {
        return objTypeFilters;
    }

    public Map<String, Boolean> getCalibFilters() {
        return calibFilters;
    }

    /**
     * If rs matches the resolve filters then it is allowed.
     * @param rs
     * @return
     */
    public boolean matchResolve(ResolveStatus rs) {
        Class clazz = ResolveStatus.class;
        return match(resolveFilters,clazz,rs);
    }

    /**
     * if rs matches the flags1 settings then it is allowed
     * @param rs
     * @return
     */
    public boolean matchFlags1(Flags1 rs) {
        Class clazz = Flags1.class;
        return match(flags1Filters,clazz,rs);
    }

    /**
     * If rs matches the flags2 settings then it is allow
     * @param rs
     * @return
     */
    public boolean matchFlags2(Flags2 rs) {
        Class clazz = Flags2.class;
        return match(flags2Filters,clazz,rs);
    }

    /**
     * If rs matches the Calib settings then it is allows
     * @param rs
     * @return
     */
    public boolean matchCalib(CalibStatus rs) {
        Class clazz = CalibStatus.class;
        return match(calibFilters,clazz,rs);
    }
    private boolean match(Map<String,Boolean> filters, Class clazz, Object ob){
        boolean match = true;
        try {
            for (String b : filters.keySet()) {
                String cap = b.substring(0, 1).toUpperCase() + b.substring(1);
                if (filters.get(b) != null) {
                    if (filters.get(b)) { //filter is set to allow
                        if (!(boolean) clazz.getDeclaredMethod("is" + cap).invoke(ob)) {
                            match = false;
                            break;
                        }
                    } else { //filter is set to disallow
                        if ((boolean) clazz.getDeclaredMethod("is" + cap).invoke(ob)) {
                            match = false;
                            break;
                        }
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return match;
    }

    /**
     * If objType matches the selected filter options, then it is allowed
     * @param objType
     * @return
     */
    public boolean matchObjType(ObjCType objType) {
        boolean match = true;
        for (String b : objTypeFilters.keySet()) {
            if (objTypeFilters.get(b) != null) {
                if (!objTypeFilters.get(b)) { //filter is set to disallow
                    if(objType.text().equals(b)){
                        match = false;
                        break;
                    }
                }
            }
        }
        return match;
    }
}
