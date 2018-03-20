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
            int original_width = this.image.getWidth(this);
            int original_height = this.image.getHeight(this);
            int bound_width = 150;
            int bound_height = 150;
            int new_width = original_width;
            int new_height = original_height;
            if (original_width > bound_width) {
                new_width = bound_width;
                new_height = (new_width * original_height) / original_width;
            }

            // then check if we need to scale even with the new height
            if (new_height > bound_height) {
                //scale height to fit instead
                new_height = bound_height;
                //scale width to maintain aspect ratio
                new_width = (new_height * original_width) / original_height;
            }

            //g.drawImage(image, 0, 0, this); //use image size
            g.drawImage(image,0,0, new_width-1, new_height-1, this);
            g.drawRect(0,0,new_width-1,new_height);
        }
    }
}

