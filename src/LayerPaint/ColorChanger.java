package LayerPaint;

import java.awt.Color;
import javax.swing.JColorChooser;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Pieter
 */
class ColorChanger implements ChangeListener {

    public enum mode {
        FILL, STROKE
    };

    private final DrawPanel drawPanel;
    private final JColorChooser colorChooser;
    private final mode m;
    private final PreviewPanel colorPreviewPanel;

    public ColorChanger(DrawPanel drawPanel, PreviewPanel colorPreviewPanel, JColorChooser colorChooser, mode m) {
        this.drawPanel = drawPanel;
        this.colorChooser = colorChooser;
        this.colorPreviewPanel = colorPreviewPanel;
        this.m = m;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        Color color = colorChooser.getColor();
        if (m == mode.FILL) {
            drawPanel.setFillColor(color);
            colorPreviewPanel.setFillColor(color);
        } else if (m == mode.STROKE) {
            drawPanel.setStrokeColor(color);
            colorPreviewPanel.setStrokeColor(color);
        }
    }

}
