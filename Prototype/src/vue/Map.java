package vue;

import model.Boutique;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 */
public class Map extends JPanel {
    /**
     * Liste of all polygons who represent a location on the map
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
     *  Referenc of the right panel
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
     * constructor of the map
     */
    public Map()
    {
        this.polygons = new ArrayList<MyPolygon>();
        this.img1 = Toolkit.getDefaultToolkit().getImage("Prototype/src/res/PlanCentreCo.png");
        this.popUpMap = new PopUpMap();
    }

    /**
     * initiialize all the polygons
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
     * @param polygons Arraylist<Polygon> that will be use in map
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
        //this.clicked = null;
    }
    public void setClickedDefault()
    {
        this.clicked = null;
    }
    /**
     * Say to the map where the usere clicked
     */
    public void setClicked()
    {
        for(MyPolygon poly: this.polygons)
        {
            if(poly.isSelected())
            {
                this.clicked = poly;
                System.out.println(poly);
                return;
            }
            this.clicked=null;
        }
    }
    public void setSearch(Integer[] liste)
    {
        System.out.println(liste.toString());
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
    @Override
    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2.drawImage(this.img1, 0 , 0,this.getWidth(), this.getHeight(), this);
        g2.setColor(Color.PINK);
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
                g2.setColor(Color.PINK);
            }
            if(poly.isSearched())
            {
                g2.setColor(Color.CYAN);
                g2.fillPolygon(poly);
                g2.drawPolygon(poly);
                g2.setColor(Color.PINK);
            }
            if(poly.equals(this.clicked))
            {
                g2.setColor(Color.ORANGE);
                g2.fillPolygon(poly);
                g2.drawPolygon(poly);
                g2.setColor(Color.PINK);
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
    }
}
