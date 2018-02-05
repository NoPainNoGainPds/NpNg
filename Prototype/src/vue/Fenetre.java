package vue;

import javax.swing.*;
import utils.Constants;

public class Fenetre extends JFrame implements Runnable{
    public Fenetre(String s)
    {
        super(s);
        this.setSize(Constants.WIDTH,Constants.HEIGHT);
    }
    @Override
    public void run() {
        this.setVisible(true);
    }
}
