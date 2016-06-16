package LayerPaint;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.*;

public class Window extends JFrame {

    public Window() {
        super();
        setTitle("LayerPaint");
        setSize(new Dimension(1280, 720));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        getContentPane().setLayout(new BorderLayout());

        DrawPanel dp = new DrawPanel();
        dp.addKeyListener(new InputHandler(dp));

        JToolBar sb = new SidebarPanel(dp);
        JToolBar csb = new ColorSidebarPanel(dp);

        getContentPane().add(dp, BorderLayout.CENTER);
        getContentPane().add(sb, BorderLayout.WEST);
        getContentPane().add(csb, BorderLayout.EAST);

        setVisible(true);
    }
}
