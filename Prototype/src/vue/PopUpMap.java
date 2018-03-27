package vue;

import model.Boutique;

import java.awt.*;

/**
 * Represents the class to create a Pop Up
 */
public class PopUpMap extends Rectangle{
    public Boutique b;
    public PopUpMap()
    {
        super(0,0,150,45 );
        this.b = null;
    }

    /**
     * Constructor
     * @param b
     */
    public void setBoutique(Boutique b)
    {
        this.b = b;
        this.width = this.b.toString().length()*6;
    }
}
