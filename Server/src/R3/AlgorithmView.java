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
        panel.setLayout(new FlowLayout());
        launchAlgoButton = new JButton("Launch Assignation Algorithm");
        launchAlgoButton.addActionListener((event) -> {
            logger.info("Launching assignation algorithm");
            System.out.println("Launching assignation algorithm...");
            algo.assignLocationsToStores();
        });
        panel.add(launchAlgoButton);
        this.add(panel);
    }

    public void run() {
        this.setVisible(true);
    }
}
