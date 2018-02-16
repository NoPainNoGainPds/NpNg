package vue;

import model.Boutique;
import model.Produit;
import utils.Constants;
import utils.MyListModel;
import utils.daoUtils.ProduitDAO;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class VueBoutique extends JFrame {
    private Boutique boutique;
    private JList<Produit> jlistProduit;
    private JLabel msgError;
    public VueBoutique(Boutique boutique)
    {
        super("Boutique");
        this.boutique = boutique;
        this.setSize(Constants.WIDTH,Constants.HEIGHT);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.msgError = new JLabel();
        //Recover the products list.
        ProduitDAO pDao = new ProduitDAO();
        ArrayList<Produit> listProduit = pDao.findFromReference(boutique.getId());
        if(listProduit==null)
        {
            return;
        }
        MyListModel<Produit> listModel = new MyListModel<>(listProduit);
        //Data in a jlist for displaying
        this.jlistProduit = new JList<Produit>(listModel);
        this.jlistProduit.setFixedCellWidth(80);
        //Display and Layout
        JScrollPane jscrollPane = new JScrollPane(jlistProduit);
        JPanel panel = new JPanel();
        panel.add(jscrollPane);
        this.add(panel, BorderLayout.EAST);
        panelDroit();
        this.setVisible(true);
    }

    /**
     * This Method add the right panel
     */

    private void panelDroit()
    {
        JPanel panel = new JPanel();
        // Button to Add a product
        JButton ajout = new JButton("Ajouter");
        //Button to delete a product
        JButton suppr = new JButton("Supprimer");
        //Changes of products Button
        JButton modif = new JButton("Modifier");
        panel.add(ajout);
        panel.add(suppr);
        panel.add(modif);
        //Additional Controllers
        ajout.addActionListener(event ->
        {
            //action ici :)
            this.msgError.setText("Pas pour le moment");
            this.msgError.setForeground(Color.RED);
        });
        suppr.addActionListener(event ->
        {
            //et la aussi pour suppr
            this.msgError.setText("Pas pour le moment");
            this.msgError.setForeground(Color.RED);
        });
        modif.addActionListener(event ->
        {
            new UpdateWindow<>(jlistProduit.getSelectedValue());
        });
        this.add(panel,BorderLayout.WEST);

    }
}
