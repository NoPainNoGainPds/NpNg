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
    public VueBoutique(Boutique boutique)
    {
        super("Boutique");
        this.boutique = boutique;
        this.setSize(Constants.WIDTH,Constants.HEIGHT);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //recuperation de la liste de produits
        ProduitDAO pDao = new ProduitDAO();
        ArrayList<Produit> listProduit = pDao.findFromReference(boutique.getId());
        MyListModel<Produit> listModel = new MyListModel<>(listProduit);
        //donees dans une jlist pour l'afffichage
        this.jlistProduit = new JList(listModel);
        //affichage et mise en page
        JScrollPane jscrollPane = new JScrollPane(jlistProduit);
        JPanel panel = new JPanel();
        panel.add(jscrollPane);
        this.add(panel, BorderLayout.EAST);
        panelDroit();
        this.setVisible(true);
    }

    /**
     * Method qui ajoute le panneau droit
     */
    private void panelDroit()
    {
        JPanel panel = new JPanel();
        //bouton d'ajout de produit
        JButton ajout = new JButton("Ajouter");
        //bouton de suppression de produit
        JButton suppr = new JButton("Supprimer");
        //bouton de modification de produit
        JButton modif = new JButton("Modifier");
        panel.add(ajout);
        panel.add(suppr);
        panel.add(modif);
        //ajout des controler
        ajout.addActionListener(event ->
        {
            //action ici :)
        });
        suppr.addActionListener(event ->
        {
            //et la aussi pour suppr
        });
        modif.addActionListener(event ->
        {
            new UpdateWindow<>(jlistProduit.getSelectedValue());
        });
        this.add(panel,BorderLayout.WEST);

    }
}
