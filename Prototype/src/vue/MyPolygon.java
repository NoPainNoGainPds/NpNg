package vue;

import utils.Constants;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class MyPolygon extends Polygon {
    private boolean selected;
    private int id_emplacement;
    private int id_boutique;
    private String nom_boutique = "";
    public int[] new_x;
    public int[] new_y;
    private float surface = 0.0f;
    public MyPolygon() {
        // TODO Auto-generated constructor stub
        this.selected = false;
        this.id_emplacement = -1;
        this.id_boutique = 0;
    }
    public MyPolygon(int[] x,int[] y, int n)
    {
        super(x,y,n);
        this.selected = false;
    }
    public MyPolygon(ArrayList<Point> liste)
    {
        super();
        for(Point p : liste) {
            this.addPoint(p.x,p.y);
        }
    }
    public void setnew()
    {
        //System.out.println("setnew");
        this.new_x = Arrays.copyOf(this.xpoints,this.npoints);
        this.new_y = Arrays.copyOf(this.ypoints,this.npoints);
    }
    public void refresh(Dimension mapSize)
    {
        //System.out.println(mapSize);
        this.reset();
        this.npoints = this.new_x.length;
        for(int i=0;i<this.npoints;i++)
        {
            this.xpoints[i]=(int) ((this.new_x[i]*mapSize.getWidth())/ Constants.IMAGE_WIDTH);
            this.ypoints[i]=(int) ((this.new_y[i]*mapSize.getHeight())/Constants.IMAGE_HEIGHT);
            //System.out.println(this.new_x[i] + ";" + this.new_y[i]);
        }
    }
    public float getSurface()
    {
        return this.surface;
    }
    public void setSurface(float sur)
    {
        this.surface = sur;
    }
    public void setEmplacement(int id)
    {
        this.id_emplacement = id;
    }
    public int getIdEmplacement()
    {
        return this.id_emplacement;
    }
    public void setId_boutique(int id)
    {
        this.id_boutique = id;
    }
    public int getId_boutique()
    {
        return this.id_boutique;
    }
    public void setSelected(boolean b)
    {
        this.selected = b;
    }
    public boolean isSelected()
    {
        return this.selected;
    }
}
