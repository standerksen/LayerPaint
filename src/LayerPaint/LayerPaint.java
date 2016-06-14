package LayerPaint;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class LayerPaint {

    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(() -> {
            Window window = new Window();
        });
    }
}
