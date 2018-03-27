package vue;

import model.Boutique;
import model.CategorieBoutique;
import model.Emplacement;
import utils.Constants;
import utils.daoUtils.CategorieBoutiqueDAO;
import utils.daoUtils.EmplacementDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

/**
 * Represents the view to insert a store
 */
public class InsertBoutique extends JFrame implements Runnable
    /**
     * The store
     */
    private Boutique b;
        /**
         * TextLabel to enter the location
         */
    private TextLabel<JComboBox<Emplacement>> emplacement;
        /**
         * TextLabel to enter the category
         */
    private TextLabel<JComboBox<CategorieBoutique>> cat;
        /**
         * TextLabel to enter the store's name
         */
    private TextLabel<JTextField> nomboutique;
    /**
     * Error message if needed
     */
    private JLabel msgErreur ;
        /**
         * Boolean to know if it's  been saved
         */
    private boolean saved = false;

        /**
         * to get the saved attribute
         * @return the attribute
         */
    public boolean saved()
    {
        return this.saved;
    }

        /**
         * Constructor
         * @param b the store
         * @param f the frame
         */
    public InsertBoutique(Boutique b,Fenetre f)
    {
        this.b = b;
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if(saved)
                {
                    //nothing
                    f.notifyBoutique();
                }else
                {
                    f.notifyBoutique();
                }
            }
        });
        this.setSize(Constants.WIDTH,Constants.HEIGHT);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.add(this.setForm(),BorderLayout.CENTER);
        JButton btnSave = new JButton("Save");
        this.msgErreur = new JLabel("");
        btnSave.addActionListener(event ->
        {
            String s = nomboutique.field.getText();
            b.setNom(s);
            System.out.println(b);
            if(b.getDaoClass().create(b))
            {
                //affichage couleur vert
                this.msgErreur.setText("OK");
                this.msgErreur.setForeground(Color.GREEN);
                this.saved = true;
            }else{
                //message d'erreur
                this.msgErreur.setText("Error!");
                this.msgErreur.setForeground(Color.RED);
            }
            this.repaint();
        });
        this.add(btnSave, BorderLayout.SOUTH);
        this.add(this.msgErreur,BorderLayout.EAST);
        //this.setVisible(true);

    }

        /**
         * to get the store
         * @return the store
         */
    public Boutique getBoutique() {
        if(saved)
            return b;
        else
            return null;
    }

        /**
         * to create the form
         * @return the panel
         */
    private JPanel setForm()
    {
        JPanel panel = new JPanel();

        //Store Name
        nomboutique = new TextLabel(new JTextField(25),new JLabel("nom Boutique"));
        nomboutique.field.addActionListener(event ->
        {
            String s = nomboutique.field.getText();
            b.setNom(s);
        });
        //Store Location
        ArrayList<Emplacement> list = new EmplacementDAO().findFromReference();
        if(list!=null)
        {
            emplacement = new TextLabel<JComboBox<Emplacement>>(new JComboBox<Emplacement>(list.toArray(new Emplacement[list.size()])),new JLabel("Emplacement"));
            emplacement.field.addActionListener(event ->
            {
                b.setEmplacementRef(emplacement.field.getItemAt(emplacement.field.getSelectedIndex()));
            });
        }
        //categorie store
        ArrayList<CategorieBoutique> list2 = new CategorieBoutiqueDAO().findFromReference();
        if(list2 != null) {
             cat = new TextLabel<>(new JComboBox(list2.toArray(new CategorieBoutique[list2.size()])), new JLabel("categorie boutique"));
             cat.field.addActionListener(event ->
             {
                 b.setCategorieBoutiqueRef(cat.field.getItemAt(cat.field.getSelectedIndex()));
             });
        }
        panel.add(nomboutique);
        panel.add(emplacement);
        panel.add(cat);
        return panel;
    }

        /**
         * The run method
         */
    @Override
    public void run() {
        this.setVisible(true);
    }
}
