package vue;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
public class ImageComponent extends JPanel implements Serializable {
    Image image = null;
    private boolean isSquare;
    public ImageComponent(Image image,boolean isSquare) {
        this.image = image;
        this.isSquare = isSquare;
    }
    public ImageComponent(boolean isSquare) {
        this.isSquare = isSquare;
    }
    public void setImage(Image image){
        this.image = image;
    }
    public Image getImage(Image image){
        return image;
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g); //paint background
        if (image != null) { //there is a picture: draw it
            int height = this.getSize().height;
            int width = this.getSize().width;
            if(isSquare)
            {
                if(height>width)
                    height=width;
                if(width>height)
                    width=height;
            }
            //g.drawImage(image, 0, 0, this); //use image size
            g.drawImage(image,0,0, width-1, height-1, this);
            g.drawRect(0,0,width-1,height);
        }
    }
}

