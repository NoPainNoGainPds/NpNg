package R3;

import model.Emplacement;


public class MarkedLocation implements Comparable{
    /**
     * The location linked to to marked location
     */
    private Emplacement location;
    /**
     * Mark of the location
     */
    private int mark;

    /**
     * Constructor
     * @param mark location's mark
     * @param location the location
     */
    public MarkedLocation(int mark, Emplacement location)
    {
        this.mark = mark;
        this.location = location;
    }

    /**
     * Get the mark of the location
     * @return the mark of the location
     */
    public int getMark() {
        return mark;
    }

    /**
     * Set the mark of the location
     * @param mark the new mark
     */
    public void setMark(int mark) {
        this.mark = mark;
    }
    /**
     * Get the location linked to the marked location
     * @return the location
     */
    public Emplacement getLocation() {
        return location;
    }

    /**
     * Set the location linked to the marked location
     * @param location the new location
     */
    public void setLocation(Emplacement location) {
        this.location = location;
    }

    /**
     * Method to compare two locations by their mark
     * @param o the location to compare to this
     * @return
     */
    @Override
    public int compareTo(Object o) {
        MarkedLocation ml = (MarkedLocation) o;
        return ((Integer) this.mark).compareTo((Integer) ml.getMark());
    }
}
