package model;

/**
 * Created by Graham Perry on 05/09/16.
 *
 * @author Graham Perry
 */
public enum Filter {
    u (0),
    g (1),
    r (2),
    i (4),
    z (5);
    private final int index;   // in kilograms
    Filter(int index) {
        this.index = index;
    }
    public int index() { return index; }
}
