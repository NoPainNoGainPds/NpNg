package R3;

import org.apache.log4j.Logger;
import utils.daoUtils.BoutiqueDAO;
import utils.daoUtils.EmplacementDAO;

import javax.swing.*;
import java.awt.*;

public class AlgorithmView extends JPanel {
    private JButton launchAlgoButton;
    Logger logger = Logger.getLogger(AlgorithmView.class);

    public AlgorithmView(Algorithm algo) {
        JPanel panel = new JPanel();
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
            algo.assignLocationsToStores();
            JOptionPane jop = new JOptionPane();
            jop.showMessageDialog(this, "Algorithm successfully executed !");
        });
        unlinkButton.addActionListener((event) -> {
            logger.info("Unlink stores and locations");
            algo.unassignAllLocations();
            algo.unlocateAllStores();
        });
        createButton.addActionListener((event) -> {
            logger.info("Populating database");
            JOptionPane jop = new JOptionPane();
            String value = jop.showInputDialog(this,"How much stores and locations you want ?", "Question", JOptionPane.QUESTION_MESSAGE);
            int res = Integer.parseInt(value);
            algo.createLocations(res);
            algo.createStores(res);
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
