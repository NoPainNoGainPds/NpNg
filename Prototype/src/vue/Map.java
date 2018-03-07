package vue;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 *
 */
public class Map extends JPanel {
    private ArrayList<MyPolygon> polygons;
    private Image img1;
    private MyPolygon clicked = null;
    /**
     *
     */
    public Map()
    {
        this.polygons = new ArrayList<MyPolygon>();
        this.img1 = Toolkit.getDefaultToolkit().getImage("src/res/PlanCentreCo.png");
    }
    public void setPolygons(ArrayList<MyPolygon> polygons)
    {
        this.polygons = polygons;
        this.repaint();
    }
    public void refresh()
    {
        for(MyPolygon poly : this.polygons)
        {
            poly.refresh(this.getSize());
        }
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
