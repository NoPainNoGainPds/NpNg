package vue;

import javax.swing.*;

/**
 * Private Class for display a label
 *
 * @param <J>
 */
public class TextLabel<J extends JComponent> extends JPanel
{
    public J field;
    public JLabel label;

    public TextLabel(J field, JLabel label) {
        this.field = field;
        this.label = label;
        this.add(this.label);
        this.add(this.field);
    }
}
