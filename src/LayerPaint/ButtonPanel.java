package LayerPaint;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class ButtonPanel extends JPanel {

    static final int MIN_STROKE = 1;
    static final int MAX_STROKE = 10;
    static final int INIT_STROKE = 1;

    private JButton randomColorButton, movShapeButton,
            delShapeButton, rectShapeButton, elliShapeButton, lineShapeButton, 
            fillButton, imageButton;

    private JSlider strokeSlider;

    public ButtonPanel(DrawPanel drawPanel) {
        randomColorButton = new JButton("Change color");
        movShapeButton = new JButton("Move");
        delShapeButton = new JButton("Delete");
        rectShapeButton = new JButton("Rectangle");
        elliShapeButton = new JButton("Ellipse");
        lineShapeButton = new JButton("Line");
        strokeSlider = new JSlider(JSlider.HORIZONTAL, MIN_STROKE, MAX_STROKE, INIT_STROKE);
        fillButton = new JButton("Fill");
        imageButton = new JButton("Image");

        strokeSlider.setMajorTickSpacing(5);
        strokeSlider.setMinorTickSpacing(1);
        strokeSlider.setPaintTicks(true);
        strokeSlider.setPaintLabels(true);

        delShapeButton.addActionListener(new InputHandler(drawPanel, this));
        movShapeButton.addActionListener(new InputHandler(drawPanel, this));
        rectShapeButton.addActionListener(new InputHandler(drawPanel, this));
        elliShapeButton.addActionListener(new InputHandler(drawPanel, this));
        lineShapeButton.addActionListener(new InputHandler(drawPanel, this));
        fillButton.addActionListener(new InputHandler(drawPanel, this));
        imageButton.addActionListener(new InputHandler(drawPanel, this));
        strokeSlider.addChangeListener(new InputHandler(drawPanel, this));
        drawPanel.addMouseListener(new InputHandler(drawPanel, this));
        drawPanel.addMouseMotionListener(new InputHandler(drawPanel, this));

        add(delShapeButton);
        add(movShapeButton);
        add(rectShapeButton);
        add(elliShapeButton);
        add(lineShapeButton);
        add(fillButton);
        add(imageButton);
        add(strokeSlider);
    }

    public void changeText(Color c) {
        int red = c.getRed();
        int green = c.getGreen();
        int blue = c.getBlue();
        String text = String.format("Color: #%02X%02X%02X", red, green, blue);

        this.randomColorButton.setText(text);
    }
}
