/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LayerPaint;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import javax.swing.border.Border;

/**
 *
 * @author pieter
 */
class ColorButton extends JButton {
    private Color color;
    public ColorButton(Color c, String n, ColorPanel p, Border b) {
        setActionCommand(n);
        addActionListener(p);
        this.setToolTipText(n);
        this.setForeground(c);
        setBorder(b);
        setBackground(c);
        setForeground (c);
        color = c;
    }

    Color getColor() {
        return color;
    }
    
}
