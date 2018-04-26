package vue;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

/**
 * Represents a component of an image
 */
public class ImageComponent extends JPanel implements Serializable {
    /**
     * The image
     */
    Image image = null;
    private int bound_width = 150;
    private int bound_height = 150;
    private boolean isSquare;

    /**
     * Constructor
     * @param image the image
     * @param isSquare the boolean
     */
    public ImageComponent(Image image,boolean isSquare) {
        this.image = image;
        this.isSquare = isSquare;
    }

    /**
     * Constructor
     * @param isSquare the boolean
     */
    public ImageComponent(boolean isSquare) {
        this.isSquare = isSquare;
    }

    /**
     * To set the image
     * @param image the image
     */
    public void setImage(Image image){
        this.image = image;
    }

    /**
     * to get the image
     * @return the image
     */
    public Image getImage(){
        return image;
    }

    public void setImageDimention(int w, int h)
    {
        this.bound_height = h;
        this.bound_width = w;
    }
    /**
     * paintComponent method to draw
     * @param g the graphics element
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g); //paint background
        if (image != null) { //there is a picture: draw it
            int original_width = this.image.getWidth(this);
            int original_height = this.image.getHeight(this);
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

