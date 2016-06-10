package LayerPaint;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import javax.swing.JPanel;

public final class DrawPanel extends JPanel {

    private ToolName tool = ToolName.RECTANGLE;
    private final ArrayList<Drawable> shapesList;
    private final Random rnd;
    private int i = 0, startx, starty, lastx, lasty;
    private BasicStroke stroke;
    private Color fillColor = new Color(0,0,0, 0);
    private Drawable moveShape;
    
    public DrawPanel() {
        super();
        this.rnd = new Random();
        this.shapesList = new ArrayList<>();
        this.setStroke(1);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        shapesList.stream().forEach((s) -> {
            s.draw(g2d);
        });
    }
    
    public void setFillColor(Color color){
        this.fillColor = color;
    }
    
    public void setStroke(int s) {
        stroke = new BasicStroke((float) s, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
    }
    
    public void delRandomShape(){
        int remove;
        if(shapesList.size() > 0) {
            if(shapesList.size() == 1){
                remove = 0;
            } else {
                remove = rnd.nextInt(shapesList.size() - 1);
            }
            if(remove < i)
                i--;
            shapesList.remove(remove);
            repaint();
        }
    }
    
    public void setTool(ToolName tool){
        this.tool = tool;
    }
    
    private int shapeAtClick(int x, int y){
        int i = 0;
        int shape = -1;
        
        for(Drawable s: shapesList){
            if(s.contains(x, y)){
                shape = i;
            }
            i++;
        }
        return shape;
    }
    
    public void click(int x, int y){
        if(tool == ToolName.DELETE){
            int shape = shapeAtClick(x, y);
            if(shape > -1){
                shapesList.remove(shape);
            }
            repaint();
        }
    }
    private int clamp(int x, int min, int max){
        x = Math.max(min, Math.min(max, x));
        return x;
    }
    
    public void moveClick(int x, int y)
    {
        x = clamp(x, (int) stroke.getLineWidth() - 1, this.getSize().width - (int) stroke.getLineWidth());
        y = clamp(y, (int) stroke.getLineWidth() - 1, this.getSize().height - (int) stroke.getLineWidth());
        
        if(tool != ToolName.MOVE && tool != ToolName.DELETE){
            Drawable shape = shapesList.get(shapesList.size() - 1);
            shape.setCoords(new Tuple4d(startx, starty, x, y));
            shapesList.set(shapesList.size() - 1, shape);
        } else if(tool == ToolName.MOVE){
            if(moveShape != null){
                if(lastx + lasty < 0){
                    lastx = startx;
                    lasty = starty;
                }
                int changex = lastx - x;
                int changey = lasty - y;
                Tuple4d oldCoords = moveShape.getCoords();
                int x1 = clamp((int) oldCoords.x - changex, 0, this.getSize().width - 1);
                int y1 = clamp((int) oldCoords.y - changey, 0, this.getSize().height - 1);
                int x2 = clamp((int) oldCoords.z - changex, 0, this.getSize().width - 1);
                int y2 = clamp((int) oldCoords.w - changey, 0, this.getSize().height - 1);
                
                if(x1 == (int) oldCoords.x - changex &&
                        y1 == (int) oldCoords.y - changey &&
                        x2 == oldCoords.z - changex &&
                        y2 == (int) oldCoords.w - changey){
                    moveShape.setCoords(new Tuple4d(x1, y1, x2, y2));
                }
            }
        }
        lastx = x;
        lasty = y;
        repaint();
    }
    
    public void startClick(int x, int y){
        startx = x;
        starty = y;
        Drawable shape = null;
        if(tool == ToolName.RECTANGLE || tool == ToolName.ELLIPSE ||
                tool == ToolName.LINE){
            switch(tool){
                case RECTANGLE:
                    shape = new MyRectangle(x, y, x, y, stroke, fillColor);
                    break;
                case ELLIPSE:
                    shape = new MyEllipse(x, y, x, y, stroke, fillColor);
                    break;
                case LINE:
                    shape = new MyLine(x, y, x, y, stroke);
                    break;
            }
            shapesList.add(shape); 
        } else if(tool == ToolName.MOVE){
            if(shapeAtClick(x, y) >= 0){
                moveShape = shapesList.get(shapeAtClick(x, y));
            }
        }
        repaint();
    }
    
    public void stopClick(int x, int y){
        startx = 0;
        starty = 0;
        if(tool != ToolName.DELETE){
            if( x == startx && y == starty){
                shapesList.remove(shapesList.size() - 1);
            }
        }
        if(tool == ToolName.MOVE){
            moveShape = null;
        }
        lastx = -1;
        lasty = -1;
    }
}
