package LayerPaint;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.Objects;

public class MyRectangle implements Drawable, Fillable, Strokeable {

    public boolean selected;
    public SelectBox s;
    private double x1, y1, x2, y2;
    private BasicStroke stroke;
    private Color strokeColor;
    private Color color;

    public MyRectangle() {
    }

    public MyRectangle(double x1, double y1, double x2, double y2, BasicStroke stroke, Color color, Color strokeColor) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.stroke = stroke;
        this.color = color;
        this.strokeColor = strokeColor;
    }

    public MyRectangle(Tuple4d location) {
        x1 = location.x;
        y1 = location.y;
        x2 = location.z;
        y2 = location.w;
    }

    @Override
    public BasicStroke stroke() {
        return stroke;
    }

    @Override
    public SelectBox getBox() {
        return s;
    }

    @Override
    public void draw(Graphics2D g) {
        double x = getStartX();
        double y = getStartY();
        double width = getWidth();
        double height = getHeight();
        Rectangle2D r = new Rectangle2D.Double(x, y, width, height);
        g.setStroke(stroke);
        g.setPaint(color);
        g.fill(r);
        g.setColor(strokeColor);
        g.draw(r);
        g.setColor(Color.BLACK);

        if (selected) {
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
    public void setCoords(Tuple4d tuple) {
        if((tuple.x < tuple.z && tuple.y < tuple.w) || (tuple.x == tuple.z && tuple.y == tuple.w)){
            x1 = tuple.x;
            y1 = tuple.y;
            x2 = tuple.z;
            y2 = tuple.w;
        } else if(tuple.x > tuple.z && tuple.y < tuple.w) {
            x1 = tuple.z;
            y1 = tuple.y;
            x2 = tuple.x;
            y2 = tuple.w;
        } else if(tuple.x < tuple.z && tuple.y > tuple.w){
            x1 = tuple.x;
            y1 = tuple.w;
            x2 = tuple.z;
            y2 = tuple.y;
        } else if(tuple.x > tuple.z && tuple.y > tuple.w){
            x1 = tuple.z;
            y1 = tuple.w;
            x2 = tuple.x;
            y2 = tuple.y;
        }
        s = new SelectBox(getStartX() - 1, getStartY() - 1, getWidth() + 2, getHeight() + 2);
    }

    @Override
    public void setColor(Color c) {
        color = c;
    }

    @Override
    public void setStrokeColor(Color c) {
        strokeColor = c;
    }

    @Override
    public boolean contains(double x, double y) {
        double width = getWidth();
        double height = getHeight();
        Rectangle2D r = new Rectangle2D.Double(getStartX(), getStartY(), width, height);
        return r.contains(x, y);
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
