package vue;

import model.Boutique;
import model.Emplacement;
import utils.Constants;
import utils.daoUtils.EmplacementDAO;

import javax.swing.*;
import java.util.ArrayList;

public class UpdateBoutique extends JFrame {
    private Boutique b;
    public UpdateBoutique(Boutique b)
    {
        this.b = b;
        this.setSize(Constants.WIDTH, Constants.HEIGHT);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.add(this.setForm());
        this.setVisible(true);

    }
    private JPanel setForm()
    {
        JPanel panel = new JPanel();

        //nom de la boutique
        TextLabel<JTextField> nomboutique = new TextLabel(new JTextField(25),new JLabel("nom Boutique"));
        //emplacement
        ArrayList<Emplacement> list = new EmplacementDAO().findFromReference();
        TextLabel<JComboBox> emplacement = new TextLabel<>(new JComboBox(list.toArray()),new JLabel("Emplacement"));
        panel.add(nomboutique);
        panel.add(emplacement);
        return panel;
    }
}