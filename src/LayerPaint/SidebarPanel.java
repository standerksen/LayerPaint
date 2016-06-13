package LayerPaint;

import LayerPaint.ColorChanger.mode;
import javax.swing.*;
import javax.swing.colorchooser.AbstractColorChooserPanel;

public class SidebarPanel extends JToolBar {

    static final int MIN_STROKE = 1;
    static final int MAX_STROKE = 10;
    static final int INIT_STROKE = 1;

    private final JButton movShapeButton, delShapeButton, rectShapeButton, elliShapeButton, lineShapeButton, textButton;
    private final JSlider strokeSlider;
    public JColorChooser fillColorChooser, strokeColorChooser;
    private final PreviewPanel previewPanel;

    public SidebarPanel(DrawPanel drawPanel) {
        AbstractColorChooserPanel[] tabs;

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
        textButton = new JButton("Text");

        movShapeButton.setActionCommand("Move");
        delShapeButton.setActionCommand("Delete");
        rectShapeButton.setActionCommand("Rectangle");
        elliShapeButton.setActionCommand("Ellipse");
        lineShapeButton.setActionCommand("Line");
        textButton.setActionCommand("Text");

        strokeSlider = new JSlider(JSlider.HORIZONTAL, MIN_STROKE, MAX_STROKE, INIT_STROKE);
        strokeSlider.setMajorTickSpacing(2);
        strokeSlider.setMinorTickSpacing(1);
        strokeSlider.setPaintTicks(true);
        strokeSlider.setPaintLabels(true);

        fillColorChooser = new JColorChooser(drawPanel.getFillColor());
        strokeColorChooser = new JColorChooser(drawPanel.getStrokeColor());

        previewPanel = new PreviewPanel(fillColorChooser, strokeColorChooser);
        fillColorChooser.setPreviewPanel(new JPanel());
        strokeColorChooser.setPreviewPanel(previewPanel);

        tabs = fillColorChooser.getChooserPanels();
        for (AbstractColorChooserPanel tab : tabs) {
            String name = tab.getClass().getSimpleName();
            if (!"DefaultSwatchChooserPanel".equals(name)) {
                fillColorChooser.removeChooserPanel(tab);
            }
        }

        tabs = strokeColorChooser.getChooserPanels();
        for (AbstractColorChooserPanel tab : tabs) {
            String name = tab.getClass().getSimpleName();
            if (!"DefaultSwatchChooserPanel".equals(name)) {
                strokeColorChooser.removeChooserPanel(tab);
            }
        }

        JButton[] jButtonArray = {movShapeButton, delShapeButton, lineShapeButton, rectShapeButton, elliShapeButton, textButton};

//        delShapeButton.addActionListener(new InputHandler(drawPanel, this));
//        movShapeButton.addActionListener(new InputHandler(drawPanel, this));
//        rectShapeButton.addActionListener(new InputHandler(drawPanel, this));
//        elliShapeButton.addActionListener(new InputHandler(drawPanel, this));
//        lineShapeButton.addActionListener(new InputHandler(drawPanel, this));
        strokeSlider.addChangeListener(new InputHandler(drawPanel, previewPanel));
        fillColorChooser.getSelectionModel().addChangeListener(new ColorChanger(drawPanel, previewPanel, fillColorChooser, mode.FILL));
        strokeColorChooser.getSelectionModel().addChangeListener(new ColorChanger(drawPanel, previewPanel, strokeColorChooser, mode.STROKE));
        drawPanel.addMouseListener(new InputHandler(drawPanel, previewPanel));
        drawPanel.addMouseMotionListener(new InputHandler(drawPanel, previewPanel));

        for (JButton button : jButtonArray) {
            button.addActionListener(new InputHandler(drawPanel, previewPanel));
            add(button);
        }

        add(strokeSlider);
        add(fillColorChooser);
        add(strokeColorChooser);
    }
}
