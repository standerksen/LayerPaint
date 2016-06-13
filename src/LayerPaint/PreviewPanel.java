/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LayerPaint;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Stroke;
import javax.swing.JColorChooser;
import javax.swing.JComponent;

/**
 *
 * @author Pieter
 */
class PreviewPanel extends JComponent {

    private Color fillColor, strokeColor;
    private int stroke = 1;

    public PreviewPanel(JColorChooser fillChooser, JColorChooser strokeChooser) {
        fillColor = fillChooser.getColor();
        strokeColor = strokeChooser.getColor();
        setPreferredSize(new Dimension(50, 50));
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(strokeColor);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(fillColor);
        g.fillRect(stroke, stroke, getWidth() - (int) stroke * 2, getHeight() - stroke * 2);
    }

    public void setFillColor(Color color) {
        System.out.println(color);
        fillColor = color;
        repaint();
    }

    public void setStrokeColor(Color color) {
        System.out.println(color);
        strokeColor = color;
        repaint();
    }

    void setStroke(int s) {
        stroke = s;
        repaint();
    }

}
