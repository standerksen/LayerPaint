package superclicker;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;

public class SetHandler implements ActionListener {
    private JTextField colorInput;
    private DrawPanel drawPanel;
    
    public SetHandler(JTextField colorInput, DrawPanel drawPanel) {
        this.colorInput = colorInput;
        this.drawPanel = drawPanel;
    }
    
    public void actionPerformed(ActionEvent e) {
        System.out.println(e);
        String hexColor = colorInput.getText();
        if(hexColor.length() == 6) {
            this.drawPanel.repaint();
            this.colorInput.setBackground(Color.WHITE);
        } else {
            this.colorInput.setBackground(Color.RED);
        }
        this.drawPanel.setFillColor(new Color(Integer.parseInt(hexColor, 16)));
    }
}
