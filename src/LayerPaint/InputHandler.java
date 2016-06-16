package LayerPaint;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.AbstractButton;
import javax.swing.JSlider;
import javax.swing.JToggleButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class InputHandler implements ActionListener, ChangeListener, MouseListener, MouseMotionListener, KeyListener {

    private final DrawPanel drawPanel;
    private final PreviewPanel previewPanel;
    private final JToggleButton[] buttons;
    private boolean backspace;

    public InputHandler(DrawPanel drawPanel) {
        this.drawPanel = drawPanel;
        previewPanel = null;
        buttons = null;
    }

    protected InputHandler(DrawPanel drawPanel, PreviewPanel previewPanel) {
        this.drawPanel = drawPanel;
        this.previewPanel = previewPanel;
        buttons = null;
    }

    InputHandler(DrawPanel drawPanel, JToggleButton[] jButtonArray) {
        this.drawPanel = drawPanel;
        previewPanel = null;
        buttons = jButtonArray;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() != null) {
            for (JToggleButton button : buttons) {
                button.setSelected(false);
            }
            ((AbstractButton) e.getSource()).setSelected(true);
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
                case "File":
                    this.drawPanel.setTool(ToolName.IMAGE);
                    break;
                case "Text":
                    this.drawPanel.setTool(ToolName.TEXT);
                    break;
                default:
                    this.drawPanel.setTool(null);
                    break;
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        drawPanel.moveClick(e.getX(), e.getY());
        this.drawPanel.requestFocusInWindow();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        drawPanel.click(e.getX(), e.getY());
        this.drawPanel.requestFocusInWindow();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        drawPanel.startClick(e.getX(), e.getY());
        this.drawPanel.requestFocusInWindow();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        drawPanel.stopClick(e.getX(), e.getY());
        this.drawPanel.requestFocusInWindow();
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
        JSlider source = (JSlider) e.getSource();
        drawPanel.setStroke(source.getValue());
        previewPanel.setStroke(source.getValue());
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (backspace) {
            drawPanel.backspace();
        } else {
            drawPanel.type(e.getKeyChar());
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
            backspace = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_BACK_SPACE:
                backspace = false;
                break;
            case KeyEvent.VK_DELETE:
                drawPanel.delete();
                break;
            case KeyEvent.VK_PAGE_UP:
                drawPanel.pageUp();
                break;
            case KeyEvent.VK_PAGE_DOWN:
                drawPanel.pageDown();
                break;
            default:
                break;
        }
    }
}
