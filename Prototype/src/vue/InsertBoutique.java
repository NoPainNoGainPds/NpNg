package vue;

import model.Boutique;
import model.Emplacement;
import utils.Constants;
import utils.daoUtils.EmplacementDAO;

import javax.swing.*;
import java.util.ArrayList;

public class InsertBoutique extends JFrame{
    private Boutique b;
    public InsertBoutique(Boutique b)
    {
        this.b = b;
        this.setSize(Constants.WIDTH,Constants.HEIGHT);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.add(this.setForm());
        JButton btnSave = new JButton("Save");
        btnSave.addActionListener(event ->
        {
            b.getDaoClass().create(b);
        });
        this.setVisible(true);

    }
    private JPanel setForm()
    {
        JPanel panel = new JPanel();

        //nom de la boutique
        TextLabel<JTextField> nomboutique = new TextLabel(new JTextField(25),new JLabel("nom Boutique"));
        nomboutique.field.addActionListener(event ->
        {
            String s = nomboutique.field.getText();
            b.setNom(s);
        });
        //emplacement
        ArrayList<Emplacement> list = new EmplacementDAO().findFromReference();
        TextLabel<JComboBox> emplacement = new TextLabel<>(new JComboBox(list.toArray()),new JLabel("Emplacement"));
        panel.add(nomboutique);
        panel.add(emplacement);
        return panel;
    }
}
