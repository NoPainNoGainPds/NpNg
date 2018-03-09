package vue;

import javax.swing.*;
import java.awt.*;

public class ImageComponent extends JComponent {
    private Image logo;
    public ImageComponent()
    {
        super();
        this.logo = Toolkit.getDefaultToolkit().getImage("Prototype/src/res/empty-logo.png");;
    }
    public void setImage(Image logo)
    {
        this.logo = logo;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(g.drawImage(this.logo,0,0,this))
            System.out.println("dessin");
        else
            System.out.println("pas");
    }
}
