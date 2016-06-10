package LayerPaint;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.UIManager;

public class Window extends JFrame {
    
    
    public Window() {
        super();
        try {
            UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        setTitle("Superclicker");
        setSize(new Dimension(500, 500));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());
        
        DrawPanel rp = new DrawPanel();
        ButtonPanel bp = new ButtonPanel(rp);
        InputPanel ip = new InputPanel(rp);
        getContentPane().add(rp, BorderLayout.CENTER);
        getContentPane().add(bp, BorderLayout.NORTH);
        getContentPane().add(ip, BorderLayout.SOUTH);
        
        setVisible(true);
    }
}
