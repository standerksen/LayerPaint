
package superclicker;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class InputPanel extends JPanel {
    private JTextField colorInput;
    private JButton setButton;
    private JTextArea hash;
    
    public InputPanel(DrawPanel drawPanel) {
        colorInput = new JTextField("000000", 6);
        hash = new JTextArea("#");
        setButton = new JButton("Set!");
        setButton.addActionListener(new SetHandler(colorInput, drawPanel));
        
        add(hash);
        add(colorInput);
        add(setButton);
    }
}
