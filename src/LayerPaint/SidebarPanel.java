package LayerPaint;

import LayerPaint.ColorChanger.mode;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Hashtable;
import javax.swing.*;
import javax.swing.colorchooser.AbstractColorChooserPanel;

public class SidebarPanel extends JToolBar {

    static final int MIN_STROKE = 1;
    static final int MAX_STROKE = 10;
    static final int INIT_STROKE = 1;

    private final JButton movShapeButton, delShapeButton, rectShapeButton, elliShapeButton, lineShapeButton, textButton, fileButton;
    private final JSlider strokeSlider;
    public JColorChooser fillColorChooser, strokeColorChooser;
    private final PreviewPanel previewPanel;

    public SidebarPanel(DrawPanel drawPanel) {
        AbstractColorChooserPanel[] tabs;
        Hashtable labelTable;

        this.setOrientation(JToolBar.VERTICAL);

        ImageIcon move = new ImageIcon(getClass().getResource("resources/ic_move.png"));
        ImageIcon delete = new ImageIcon(getClass().getResource("resources/ic_delete.png"));
        ImageIcon rectangle = new ImageIcon(getClass().getResource("resources/ic_rectangle.png"));
        ImageIcon ellipse = new ImageIcon(getClass().getResource("resources/ic_ellipse.png"));
        ImageIcon line = new ImageIcon(getClass().getResource("resources/ic_line.png"));
        ImageIcon text = new ImageIcon(getClass().getResource("resources/ic_text.png"));
        ImageIcon file = new ImageIcon(getClass().getResource("resources/ic_file.png"));

        movShapeButton = new JButton(move);
        delShapeButton = new JButton(delete);
        rectShapeButton = new JButton(rectangle);
        elliShapeButton = new JButton(ellipse);
        lineShapeButton = new JButton(line);
        textButton = new JButton(text);
        fileButton = new JButton(file);

        movShapeButton.setActionCommand("Move");
        delShapeButton.setActionCommand("Delete");
        rectShapeButton.setActionCommand("Rectangle");
        elliShapeButton.setActionCommand("Ellipse");
        lineShapeButton.setActionCommand("Line");
        textButton.setActionCommand("Text");
        fileButton.setActionCommand("File");

        strokeSlider = new JSlider(JSlider.VERTICAL, MIN_STROKE, MAX_STROKE, INIT_STROKE);
        strokeSlider.setMajorTickSpacing(2);
        strokeSlider.setMinorTickSpacing(1);
        labelTable = new Hashtable();
        Object put = labelTable.put(MIN_STROKE, new JLabel("1") );
        labelTable.put(MAX_STROKE, new JLabel("10") );
        strokeSlider.setLabelTable( labelTable );

        strokeSlider.setInverted(true);
        strokeSlider.setSnapToTicks(true);
        strokeSlider.setPaintTicks(true);
        strokeSlider.setPaintLabels(true);

        fillColorChooser = new JColorChooser(drawPanel.getFillColor());
        strokeColorChooser = new JColorChooser(drawPanel.getStrokeColor());

        previewPanel = new PreviewPanel(fillColorChooser, strokeColorChooser);
        fillColorChooser.setPreviewPanel(new JPanel());
        strokeColorChooser.setPreviewPanel(previewPanel);

        tabs = fillColorChooser.getChooserPanels();
        for (AbstractColorChooserPanel tab : tabs) {
            fillColorChooser.removeChooserPanel(tab);
        }
        fillColorChooser.addChooserPanel(new ColorPanel());
        fillColorChooser.setBorder(BorderFactory.createLineBorder(Color.black));
        fillColorChooser.setMaximumSize(new Dimension(50,100));
        fillColorChooser.setSize(new Dimension(50,100));
        tabs = strokeColorChooser.getChooserPanels();
        for (AbstractColorChooserPanel tab : tabs) {
            String name = tab.getClass().getSimpleName();
            if (!"DefaultSwatchChooserPanel".equals(name)) {
                strokeColorChooser.removeChooserPanel(tab);
            }
        }

        JButton[] jButtonArray = {fileButton, movShapeButton, delShapeButton, lineShapeButton, rectShapeButton, elliShapeButton, textButton, fileButton};

        strokeSlider.addChangeListener(new InputHandler(drawPanel, previewPanel));
        fillColorChooser.getSelectionModel().addChangeListener(new ColorChanger(drawPanel, previewPanel, fillColorChooser, mode.FILL));
        strokeColorChooser.getSelectionModel().addChangeListener(new ColorChanger(drawPanel, previewPanel, strokeColorChooser, mode.STROKE));
        drawPanel.addMouseListener(new InputHandler(drawPanel, previewPanel));
        drawPanel.addMouseMotionListener(new InputHandler(drawPanel, previewPanel));

        for (JButton button : jButtonArray) {
            button.addActionListener(new InputHandler(drawPanel, previewPanel));
            add(button);
        }
        
        add(fillColorChooser);
        //add(strokeColorChooser);
        //add(strokeSlider);
    }
}
