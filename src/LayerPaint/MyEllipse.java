package LayerPaint;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Ellipse2D;

public class MyEllipse implements Drawable, Fillable {
        
    public boolean selected = false;
    public SelectBox s;
    private double x1, y1, x2, y2;
    private Color color = Color.CYAN;
    private Stroke stroke;
    private Color strokeColor = Color.RED;

    public MyEllipse() {
    }

    public MyEllipse(double x1, double y1, double x2, double y2, Stroke stroke, Color color, Color strokeColor) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.stroke = stroke;
            this.color = color;
            this.strokeColor = strokeColor;
    }

    @Override
    public Stroke stroke(){
        return stroke;
    }

    public MyEllipse(Tuple4d location){
        x1 = location.x;
        y1 = location.y;
        x2 = location.z;
        y2 = location.w;
    }

    @Override
    public boolean equals(Object other){
        return other instanceof MyEllipse;
    }

    @Override
    public SelectBox getBox(){
        return s;
    }

    @Override
    public void draw(Graphics2D g) {
            double x = getStartX();
            double y = getStartY();
            double width = getWidth();
            double height = getHeight();
            Ellipse2D r = new Ellipse2D.Double(x, y, width, height);

            g.setStroke(stroke);
            g.setPaint(color);
            g.fill(r);
            g.setColor(strokeColor);
            g.draw(r);
            g.setColor(Color.BLACK);
            if(selected){
                s = new SelectBox(x, y, width, height);
                s.draw(g);
            }
    }

    private double getWidth() {
            return Math.abs(x1 - x2);
    }

    private double getHeight() {
            return Math.abs(y1 - y2);
    }

    private double getStartX() {
            return Math.min(x1, x2);
    }

    private double getStartY() {
            return Math.min(y1, y2);
    }

    @Override
    public Tuple4d getCoords() {
        return new Tuple4d(x1, y1, x2, y2);
    }

    @Override
    public void setCoords( Tuple4d tuple ){
        x1 = tuple.x;
        y1 = tuple.y;
        x2 = tuple.z;
        y2 = tuple.w;
    }

    @Override
    public void setColor(Color c){
        this.color = c;
    }

    public Color getColor(){
        return color;
    }

    @Override
    public void setStrokeColor(Color c) {
        strokeColor = c;
    }

    @Override
    public boolean contains(double x, double y) {
        double width = getWidth();
        double height = getHeight();
        Ellipse2D r = new Ellipse2D.Double(getStartX(), getStartY(), width, height);
        return r.contains(x, y);
    }
    
    @Override
    public void select(boolean select) {
        selected = select;
    }

    
}
