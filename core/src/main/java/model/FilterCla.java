package model;

/**
 * Created by Graham Perry on 05/09/16.
 *
 * @author Graham Perry
 */
public class FilterCla {
    private final int filterBit;
    private String helpText;
    FilterCla(int filterBit, String helpText){
        this.filterBit = filterBit;
    }

    boolean isR(){
        return filterBit != 0;
    }
}
