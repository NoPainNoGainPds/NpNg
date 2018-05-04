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
        buttonPanel.setBorder(BorderFactory.createTitledBorder("Launching"));
        JPanel beforeLaunchPanel = new JPanel();
        beforeLaunchPanel.setBorder(BorderFactory.createTitledBorder("Before lauching the algorithm"));
        JPanel afterLaunchPanel = new JPanel();
        afterLaunchPanel.setBorder(BorderFactory.createTitledBorder("After launching the algorithm "));
        launchAlgoButton = new JButton("Launch Assignation Algorithm");
        launchAlgoButton.addActionListener((event) -> {
            logger.info("Launching assignation algorithm");
            System.out.println("Launching assignation algorithm...");
            algo.assignLocationsToStores();
        });
        buttonPanel.add(launchAlgoButton);
        panel.add(buttonPanel);
        panel.add(beforeLaunchPanel);
        panel.add(afterLaunchPanel);
        this.add(panel);
    }

    public void run() {
        this.setVisible(true);
    }
}
