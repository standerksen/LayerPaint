package LayerPaint;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Hashtable;
import javax.swing.BorderFactory;
import javax.swing.Box;
import static javax.swing.Box.createHorizontalGlue;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JToolBar;
import javax.swing.border.Border;
import javax.swing.colorchooser.AbstractColorChooserPanel;

public class ColorSidebarPanel extends JToolBar {

    static final int MIN_STROKE = 1;
    static final int MAX_STROKE = 5;
    static final int INIT_STROKE = 1;

    private final JSlider strokeSlider;
    private final PreviewPanel previewPanel;

    public JColorChooser fillColorChooser, strokeColorChooser;

    public ColorSidebarPanel(DrawPanel drawPanel) {
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.setOrientation(JToolBar.VERTICAL);
        this.setPreferredSize(new Dimension(125, 0));
        Border border = BorderFactory.createLineBorder(Color.black);

        AbstractColorChooserPanel[] tabs;
        Hashtable labelTable;

        strokeSlider = new JSlider(JSlider.HORIZONTAL, MIN_STROKE, MAX_STROKE, INIT_STROKE);
        strokeSlider.setMajorTickSpacing(2);
        strokeSlider.setMinorTickSpacing(1);
        labelTable = new Hashtable();
        labelTable.put(MIN_STROKE, new JLabel("1"));
        labelTable.put(MAX_STROKE, new JLabel("5"));
        strokeSlider.setLabelTable(labelTable);
        strokeSlider.setSnapToTicks(true);
        strokeSlider.setPaintTicks(true);
        strokeSlider.setPaintLabels(true);
        strokeSlider.setMaximumSize(new Dimension(100, 50));
        strokeSlider.setPreferredSize(new Dimension(100, 50));

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
        fillColorChooser.setMaximumSize(new Dimension(100, 50));
        fillColorChooser.setPreferredSize(new Dimension(100, 50));
        tabs = strokeColorChooser.getChooserPanels();
        for (AbstractColorChooserPanel tab : tabs) {
            strokeColorChooser.removeChooserPanel(tab);
        }
        strokeColorChooser.addChooserPanel(new ColorPanel());
        strokeColorChooser.setMaximumSize(new Dimension(100, 125));
        strokeColorChooser.setPreferredSize(new Dimension(100, 125));
        //strokeColorChooser.setSize(new Dimension(50,200));

        strokeSlider.addChangeListener(new InputHandler(drawPanel, previewPanel));
        fillColorChooser.getSelectionModel().addChangeListener(new ColorChanger(drawPanel, previewPanel, fillColorChooser, ColorChanger.mode.FILL));
        strokeColorChooser.getSelectionModel().addChangeListener(new ColorChanger(drawPanel, previewPanel, strokeColorChooser, ColorChanger.mode.STROKE));

        drawPanel.addMouseListener(new InputHandler(drawPanel, previewPanel));
        drawPanel.addMouseMotionListener(new InputHandler(drawPanel, previewPanel));

        JLabel fillLabel = new JLabel("Fill");
        JLabel strokeLabel = new JLabel("Stroke");
        JLabel sliderLabel = new JLabel("Stroke Size");

        add(fillLabel);
        add(fillColorChooser);
        add(strokeLabel);
        
        add(strokeColorChooser);
        add(sliderLabel);
        add(strokeSlider);
    }

}
