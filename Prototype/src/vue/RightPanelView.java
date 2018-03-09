package vue;

import model.Boutique;
import utils.Constants;

import javax.swing.*;
import java.awt.*;

public class RightPanelView extends JPanel {
    private Boutique boutique;
    private CardLayout cl;
    private JPanel view1;
    private JPanel view2;
    private JLabel nomBoutique ;
    private JLabel categorieEmplacement;
    private JLabel nomEmplacement;
    public RightPanelView()
    {
        this.cl = new CardLayout();
        this.setLayout(this.cl);
        //build 1st View
        this.buildFirstView();
        this.buildSecondView();
    }
    private void buildFirstView()
    {
        this.view1 = new JPanel();
        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        this.setLayout(gbl);
        //this.view1.setLayout(new GridLayout(6,1));
        //add Label Boutique
        this.nomBoutique = new JLabel();
        this.nomBoutique.setPreferredSize(new Dimension(110,30));
        //---------------
        //add Label location
        this.nomEmplacement = new JLabel();
        //----------------
        //add label Categorie location
        this.categorieEmplacement = new JLabel();
        //------------------------

        JButton btn1 = new JButton("next");
        btn1.setMaximumSize(new Dimension(Constants.BTN_WIDTH,Constants.BTN_HEIGHT));
        btn1.addActionListener(event ->
        {
            this.change();
        });

        this.view1.add(this.nomBoutique);
        this.view1.add(this.nomEmplacement);
        this.view1.add(this.categorieEmplacement);

        this.view1.add(btn1);

        this.add(this.view1,"view1");
    }
    private void buildSecondView()
    {
        this.view2 = new JPanel();
        this.add(this.view2,"view2");
    }
    public void recieveData(Boutique b)
    {
        this.boutique = b;
        this.nomBoutique.setText(this.boutique.getNom());
        this.nomEmplacement.setText(this.boutique.getEmplacement().getNom());
        this.categorieEmplacement.setText(this.boutique.getEmplacement().getCat());
        this.repaint();
        this.cl.show(this,"view1");
    }
    public void change()
    {
        this.cl.next(this);
        this.repaint();
    }
}
