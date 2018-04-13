package R3;

import model.Boutique;
import model.Emplacement;

import java.awt.*;
import java.util.ArrayList;

public class MarkedLocation implements Comparable{
    /**
     * Location id
     */
    private int id;
    /**
     * Location name
     */
    private String name;
    /**
     * The store in the location
     */
    private Boutique reference;
    /**
     * Area of the location
     */
    private int area;
    /**
     * Location category
     */
    private String category;
    /**
     * Path of the location
     */
    private ArrayList<Point> path;
    /**
     * The location
     */
    private Emplacement location;
    /**
     * Mark of the location
     */
    private int mark;

    /**
     * Constructor
     */
    public MarkedLocation()
    {
        this.name = "fictif";
        this.id = -1;
    }

    /**
     * Get the location
     * @return the location
     */
    public Emplacement getLocation() {
        return location;
    }

    /**
     * Set the location
     * @param location the new location
     */
    public void setLocation(Emplacement location) {
        this.location = location;
    }

    /**
     * Constructor
     * @param name location's name
     * @param id location's id
     * @param area location's area
     * @param category location's category
     * @param mark location's mark
     * @param location the location
     */
    public MarkedLocation(String name, int id, int area, String category, int mark, Emplacement location)
    {
        this.name = name;
        this.id = id;
        this.category = category;
        this.area = area;

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
     * get the location category
     * @return the category
     */
    public String getCat()
    {
        return this.category;
    }

    /**
     * get location id
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Set location id
     * @param id the new id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * get location name
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Set location name
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the store
     * @return the store
     */
    public Boutique getReference() {
        return reference;
    }

    /**
     * get the location area
     * @return the area
     */
    public int getArea()
    {
        return  this.area;
    }

    /**
     * Set location area
     * @param area the new area
     */
    public void setSuperficie(int area)
    {
        this.area = area;
    }

    /**
     * set location path
     * @param list list of each point
     */
    public void setPath(ArrayList<Point> list)
    {
        this.path = list;
    }

    /**
     * get the location path
     * @return list of each point
     */
    public ArrayList<Point> getPath() {
        return path;
    }

    /**
     * set the store at the location
     * @param reference the store
     */
    public void setReference(Boutique reference) {
        this.reference = reference;
    }

    /**
     * method to display a location
     * @return the display
     */
    public String toString()
    {
        return "Id :"+this.id+" ;" +
                " Nom :"+this.name+";" +
                " Superficie  : "+this.area+"; " +
                "cat : "+this.category+"; " +
                "mark : "+this.mark+";";
    }

    @Override
    public int compareTo(Object o) {
        MarkedLocation ml = (MarkedLocation) o;
        return ((Integer) this.mark).compareTo((Integer) ml.getMark());
    }
}
