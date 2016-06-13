package LayerPaint;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

public class MyLine implements Drawable, Strokeable {
    
    private static final double HIT_BOX_SIZE = 10;
    
    public boolean selected = false;
    public SelectBox s;
    private double x1, y1, x2, y2;
    private Color color = Color.BLACK;
    private BasicStroke stroke;
    
    public MyLine() {
    }

    public MyLine(double x1, double y1, double x2, double y2, BasicStroke stroke, Color color) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.stroke = stroke;
            this.color = color;
    }
    
    public MyLine(Tuple4d location){
        x1 = location.x;
        y1 = location.y;
        x2 = location.z;
        y2 = location.w;
    }
    
    @Override
    public BasicStroke stroke(){
        return stroke;
    }

    @Override
    public void draw(Graphics2D g) {
            Line2D r = new Line2D.Double(x1, y1, x2, y2);
            g.setStroke(stroke);
            g.setColor(color);
            g.draw(r);
            if(selected){
                s.draw(g);
            }
    }

    @Override
    public Tuple4d getCoords() {
        return new Tuple4d(x1, y1, x2, y2);
    }
    
    @Override
    public SelectBox getBox(){
        return s;
    }
    
    @Override
    public void setCoords( Tuple4d tuple){
        x1 = tuple.x;
        y1 = tuple.y;
        x2 = tuple.z;
        y2 = tuple.w;
        s = new SelectBox(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x2-x1), Math.abs(y2-y1));
    }
    
    @Override
    public void setStrokeColor(Color c){
        color = c;
    }
    
    
    @Override
    public boolean contains(double x, double y) {
        int boxX = (int) (x - (HIT_BOX_SIZE / 2));
        int boxY = (int) (y - (HIT_BOX_SIZE) / 2);
        int width = (int) HIT_BOX_SIZE;
        int height = (int) HIT_BOX_SIZE;
        Rectangle2D b = new Rectangle2D.Double(boxX, boxY, width, height);
        System.out.println(b);
        Line2D r = new Line2D.Double(x1, y1, x2, y2);
        System.out.println(r);
        return r.intersects(b);
    }

    @Override
    public void select(boolean select) {
        selected = select;
    }
    
    @Override
    public void setStroke(BasicStroke s) {
        stroke = s;
    }
}
