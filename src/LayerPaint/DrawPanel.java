package LayerPaint;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
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
    private int startx, starty, lastx, lasty, movePoint;
    private Drawable selected;
    private BasicStroke stroke;
    private Color fillColor = new Color(0,0,0, 0);
    private Drawable moveShape;
    
    public DrawPanel() {
        super();
        this.setBackground(Color.WHITE);
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
    
    public void setTool(ToolName tool){
        this.tool = tool;
    }
    
    public void mouse(double x, double y){
        if(selected instanceof Drawable){
            SelectBox s = selected.getBox();
            movePoint = s.contains(x,y);
            switch (movePoint) {
                case 1:
                    setCursor(new Cursor(Cursor.NW_RESIZE_CURSOR));
                    break;
                case 2:
                    setCursor(new Cursor(Cursor.N_RESIZE_CURSOR));
                    break;
                case 3:
                    setCursor(new Cursor(Cursor.NE_RESIZE_CURSOR));
                    break;
                case 4:
                    setCursor(new Cursor(Cursor.W_RESIZE_CURSOR));
                    break;
                case 5:
                    setCursor(new Cursor(Cursor.E_RESIZE_CURSOR));
                    break;
                case 6:
                    setCursor(new Cursor(Cursor.SW_RESIZE_CURSOR));
                    break;
                case 7:
                    setCursor(new Cursor(Cursor.S_RESIZE_CURSOR));
                    break;
                case 8:
                    setCursor(new Cursor(Cursor.SE_RESIZE_CURSOR));
                    break;
                case 9:
                    setCursor(new Cursor(Cursor.MOVE_CURSOR));
                    break;
                default:
                    setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                    break;
            }
        } else {
            setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
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
        System.out.println(tool);
        if(tool == ToolName.DELETE){
            int shape = shapeAtClick(x, y);
            if(shape > -1){
                shapesList.remove(shape);
            }
        } else if (tool == ToolName.MOVE){
            shapesList.stream().forEach((s) -> {
                s.select(false);
                selected = null;
            });
            if(shapeAtClick(x, y) >= 0){
                shapesList.get(shapeAtClick(x, y)).select(true);
                selected = shapesList.get(shapeAtClick(x, y));
            }
        }
        repaint();
    }
    private int clamp(int x, int min, int max){
        x = Math.max(min, Math.min(max, x));
        return x;
    }
    
    public void moveClick(int x, int y)
    {
        int x1, y1, x2, y2, changex, changey;
        BasicStroke curStroke;
        Tuple4d oldCoords;
        Drawable shape;
        
        if(selected != null){
            curStroke = (BasicStroke) selected.stroke();
        } else {
            curStroke = this.stroke;
        }
            x = clamp(x, (int) stroke.getLineWidth() - 1, this.getSize().width - (int) curStroke.getLineWidth());
            y = clamp(y, (int) stroke.getLineWidth() - 1, this.getSize().height - (int) curStroke.getLineWidth());
        
        if(tool == ToolName.ELLIPSE || tool == ToolName.RECTANGLE || tool == ToolName.LINE || tool == ToolName.IMAGE){
            shape = shapesList.get(shapesList.size() - 1);
            shape.setCoords(new Tuple4d(startx, starty, x, y));
            shapesList.set(shapesList.size() - 1, shape);
        } else if(tool == ToolName.MOVE){
            if(selected != null){
                if(lastx + lasty < 0){
                    lastx = startx;
                    lasty = starty;
                }
                changex = lastx - x;
                changey = lasty - y;
                oldCoords = selected.getCoords();
                
                switch (movePoint) {
                    case 9:
                        x1 = clamp((int) oldCoords.x - changex, 0, this.getSize().width - 1);
                        y1 = clamp((int) oldCoords.y - changey, 0, this.getSize().height - 1);
                        x2 = clamp((int) oldCoords.z - changex, 0, this.getSize().width - 1);
                        y2 = clamp((int) oldCoords.w - changey, 0, this.getSize().height - 1);
                        if((int) oldCoords.x - changex < 0){
                            x2 = (int) oldCoords.z;
                        } else if((int) oldCoords.y - changey < 0){
                            y2 = (int) oldCoords.w;
                        } else if((int) oldCoords.z - changex > this.getSize().width - 1){
                            x1 = (int) oldCoords.x;
                        } else if((int) oldCoords.w - changey > this.getSize().height - 1){
                            y1 = (int) oldCoords.y;
                        }   break;
                    case 1:
                        x1 = clamp((int) oldCoords.x - changex, 0, this.getSize().width - 1);
                        y1 = clamp((int) oldCoords.y - changey, 0, this.getSize().height - 1);
                        x2 = (int) oldCoords.z;
                        y2 = (int) oldCoords.w;
                        break;
                    case 2:
                        x1 = (int) oldCoords.x ;
                        y1 = clamp((int) oldCoords.y - changey, 0, this.getSize().height - 1);
                        x2 =(int) oldCoords.z ;
                        y2 = (int) oldCoords.w;
                        break;
                    case 3:
                        x1 = (int) oldCoords.x;
                        y1 = clamp((int) oldCoords.y - changey, 0, this.getSize().height - 1);
                        x2 = clamp((int) oldCoords.z - changex, 0, this.getSize().width - 1);
                        y2 = (int) oldCoords.w;
                        break;
                    case 4:
                        x1 = clamp((int) oldCoords.x - changex, 0, this.getSize().width - 1);
                        y1 = (int) oldCoords.y;
                        x2 = (int) oldCoords.z;
                        y2 = (int) oldCoords.w;
                        break;
                    case 5:
                        x1 = (int) oldCoords.x;
                        y1 = (int) oldCoords.y;
                        x2 = clamp((int) oldCoords.z - changex, 0, this.getSize().width - 1);
                        y2 = (int) oldCoords.w;
                        break;
                    case 6:
                        x1 = clamp((int) oldCoords.x - changex, 0, this.getSize().width - 1);
                        y1 = (int) oldCoords.y;
                        x2 = (int) oldCoords.z;
                        y2 = clamp((int) oldCoords.w - changey, 0, this.getSize().height - 1);
                        break;
                    case 7:
                        x1 = (int) oldCoords.x;
                        y1 = (int) oldCoords.y;
                        x2 = (int) oldCoords.z;
                        y2 = clamp((int) oldCoords.w - changey, 0, this.getSize().height - 1);
                        break;
                    case 8:
                        x1 = (int) oldCoords.x;
                        y1 = (int) oldCoords.y;
                        x2 = clamp((int) oldCoords.z - changex, 0, this.getSize().width - 1);
                        y2 = clamp((int) oldCoords.w - changey, 0, this.getSize().height - 1);
                        break;
                    default:
                        x1 = (int) oldCoords.x;
                        y1 = (int) oldCoords.y;
                        x2 = (int) oldCoords.z;
                        y2 = (int) oldCoords.w;
                        break;
                }
                
                selected.setCoords(new Tuple4d(x1, y1, x2, y2));
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
                tool == ToolName.LINE || tool == ToolName.IMAGE){
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
                case IMAGE:
                    shape = new MyImage(x, y, x, y);
                    break;
            }
            shapesList.add(shape); 
        } else if(tool == ToolName.MOVE){
            if(movePoint == 0) {
                shapesList.stream().forEach((s) -> {
                    s.select(false);
                });
                if(shapeAtClick(x, y) >= 0 || movePoint > 0){
                    shapesList.get(shapeAtClick(x, y)).select(true);
                    selected = shapesList.get(shapeAtClick(x, y));
                }
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
        shapesList.stream().forEach((s) -> {
            s.select(false);
        });
        
        lastx = -1;
        lasty = -1;
    }
}
