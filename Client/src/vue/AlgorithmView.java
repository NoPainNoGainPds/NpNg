package vue;

import R3.Algorithm;
import model.Boutique;
import org.apache.log4j.Logger;
import utils.Constants;
import utils.daoUtils.BoutiqueDAO;
import utils.daoUtils.EmplacementDAO;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

public class AlgorithmView extends JPanel {
    private JButton launchAlgoButton;
    private EmplacementDAO eDAO;
    private BoutiqueDAO bDAO;
    private ArrayList<Boutique> listStores;
    Logger logger = Logger.getLogger(this.getClass());

    public AlgorithmView() {
        JPanel panel = new JPanel();
        this.eDAO = new EmplacementDAO();
        this.bDAO = new BoutiqueDAO();
        panel.setLayout(new BoxLayout(panel,BoxLayout.PAGE_AXIS));
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createTitledBorder("Launching / Create locations and stores"));
        JPanel beforeLaunchPanel = new JPanel();
        beforeLaunchPanel.setBorder(BorderFactory.createTitledBorder("Before lauching the algorithm"));
        listStores = bDAO.findFromReference();
        String[][] datas_before = new String[listStores.size()][2];
        String[] headers = {"Store", "Location"};
        for(int i = 0; i < listStores.size(); i++) {
            datas_before[i][0] = listStores.get(i).getNom();
            datas_before[i][1] = listStores.get(i).getEmplacement().getNom();
        }
        JTable table_before = new JTable(datas_before,headers);
        beforeLaunchPanel.add(new JScrollPane(table_before));
        JPanel afterLaunchPanel = new JPanel();
        afterLaunchPanel.setBorder(BorderFactory.createTitledBorder("After launching the algorithm "));
        launchAlgoButton = new JButton("Launch Assignation Algorithm");
        JButton createButton = new JButton("Populate database");
        launchAlgoButton.addActionListener((event) -> {
            logger.info("Launching assignation algorithm");
            System.out.println("Launching assignation algorithm...");
            String[][] datas_after = new String[listStores.size()][2];
            JTable table_after;
            eDAO.assignLocationsToStores();
            JOptionPane jop = new JOptionPane();
            listStores = bDAO.findFromReference();
            for(int i = 0; i < listStores.size(); i++) {
                datas_after[i][0] = listStores.get(i).getNom();
                datas_after[i][1] = listStores.get(i).getEmplacement().getNom();
            }
            table_after = new JTable(datas_after, headers);
            afterLaunchPanel.removeAll();
            afterLaunchPanel.add(new JScrollPane(table_after));
            jop.showMessageDialog(this, "Algorithm successfully executed !");
            this.setVisible(true);
        });
        createButton.addActionListener((event) -> {
            logger.info("Populating database");
            JOptionPane jop = new JOptionPane();
            String value = jop.showInputDialog(this,"How much stores you want ?", "Nombre de boutiques", JOptionPane.QUESTION_MESSAGE);
            int res = Integer.parseInt(value);
            bDAO.createStores(res);
            value = jop.showInputDialog(this,"How much locations you want ?", "Nombre d'emplacements", JOptionPane.QUESTION_MESSAGE);
            res = Integer.parseInt(value);
            eDAO.createLocations(res);

        });
        buttonPanel.add(launchAlgoButton);
        buttonPanel.add(createButton);
        panel.add(buttonPanel);
        panel.add(beforeLaunchPanel);
        panel.add(afterLaunchPanel);
        this.add(panel);
    }

    public void run() {
        this.setVisible(true);
    }
}
