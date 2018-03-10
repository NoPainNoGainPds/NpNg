package vue;

import model.Boutique;
import utils.Constants;

import javax.swing.*;
import java.awt.*;

public class DetailStore extends JFrame implements Runnable {
    private Boutique store;
    public DetailStore(Boutique store)
    {
        super("Detail Store");
        this.setSize(new Dimension(Constants.HEIGHT,Constants.WIDTH));
        this.store = store;
    }
    public void run()
    {
        this.setVisible(true);
    }
}
