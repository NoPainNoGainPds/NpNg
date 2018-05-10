package vue;

import model.Boutique;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *Represents the map
 */
public class Map extends JPanel {
    private Color REDGRAY = Color.decode("#8D3956");
    private Color GREENBLUEGRAY = Color.decode("#61AB99");
    private Color SELECTEDCOLOR = Color.decode("#FF804C");
    /**
     * List of all polygons which represent a location on the map
     */
    private ArrayList<MyPolygon> polygons;
    /**
     *  Background image
     */
    private Image img1;
    /**
     *  Location selected
     */
    private MyPolygon clicked = null;
    /**
     *  Reference of the right panel
     */
    private RightPanelView rpv;
    /**
     * Object used for popUp effect
     */
    private PopUpMap popUpMap;
    /**
     *  Boolean, true : draw the popup, false don't draw the popup
     */
    private boolean over = false;
    /**
     *
     */
    private ArrayList<Point> pathCourse;
    /**
     *
     */
    private ArrayList<Integer> intCourse;
    /**
     * constructor of the map
     */
    private static final int SIZE_OF_POINT = 2;
    public Map()
    {

        this.polygons = new ArrayList<MyPolygon>();
        this.img1 = Toolkit.getDefaultToolkit().getImage("Client/src/res/PlanCentreCo.png");
        this.popUpMap = new PopUpMap();
    }

    /**
     * initialize all the polygons
     */
    public void setNews()
    {
        for(MyPolygon poly : this.polygons)
        {
            poly.setnew();;
        }
    }

    /**
     * replace all the polygons by the new polygons in param
     * @param polygons Arraylist<Polygon> that will be used in map
     */
    public void setPolygons(ArrayList<MyPolygon> polygons)
    {
        this.polygons = polygons;
        this.repaint();
    }
    /**
     * Resize all the sub component with the new size of the map
     */
    public void refresh()
    {
        for(MyPolygon poly : this.polygons)
        {
            poly.refresh(this.getSize());
        }
        this.setCourse(this.intCourse);
        //this.clicked = null;
    }
    public void setClickedDefault()
    {
        this.clicked = null;
    }
    /**
     * Say to the map where the user clicked
     */
    public void setClicked()
    {
        for(MyPolygon poly: this.polygons)
        {
            if(poly.isSelected())
            {
                this.clicked = poly;
                return;
            }
            this.clicked=null;
        }
    }
    public void setSearch(Integer[] liste)
    {
        for(MyPolygon mp : this.polygons)
        {
            if(Arrays.asList(liste).contains(new Integer(mp.getBoutique().getId())))
            {
                mp.setSearch(true);
            }else
            {
                mp.setSearch(false);
            }
        }
        this.repaint();
    }
    public void setRpv(RightPanelView rpv)
    {
        this.rpv = rpv;
    }
    /**
     * Say to the map where the user is pointing
     * @param x
     * @param y
     */
    public void setMousePos(int x,int y)
    {
        //System.out.println(x+";"+y);
        for(MyPolygon poly: this.polygons)
        {
            this.popUpMap.x = x+10;
            this.popUpMap.y = y+10;
            if(poly.contains(x, y))
            {
                poly.setSelected(true);
                this.over = true;
                this.popUpMap.setBoutique(poly.getBoutique());
                return;
            }
            else
            {
                this.over = false;
                poly.setSelected(false);
            }
        }
    }

    /**
     * Methode who allow to send Data to another panel
     */
    public void sendData()
    {
        if(this.clicked!=null && this.rpv!=null)
        {
            this.rpv.recieveData(this.clicked.getBoutique());
        }
        return;
    }

    public void setCourse(ArrayList<Integer> liste)
    {
        //calcul coordinates of stores in the liste to draw course line
        this.intCourse = liste;
        ArrayList<Point> returnValue = new ArrayList<>();
        for(MyPolygon poly : this.polygons)
        {
            System.out.println("polygon");
            Integer b = poly.getIdEmplacement();
            if(b!=-1)
            {
                //is in liste ?
                for(Integer value : liste) {
                    System.out.println("is in ?"+b);
                    if (value.equals(b)) {
                        System.out.println("match");
                        Point p = poly.centerOfPoly();
                        returnValue.add(p);
                    }
                }
            }
        }
        this.pathCourse = returnValue;
    }
    /*
    public void setCourse(ArrayList<Boutique> liste)
    {
        //calcul coordinates of stores in the liste to draw course line
        ArrayList<Point> returnValue = new ArrayList<>();
        for(MyPolygon poly : this.polygons)
        {
            Boutique b = poly.getBoutique();
            if(b!=null)
            {
                //is in liste ?
                if(liste.contains(b))
                {
                    Point p = poly.centerOfPoly();
                    returnValue.add(p);
                }
            }
        }
        this.pathCourse = returnValue;
    }*/
    @Override
    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2.drawImage(this.img1, 0 , 0,this.getWidth(), this.getHeight(), this);
        g2.setColor(this.REDGRAY);
        for(MyPolygon poly: this.polygons)
        {
            //System.out.println("dessin d'un poly");
            int polyId = poly.getIdEmplacement();
            if(!poly.isSelected())
                g2.drawPolygon(poly);
            else
            {
                g2.fillPolygon(poly);
                g2.drawPolygon(poly);
                g2.setColor(this.REDGRAY);
            }
            if(poly.isSearched())
            {
                g2.setColor(this.GREENBLUEGRAY);
                g2.fillPolygon(poly);
                g2.drawPolygon(poly);
                g2.setColor(this.REDGRAY);
            }
            if(poly.equals(this.clicked))
            {
                g2.setColor(this.SELECTEDCOLOR);
                g2.fillPolygon(poly);
                g2.drawPolygon(poly);
                g2.setColor(this.REDGRAY);
            }

        }
        if(this.over)
        {
            g2.setColor(new Color(0.2f,0.5f,0.5f,0.95f));
            g2.fill(this.popUpMap);
            g2.setColor(Color.BLACK);
            g2.draw(this.popUpMap);
            g2.setColor(Color.BLACK);
            g2.drawString(this.popUpMap.b.toString(),this.popUpMap.x+10,this.popUpMap.y+25);
        }
        g2.setColor(Color.YELLOW);
        Point old_p = null;
        if(this.pathCourse!=null)
        for(Point p : this.pathCourse)
        {
            Shape theCircle = new Ellipse2D.Double(p.getX() - this.SIZE_OF_POINT, p.getY() - this.SIZE_OF_POINT, 2.0 * this.SIZE_OF_POINT, 2.0 * this.SIZE_OF_POINT);
            g2.draw(theCircle);
            if(old_p!=null)
            g2.drawLine(old_p.x,old_p.y,p.x,p.y);
            old_p = p;
        }
    }

}
