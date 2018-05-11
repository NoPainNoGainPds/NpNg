package vue;

import R3.Algorithm;
import org.apache.log4j.Logger;
import utils.Constants;
import utils.daoUtils.BoutiqueDAO;
import utils.daoUtils.EmplacementDAO;

import javax.swing.*;

public class AlgorithmView extends JPanel {
    private JButton launchAlgoButton;
    private EmplacementDAO eDAO;
    private BoutiqueDAO bDAO;
    Logger logger = Logger.getLogger(this.getClass());

    public AlgorithmView() {
        JPanel panel = new JPanel();
        this.eDAO = new EmplacementDAO();
        this.bDAO = new BoutiqueDAO();
        panel.setLayout(new BoxLayout(panel,BoxLayout.PAGE_AXIS));
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createTitledBorder("Launching / Unlink / Create locations and stores"));
        JPanel beforeLaunchPanel = new JPanel();
        beforeLaunchPanel.setBorder(BorderFactory.createTitledBorder("Before lauching the algorithm"));
        JPanel afterLaunchPanel = new JPanel();
        afterLaunchPanel.setBorder(BorderFactory.createTitledBorder("After launching the algorithm "));
        launchAlgoButton = new JButton("Launch Assignation Algorithm");
        JButton unlinkButton = new JButton("Unlink stores and locations");
        JButton createButton = new JButton("Populate database");
        launchAlgoButton.addActionListener((event) -> {
            logger.info("Launching assignation algorithm");
            System.out.println("Launching assignation algorithm...");
            this.eDAO.assignLocationsToStores();
            JOptionPane jop = new JOptionPane();
            jop.showMessageDialog(this, "Algorithm successfully executed !");
        });
        unlinkButton.addActionListener((event) -> {
            logger.info("Unlink stores and locations");
            System.out.println("Unlink");
            eDAO.unassignAllLocations();
            System.out.println("fin de unassign");
            bDAO.unlocateAllStores();
        });
        createButton.addActionListener((event) -> {
            logger.info("Populating database");
            JOptionPane jop = new JOptionPane();
            String value = jop.showInputDialog(this,"How much stores you want ?", "Nombre de boutiques", JOptionPane.QUESTION_MESSAGE);
            int res = Integer.parseInt(value);
            //algo.createStores(res);
            value = jop.showInputDialog(this,"How much locations you want ?", "Nombre d'emplacements", JOptionPane.QUESTION_MESSAGE);
            res = Integer.parseInt(value);
            //algo.createLocations(res);

        });
        buttonPanel.add(launchAlgoButton);
        buttonPanel.add(unlinkButton);
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
