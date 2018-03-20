//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package vue;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import model.Boutique;
import model.Produit;
import net.miginfocom.swing.MigLayout;
import utils.MyListModel;
import utils.daoUtils.ProduitDAO;

public class DetailStore extends JFrame implements Runnable {
    private Boutique store;
    private Image img;
    private ImageComponent logo;
    private JLabel nomBoutique;
    private JLabel msgError;

    public DetailStore(Boutique store) {
        super("Detail Store");
        this.setSize(new Dimension(500, 420));
        this.store = store;
        JPanel panel = new JPanel();
        panel.setLayout(new MigLayout("inset 10", "[fill, grow]", "[fill, grow]"));
        URL url = null;
        BufferedImage c = null;
        this.img = Toolkit.getDefaultToolkit().getImage(this.store.getLogo());
        this.logo = new ImageComponent(true);
        this.logo.setImage(this.img);
        panel.add(this.logo, "cell 0 0 2 2, split 2");
        this.nomBoutique = new JLabel(store.getNom());
        panel.add(this.nomBoutique);
        this.add(panel);
        JButton btn1 = new JButton("Informations de la boutique");
        JButton btn2 = new JButton("Informations sur le produit selectionne");
        JButton btn3 = new JButton("Entrees de stock");
        JButton btn4 = new JButton("Ajouter un produit");
        panel.add(btn1, "cell 0 2, HEIGHT 40:40:40, WIDTH 150:220:300");
        panel.add(btn2, "cell 0 3, HEIGHT 40:40:40, WIDTH 150:220:300");
        panel.add(btn3, "cell 0 4, HEIGHT 40:40:40, WIDTH 150:220:300");
        panel.add(btn4, "cell 0 5, HEIGHT 40:40:40, WIDTH 150:220:300");
        /*ProduitDAO pDao = new ProduitDAO();
        ArrayList<Produit> listProduit = pDao.findFromReference(store.getId());
        MyListModel<Produit> listModel = new MyListModel(listProduit);
        JList<Produit> jlist = new JList(listModel);
        jlist.setFixedCellWidth(90);
        JScrollPane jscrollPane = new JScrollPane(jlist);
        panel.add(jscrollPane, "cell 2 2 2 4");
        btn2.addActionListener((event) -> {
            DetailProduct ds = new DetailProduct(this.store, jlist.getSelectedValue());
            SwingUtilities.invokeLater(ds);
        });*/
    }

    public void run() {
        this.setVisible(true);
    }
}
