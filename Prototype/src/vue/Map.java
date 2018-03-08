package vue;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 *
 */
public class Map extends JPanel {
    /**
     *
     */
    private ArrayList<MyPolygon> polygons;
    private Image img1;
    private MyPolygon clicked = null;
    /**
     * constructor of the map
     */
    public Map()
    {
        this.polygons = new ArrayList<MyPolygon>();
        this.img1 = Toolkit.getDefaultToolkit().getImage("Prototype/src/res/PlanCentreCo.png");
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
     * @param polygons
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
                return;
            }
            this.clicked=null;
        }
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
            if(poly.contains(x, y))
            {
                poly.setSelected(true);
                //System.out.println("IN");
            }
            else
            {
                poly.setSelected(false);
            }
        }
    }

    /**
     * Mthode who allow to send Data to another panel
     */
    public void sendData()
    {
        return;
    }
    @Override
    public void paintComponent(Graphics g)
    {


        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.WHITE);
        //g2.fillRect(0, 0, this.getWidth(), this.getHeight());
        g2.setColor(Color.PINK);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2.drawImage(this.img1, 0 , 0,this.getWidth(), this.getHeight(), this);
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
            if(poly==this.clicked)
            {
                g2.setColor(Color.ORANGE);
                g2.fillPolygon(poly);
                g2.drawPolygon(poly);
                g2.setColor(Color.PINK);
            }

        }
    }
}
