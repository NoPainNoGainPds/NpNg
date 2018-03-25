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

public class FenetreStock extends JFrame{

    public FenetreStock(){
            this.setTitle("Fenetre");
            this.setSize(1200, 500);
            this.setLocationRelativeTo(null);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            EntryIntoStorage ESVpan = new EntryIntoStorage();
            this.add(ESVpan);

            //this.setVisible(true);
    }

    public void actionPerformed (ActionEvent arg0){


    }


}
