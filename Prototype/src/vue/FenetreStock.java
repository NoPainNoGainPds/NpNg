package vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import net.miginfocom.swing.MigLayout;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.*;

/**
 * Represents the view of the storage
 */
public class FenetreStock extends JFrame{
    /**
     * Constructor
     */
    public FenetreStock(){
            this.setTitle("Fenetre");
            this.setSize(1200, 500);
            this.setLocationRelativeTo(null);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            //OutputStorage CSpan = new OutputStorage();
            CreateProduct CPpan = new CreateProduct();
            //this.add(CSpan);
            this.add(CPpan);

            //this.setVisible(true);
    }

    public void actionPerformed (ActionEvent arg0){


    }


}
