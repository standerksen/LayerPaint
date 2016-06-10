package LayerPaint;

import javax.swing.SwingUtilities;

public class LayerPaint {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Window();
            }
        });
    }
}
