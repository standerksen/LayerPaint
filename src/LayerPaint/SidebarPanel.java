package LayerPaint;

import javax.swing.*;
import java.awt.*;

public class SidebarPanel extends JToolBar {
    static final int MIN_STROKE = 1;
    static final int MAX_STROKE = 10;
    static final int INIT_STROKE = 1;

    private JButton movShapeButton, delShapeButton, rectShapeButton, elliShapeButton, lineShapeButton;
    private JSlider strokeSlider;

    public SidebarPanel(DrawPanel drawPanel) {
        this.setOrientation(JToolBar.VERTICAL);

        ImageIcon move = new ImageIcon(getClass().getResource("resources/ic_move.png"));
        ImageIcon delete = new ImageIcon(getClass().getResource("resources/ic_delete.png"));
        ImageIcon rectangle = new ImageIcon(getClass().getResource("resources/ic_rectangle.png"));
        ImageIcon ellipse = new ImageIcon(getClass().getResource("resources/ic_ellipse.png"));
        ImageIcon line = new ImageIcon(getClass().getResource("resources/ic_line.png"));

        movShapeButton = new JButton(move);
        delShapeButton = new JButton(delete);
        rectShapeButton = new JButton(rectangle);
        elliShapeButton = new JButton(ellipse);
        lineShapeButton = new JButton(line);

        movShapeButton.setActionCommand("Move");
        delShapeButton.setActionCommand("Delete");
        rectShapeButton.setActionCommand("Rectangle");
        elliShapeButton.setActionCommand("Ellipse");
        lineShapeButton.setActionCommand("Line");

        strokeSlider = new JSlider(JSlider.HORIZONTAL, MIN_STROKE, MAX_STROKE, INIT_STROKE);
        strokeSlider.setMajorTickSpacing(2);
        strokeSlider.setMinorTickSpacing(1);
        strokeSlider.setPaintTicks(true);
        strokeSlider.setPaintLabels(true);

        JButton[] jButtonArray = {movShapeButton, delShapeButton, lineShapeButton, rectShapeButton, elliShapeButton};

//        delShapeButton.addActionListener(new InputHandler(drawPanel, this));
//        movShapeButton.addActionListener(new InputHandler(drawPanel, this));
//        rectShapeButton.addActionListener(new InputHandler(drawPanel, this));
//        elliShapeButton.addActionListener(new InputHandler(drawPanel, this));
//        lineShapeButton.addActionListener(new InputHandler(drawPanel, this));

        strokeSlider.addChangeListener(new InputHandler(drawPanel, this));
        drawPanel.addMouseListener(new InputHandler(drawPanel, this));
        drawPanel.addMouseMotionListener(new InputHandler(drawPanel, this));

        for(JButton button : jButtonArray) {
            button.addActionListener(new InputHandler(drawPanel, this));
            add(button);
        }

        add(strokeSlider);
    }
}
