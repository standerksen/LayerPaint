package LayerPaint;

import javax.swing.*;

public class SidebarPanel extends JToolBar {

    private final JToggleButton movShapeButton, delShapeButton, rectShapeButton, elliShapeButton, lineShapeButton, textButton, fileButton;

    public SidebarPanel(DrawPanel drawPanel) {

        this.setOrientation(JToolBar.VERTICAL);

        ImageIcon move = new ImageIcon(getClass().getResource("resources/ic_move.png"));
        ImageIcon moveSelected = new ImageIcon(getClass().getResource("resources/ic_move_selected.png"));
        ImageIcon delete = new ImageIcon(getClass().getResource("resources/ic_delete.png"));
        ImageIcon deleteSelected = new ImageIcon(getClass().getResource("resources/ic_delete_selected.png"));
        ImageIcon rectangle = new ImageIcon(getClass().getResource("resources/ic_rectangle.png"));
        ImageIcon rectangleSelected = new ImageIcon(getClass().getResource("resources/ic_rectangle_selected.png"));
        ImageIcon ellipse = new ImageIcon(getClass().getResource("resources/ic_ellipse.png"));
        ImageIcon ellipseSelected = new ImageIcon(getClass().getResource("resources/ic_ellipse_selected.png"));
        ImageIcon line = new ImageIcon(getClass().getResource("resources/ic_line.png"));
        ImageIcon lineSelected = new ImageIcon(getClass().getResource("resources/ic_line_selected.png"));
        ImageIcon text = new ImageIcon(getClass().getResource("resources/ic_text.png"));
        ImageIcon textSelected = new ImageIcon(getClass().getResource("resources/ic_text_selected.png"));
        ImageIcon file = new ImageIcon(getClass().getResource("resources/ic_file.png"));
        ImageIcon fileSelected = new ImageIcon(getClass().getResource("resources/ic_file_selected.png"));

        movShapeButton = new JToggleButton(move);
        movShapeButton.setSelectedIcon(moveSelected);
        delShapeButton = new JToggleButton(delete);
        delShapeButton.setSelectedIcon(deleteSelected);
        rectShapeButton = new JToggleButton(rectangle);
        rectShapeButton.setSelectedIcon(rectangleSelected);
        elliShapeButton = new JToggleButton(ellipse);
        elliShapeButton.setSelectedIcon(ellipseSelected);
        lineShapeButton = new JToggleButton(line);
        lineShapeButton.setSelectedIcon(lineSelected);
        textButton = new JToggleButton(text);
        textButton.setSelectedIcon(textSelected);
        fileButton = new JToggleButton(file);
        fileButton.setSelectedIcon(fileSelected);

        movShapeButton.setActionCommand("Move");
        delShapeButton.setActionCommand("Delete");
        rectShapeButton.setActionCommand("Rectangle");
        elliShapeButton.setActionCommand("Ellipse");
        lineShapeButton.setActionCommand("Line");
        textButton.setActionCommand("Text");
        fileButton.setActionCommand("File");

        JToggleButton[] jButtonArray = {fileButton, movShapeButton, delShapeButton, lineShapeButton, rectShapeButton, elliShapeButton, textButton, fileButton};

        for (JToggleButton button : jButtonArray) {
            button.addActionListener(new InputHandler(drawPanel, jButtonArray));
            add(button);
        }
    }
}
