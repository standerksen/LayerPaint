package LayerPaint;

import javax.swing.SwingUtilities;

public class Colorclicker {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new _Window_bu();
            }
        });
    }
}
