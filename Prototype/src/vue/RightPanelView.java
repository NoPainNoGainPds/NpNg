package vue;

import model.Boutique;

import javax.swing.*;
import java.awt.*;

public class RightPanelView extends JPanel {
    private Boutique boutique;
    private CardLayout cl;
    private JPanel view1;
    private JLabel nomBoutique ;
    public RightPanelView()
    {
        this.cl = new CardLayout();
        this.setLayout(this.cl);
        //build 1st View
        this.buildFirstView();
    }
    private void buildFirstView()
    {
        this.view1 = new JPanel();
        this.nomBoutique = new JLabel();
        this.nomBoutique.setPreferredSize(new Dimension(110,30));
        this.view1.add(this.nomBoutique);
        this.add(this.view1,"view1");
    }
    public void recieveData(Boutique b)
    {
        this.boutique = b;
        this.nomBoutique.setText(this.boutique.getNom());
        this.repaint();
        this.cl.show(this,"view1");
    }
}
