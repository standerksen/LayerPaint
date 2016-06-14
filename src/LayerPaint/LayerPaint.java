package LayerPaint;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class LayerPaint {

    public static void main(String[] args) {
        
        try {
            // Set System L&F
        UIManager.setLookAndFeel(
            UIManager.getSystemLookAndFeelClassName());
        } 
        catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
           System.out.println("Failed to load LAF");
        }
        
        SwingUtilities.invokeLater(() -> {
            Window window = new Window();
        });
    }
}
