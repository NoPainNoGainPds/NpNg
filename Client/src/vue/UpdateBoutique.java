package vue;

import model.Boutique;
import model.Emplacement;
import utils.Constants;
import utils.daoUtils.EmplacementDAO;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Represents the view to update a store
 */
public class UpdateBoutique extends JFrame {
    /**
     * The Store
     */
    private Boutique b;

    /**
     * Constructor
     * @param b the store
     */
    public UpdateBoutique(Boutique b)
    {
        this.b = b;
        this.setSize(Constants.WIDTH, Constants.HEIGHT);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.add(this.setForm());
        this.setVisible(true);

    }

    /**
     * to create the form
     * @return the panel
     */
    private JPanel setForm()
    {
        JPanel panel = new JPanel();

        //Store Name
        TextLabel<JTextField> nomboutique = new TextLabel(new JTextField(25),new JLabel("nom Boutique"));
        //Store Location
        ArrayList<Emplacement> list = new EmplacementDAO().findFromReference();
        TextLabel<JComboBox> emplacement = new TextLabel<>(new JComboBox(list.toArray()),new JLabel("Emplacement"));
        panel.add(nomboutique);
        panel.add(emplacement);
        return panel;
    }
}