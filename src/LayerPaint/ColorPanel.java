package LayerPaint;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.border.Border;
import javax.swing.colorchooser.AbstractColorChooserPanel;

public class ColorPanel extends AbstractColorChooserPanel implements ActionListener {

    @Override
    public void updateChooser() {
    }

    @Override
    protected void buildChooser() {
        setLayout(new GridLayout(2, 0));
        Border border = BorderFactory.createEmptyBorder(10,10,10,10);
        ButtonGroup colorButtons = new ButtonGroup();
        
        List<ColorName> colors = new ArrayList<>();
        colors.add(new ColorName("Red", Color.RED));
        colors.add(new ColorName("Blue", Color.BLUE));
        colors.add(new ColorName("Yellow", Color.YELLOW));
        
        colors.stream().map((colorName) -> new ColorButton(colorName.color, colorName.name, this, border)).map((colorButton) -> {
            colorButtons.add(colorButton);
            return colorButton;
        }).forEach((colorButton) -> {
            add(colorButton);
        });
    }

    @Override
    public String getDisplayName() {
        return "Colors";
    }

    @Override
    public Icon getSmallDisplayIcon() {
        return null;
    }

    @Override
    public Icon getLargeDisplayIcon() {
        return null;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Color newColor = ((ColorButton)e.getSource()).getColor();
        getColorSelectionModel().setSelectedColor(newColor);
    }

    private static class ColorName {
        
        public Color color;
        public String name;

        public ColorName(String n, Color c) {
            color = c;
            name = n;
        }
    }
    
}
