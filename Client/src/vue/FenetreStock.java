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
public class FenetreStock extends JFrame implements Runnable {
    /**
     * Constructor
     */
    public FenetreStock(int type){
            this.setTitle("Fenetre");
            this.setSize(1200, 500);
            this.setLocationRelativeTo(null);
            this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            switch(type)
            {
                case 1:
                    OutputStorage pan = new OutputStorage();
                    this.add(pan);
                    break;
                case 2:
                    EntryIntoStorage pan2 = new EntryIntoStorage();
                    this.add(pan2);
                    break;
                case 3:
                    CreateProduct pan3 = new CreateProduct();
                    this.add(pan3);
                    break;
            }
    }



    /**
     * Run method
     */
    public void run() {
        this.setVisible(true);
    }
}
