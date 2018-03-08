package vue;

import model.Boutique;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class PopUpMap extends Rectangle{
    public Boutique b;
    public PopUpMap()
    {
        super(0,0,150,45 );
        this.b = null;
    }
    public void setBoutique(Boutique b)
    {
        this.b = b;
        this.width = this.b.toString().length()*6;
    }
}
