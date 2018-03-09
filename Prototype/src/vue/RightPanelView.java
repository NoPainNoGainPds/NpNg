package vue;

import controller.AnimatedCardLayoutListener;
import model.Boutique;
import utils.AnimatedCardLayout;
import utils.Constants;

import javax.swing.*;
import java.awt.*;

/**
 *
 */
public class RightPanelView extends JPanel {
    /**
     *
     */
    private Boutique boutique;
    /**
     *
     */
    private JPanel view1;
    /**
     *
     */
    private JPanel view2;
    /**
     *
     */
    private JLabel nomBoutique ;
    /**
     *
     */
    private JLabel categorieEmplacement;
    /**
     *
     */
    private JLabel nomEmplacement;
    /**
     *
     */
    private ImageComponent logo;
    /**
     *
     */
    private Image img;
    private AnimatedCardLayout cl;
    /**
     *
     */
    public RightPanelView()
    {
        this.cl = new AnimatedCardLayout();
        this.setLayout(this.cl);
        //build 1st View
        this.buildFirstView();
        this.buildSecondView();
        this.setPreferredSize(new Dimension(110,Constants.WINDOW_HEIGHT));
    }

    /**
     *
     */
    private void buildFirstView()
    {
        this.view1 = new JPanel();
        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        this.view1.setLayout(gbl);
        //this.view1.setLayout(new GridLayout(6,1));
        //add Label Boutique
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.0;
        gbc.weighty = 0.1;
        this.nomBoutique = new JLabel();
        this.nomBoutique.setPreferredSize(new Dimension(110,30));
        this.view1.add(this.nomBoutique,gbc);
        //---------------
        //add logo
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 0.0;
        gbc.weighty = 0.1;
        this.img = Toolkit.getDefaultToolkit().getImage("Prototype/src/res/empty-logo.png");
        this.logo = new ImageComponent();
        this.logo.setBackground(Color.PINK);
        //this.logo.setImage(this.img);
        this.view1.add(this.logo,gbc);
        //--------------
        //add Label location
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.0;
        gbc.weighty = 0.2;
        this.nomEmplacement = new JLabel();
        this.nomEmplacement.setPreferredSize(new Dimension(110,30));
        this.view1.add(this.nomEmplacement,gbc);
        //----------------
        //add label Categorie location
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.0;
        gbc.weighty = 0.2;
        this.categorieEmplacement = new JLabel();
        this.categorieEmplacement.setPreferredSize(new Dimension(110,30));
        this.view1.add(this.categorieEmplacement,gbc);
        //------------------------
        //add "NEXT" btn
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0.0;
        gbc.weighty = 0.1;
        JButton btn1 = new JButton("next");
        btn1.setMaximumSize(new Dimension(Constants.BTN_WIDTH,Constants.BTN_HEIGHT));
        btn1.addActionListener(event ->
        {
            this.next();
        });
        this.view1.add(btn1,gbc);
        //----------------------
        //add panel to right main panel
        this.add(this.view1,"view1");
    }

    /**
     *
     */
    private void buildSecondView()
    {
        this.view2 = new JPanel();
        this.view2.setBackground(Color.GREEN);
        JButton btnPrevious = new JButton("previous");
        btnPrevious.setMaximumSize(new Dimension(Constants.BTN_WIDTH,Constants.BTN_HEIGHT));
        btnPrevious.addActionListener(event ->
        {
            this.previous();
        });
        this.view2.add(btnPrevious);
        this.add(this.view2,"view2");
    }

    /**
     *
     * @param b
     */
    public void recieveData(Boutique b)
    {
        this.boutique = b;
        this.nomBoutique.setText(this.boutique.getNom());
        this.nomEmplacement.setText(this.boutique.getEmplacement().getNom());
        this.categorieEmplacement.setText(this.boutique.getEmplacement().getCat());
        this.logo.setImage(Toolkit.getDefaultToolkit().getImage(boutique.getLogo()));
        this.repaint();
        this.cl.show(this,"view1");
    }

    /**
     *
     */
    public void next()
    {
        Component current = this.cl.getCurrentComponent(this);
        Component next = this.cl.getNextComponent(this);
        Rectangle b = current.getBounds();
        next.setVisible(true);
        AnimatedCardLayoutListener animLayoutList = new AnimatedCardLayoutListener(10,current,next,true,this.cl,this);
        Timer timer = new Timer(20,animLayoutList);
        animLayoutList.timer = timer;
        timer.start();
    }
    public void previous()
    {
        Component current = this.cl.getCurrentComponent(this);
        Component previous = this.cl.getPreviousComponent(this);
        Rectangle b = current.getBounds();
        previous.setVisible(true);
        AnimatedCardLayoutListener animLayoutList = new AnimatedCardLayoutListener(10,current,previous,false,this.cl,this);
        Timer timer = new Timer(20,animLayoutList);
        animLayoutList.timer = timer;
        timer.start();
    }
}
