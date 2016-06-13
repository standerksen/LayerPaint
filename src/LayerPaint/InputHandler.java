package LayerPaint;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class InputHandler implements ActionListener, ChangeListener, MouseListener, MouseMotionListener {
    private final DrawPanel drawPanel;
    private final SidebarPanel sidebarPanel;
    private final PreviewPanel previewPanel;
    
    public InputHandler(DrawPanel drawPanel, PreviewPanel previewPanel, SidebarPanel sidebarPanel) {
        this.drawPanel = drawPanel;
        this.sidebarPanel = sidebarPanel;
        this.previewPanel = previewPanel;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand() != null)
            switch (e.getActionCommand()) {
            case "Move":
                this.drawPanel.setTool(ToolName.MOVE);
                break;
            case "Delete":
                this.drawPanel.setTool(ToolName.DELETE);
                break;
            case "Rectangle":
                this.drawPanel.setTool(ToolName.RECTANGLE);
                break;
            case "Ellipse":
                this.drawPanel.setTool(ToolName.ELLIPSE);
                break;
            case "Line":
                this.drawPanel.setTool(ToolName.LINE);
                break;
            case "Fill":
                this.drawPanel.setTool(ToolName.FILL);
                break;
            case "Image":
                this.drawPanel.setTool(ToolName.IMAGE);
                break;
        }
    }
    
    @Override
    public void mouseDragged(MouseEvent e){
        drawPanel.moveClick(e.getX(), e.getY());
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        drawPanel.click(e.getX(), e.getY());
    }

    @Override
    public void mousePressed(MouseEvent e) {
        drawPanel.startClick(e.getX(), e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        drawPanel.stopClick(e.getX(), e.getY());
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        drawPanel.mouse(e.getX(), e.getY());
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider)e.getSource();
        drawPanel.setStroke(source.getValue());
        previewPanel.setStroke(source.getValue());
    }
}
