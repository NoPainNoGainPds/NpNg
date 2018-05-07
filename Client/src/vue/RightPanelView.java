package vue;

import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import controller.AnimatedCardLayoutListener;
import model.Boutique;
import net.miginfocom.swing.MigLayout;
import utils.AnimatedCardLayout;
import utils.Constants;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

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
        this.setSize(new Dimension(150 ,25));
        this.setMaximumSize(new Dimension(150 ,25));
        this.setPreferredSize(new Dimension(150 ,25));
        this.buildFirstView();
        this.buildSecondView();
        this.repaint();
    }

    /**
     *
     */
    private void buildFirstView()
    {
        this.view1 = new JPanel();
        this.view1.setLayout(new MigLayout("inset 25", "[fill, grow]", "[fill, grow]"));
        //add Label Boutique

        this.nomBoutique = new JLabel();
        //this.nomBoutique.setMaximumSize(new Dimension(150 ,25));
        this.view1.add(this.nomBoutique,"cell 0 0 1 0");
        //---------------
        //add logo

        this.img = Toolkit.getDefaultToolkit().getImage("Client/src/res/empty-logo.png");
        this.logo = new ImageComponent(true);
        this.logo.setImage(this.img);
        this.logo.setMaximumSize(new Dimension(150 ,150));
        this.view1.add(this.logo,"cell 0 1 2 1, grow");
        //--------------
        //add Label location

        this.nomEmplacement = new JLabel();
        this.view1.add(this.nomEmplacement,"cell 0 2");
        //----------------
        //add label Categorie location

        this.categorieEmplacement = new JLabel();
        this.view1.add(this.categorieEmplacement,"cell 0 3");
        //------------------------
        //add "NEXT" btn

        JButton btn1 = new JButton("performance");
        //btn1.setMaximumSize(new Dimension(Constants.BTN_WIDTH,Constants.BTN_HEIGHT));
        btn1.addActionListener(event ->
        {
            this.next();
        });
        JButton btn2 = new JButton("See more");
        btn2.addActionListener(event ->
        {
            if(this.boutique!=null)
            {
                DetailStore ds = new DetailStore(this.boutique);
                SwingUtilities.invokeLater(ds);
            }else
            {
                JOptionPane.showMessageDialog(null, "alert", "alert", JOptionPane.ERROR_MESSAGE);
            }
        });
        JPanel buttonPanel = new JPanel(new MigLayout("", "[center, grow]"));
        buttonPanel.add(btn2,"");
        buttonPanel.add(btn1,"");
        btn1.setBorder(new TextBubbleBorder(Color.GRAY.darker(),2,4,0));
        btn2.setBorder(new TextBubbleBorder(Color.GRAY.darker(),2,4,0));
        this.view1.add(buttonPanel,"dock south");
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
        this.view2.setLayout(new MigLayout("inset 25", "[fill, grow]", "[fill, grow]"));
        //btn previous
        JButton btnPrevious = new JButton("previous");
        btnPrevious.setMaximumSize(new Dimension(Constants.BTN_WIDTH,Constants.BTN_HEIGHT));
        btnPrevious.addActionListener(event ->
        {
            this.previous();
        });
        JPanel buttonPanel = new JPanel(new MigLayout("", "[center, grow]"));
        buttonPanel.add(btnPrevious,"");
        this.view2.add(buttonPanel,"dock south");
        this.add(this.view2,"view2");
    }

    /**
     *
     * @param b
     */
    public void recieveData(Boutique b)
    {
        this.boutique = b;
        this.nomBoutique.setText("Store Name : "+this.boutique.getNom());
        this.nomEmplacement.setText("Location : "+this.boutique.getEmplacement().getNom());
        this.categorieEmplacement.setText("Categorie Location : "+this.boutique.getEmplacement().getCat());

        String logo2 = boutique.getLogo();
        if(logo2==null)
            logo2 = "Client/src/res/empty-logo.png";
        this.logo.setImage(Toolkit.getDefaultToolkit().getImage(logo2));

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
        AnimatedCardLayoutListener animLayoutList = new AnimatedCardLayoutListener(15,current,next,true,this.cl,this);
        Timer timer = new Timer(20,animLayoutList);
        animLayoutList.timer = timer;
        timer.start();
    }

    /**
     *
     */
    public void previous()
    {
        Component current = this.cl.getCurrentComponent(this);
        Component previous = this.cl.getPreviousComponent(this);
        Rectangle b = current.getBounds();
        previous.setVisible(true);
        AnimatedCardLayoutListener animLayoutList = new AnimatedCardLayoutListener(15,current,previous,false,this.cl,this);
        Timer timer = new Timer(20,animLayoutList);
        animLayoutList.timer = timer;
        timer.start();
    }
}
