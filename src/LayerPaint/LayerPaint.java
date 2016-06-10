package LayerPaint;

import javax.swing.SwingUtilities;

public class LayerPaint {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Window window = new Window();
        });
    }
}
